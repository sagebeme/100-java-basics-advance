package com.learning.tikiti;

import com.jayway.jsonpath.JsonPath;
import jakarta.servlet.http.Cookie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.util.Base64;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD) // a fresh clock and token store per test
class TokenApiTest {

    private static final Instant START = Instant.parse("2026-10-05T09:00:00Z");

    @TestConfiguration
    static class TestClock {
        @Bean
        @Primary
        MutableClock testClock() {
            return new MutableClock(START);
        }
    }

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MutableClock clock;

    @BeforeEach
    void startTheClock() {
        assertEquals(START, clock.instant());
    }

    private MockHttpServletResponse login(String username, String password) throws Exception {
        return mockMvc.perform(post("/auth/login").contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"" + username + "\",\"password\":\"" + password + "\"}"))
                .andReturn().getResponse();
    }

    private MockHttpServletResponse login(String username) throws Exception {
        return login(username, "matatu-sunset-42");
    }

    private static String accessToken(MockHttpServletResponse response) throws Exception {
        return JsonPath.read(response.getContentAsString(), "$.accessToken");
    }

    private static Cookie refreshCookie(MockHttpServletResponse response) {
        return response.getCookie(AuthController.REFRESH_COOKIE);
    }

    private MockHttpServletResponse refresh(Cookie cookie) throws Exception {
        return mockMvc.perform(post("/auth/refresh").cookie(cookie)).andReturn().getResponse();
    }

    private static String base64(String json) {
        return Base64.getUrlEncoder().withoutPadding().encodeToString(json.getBytes(StandardCharsets.UTF_8));
    }

    @Test
    void loginGivesAnAccessTokenInTheBodyAndARefreshTokenInALockedDownCookie() throws Exception {
        MockHttpServletResponse response = login("amina");
        assertEquals(200, response.getStatus());
        assertEquals("Bearer", JsonPath.read(response.getContentAsString(), "$.tokenType"));
        assertEquals(900, (int) JsonPath.read(response.getContentAsString(), "$.expiresIn"));

        String[] parts = accessToken(response).split("\\.");
        assertEquals(3, parts.length);
        String payload = new String(Base64.getUrlDecoder().decode(parts[1]), StandardCharsets.UTF_8);
        assertTrue(payload.contains("\"sub\":\"amina\""), "anyone can read the payload: " + payload);
        assertTrue(payload.contains("\"role\":\"FAN\""), payload);

        String setCookie = response.getHeader("Set-Cookie");
        assertNotNull(setCookie);
        for (String part : new String[] {"HttpOnly", "Secure", "SameSite=Strict", "Path=/auth"}) {
            assertTrue(setCookie.contains(part), setCookie);
        }
    }

    @Test
    void aWrongPasswordGetsA401AndNoTokens() throws Exception {
        MockHttpServletResponse response = login("amina", "guess");
        assertEquals(401, response.getStatus());
        assertNull(response.getHeader("Set-Cookie"));
    }

    @Test
    void meIsAnsweredFromTheTokenAlone() throws Exception {
        String token = accessToken(login("amina"));
        mockMvc.perform(get("/me").header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("amina"))
                .andExpect(jsonPath("$.role").value("FAN"));
    }

    @Test
    void noTokenMeans401AndAWwwAuthenticateHeader() throws Exception {
        mockMvc.perform(get("/me"))
                .andExpect(status().isUnauthorized())
                // Spring Security 7 also adds a resource_metadata link telling clients where to learn about this API.
                .andExpect(header().string("WWW-Authenticate", startsWith("Bearer")));
    }

    @Test
    void anExpiredTokenIsRefused() throws Exception {
        String token = accessToken(login("amina"));
        clock.advance(Duration.ofMinutes(15).minusSeconds(1));
        mockMvc.perform(get("/me").header("Authorization", "Bearer " + token)).andExpect(status().isOk());
        // Spring still accepts the token during its exp second itself, so step one second past it.
        clock.advance(Duration.ofSeconds(2));
        mockMvc.perform(get("/me").header("Authorization", "Bearer " + token))
                .andExpect(status().isUnauthorized())
                .andExpect(header().string("WWW-Authenticate", allOf(startsWith("Bearer error=\"invalid_token\""), containsString("expired"))));
    }

    @Test
    void anEditedPayloadBreaksTheSignature() throws Exception {
        String[] parts = accessToken(login("amina")).split("\\.");
        String promoted = parts[0] + "." + base64("{\"sub\":\"amina\",\"role\":\"ORGANISER\",\"exp\":4102444800}") + "." + parts[2];
        mockMvc.perform(post("/events").header("Authorization", "Bearer " + promoted))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void algNoneIsRefusedBecauseTheServerPicksTheAlgorithm() throws Exception {
        String unsigned = base64("{\"alg\":\"none\",\"typ\":\"JWT\"}") + "." + base64("{\"sub\":\"amina\",\"role\":\"ORGANISER\",\"exp\":4102444800}") + ".";
        mockMvc.perform(post("/events").header("Authorization", "Bearer " + unsigned))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void a401MeansWhoAreYouAndA403MeansIKnowAndNo() throws Exception {
        String fan = accessToken(login("amina"));
        String organiser = accessToken(login("baraka"));
        mockMvc.perform(post("/events").header("Authorization", "Bearer " + fan)).andExpect(status().isForbidden());
        mockMvc.perform(post("/events").header("Authorization", "Bearer " + organiser)).andExpect(status().isCreated());
        mockMvc.perform(post("/events")).andExpect(status().isUnauthorized());
    }

    @Test
    void refreshingGivesANewAccessTokenAndANewRefreshCookie() throws Exception {
        Cookie first = refreshCookie(login("amina"));
        clock.advance(Duration.ofMinutes(20)); // the access token is long gone
        MockHttpServletResponse response = refresh(first);
        assertEquals(200, response.getStatus());
        assertNotEquals(first.getValue(), refreshCookie(response).getValue());
        mockMvc.perform(get("/me").header("Authorization", "Bearer " + accessToken(response))).andExpect(status().isOk());
    }

    @Test
    void replayingAnOldRefreshTokenLogsTheWholeFamilyOut() throws Exception {
        Cookie stolen = refreshCookie(login("amina"));
        Cookie current = refreshCookie(refresh(stolen));
        MockHttpServletResponse replay = refresh(stolen);
        assertEquals(401, replay.getStatus());
        assertEquals("Log in again", JsonPath.read(replay.getContentAsString(), "$.error"));
        assertEquals(0, refreshCookie(replay).getMaxAge()); // the cookie is cleared
        assertEquals(401, refresh(current).getStatus());
    }

    @Test
    void logoutStopsTheRefreshTokenButCannotTakeBackTheAccessToken() throws Exception {
        MockHttpServletResponse response = login("amina");
        Cookie cookie = refreshCookie(response);
        mockMvc.perform(post("/auth/logout").cookie(cookie)).andExpect(status().isNoContent());
        assertEquals(401, refresh(cookie).getStatus());
        // Still valid until it expires: this is why access tokens are short.
        mockMvc.perform(get("/me").header("Authorization", "Bearer " + accessToken(response))).andExpect(status().isOk());
    }
}
