package com.learning;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void loggedOutVisitorSeesThePleaseLogInMessage() throws Exception {
        mockMvc.perform(get("/account"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Please log in")))
                .andExpect(content().string(not(containsString("Welcome back"))));
    }

    @Test
    void loggedInVisitorSeesTheWelcomeMessage() throws Exception {
        mockMvc.perform(get("/account").param("loggedIn", "true"))
                .andExpect(content().string(containsString("Welcome back")))
                .andExpect(content().string(not(containsString("Please log in"))));
    }

    @Test
    void adminRoleHitsTheAdminSwitchCase() throws Exception {
        mockMvc.perform(get("/account").param("role", "ADMIN"))
                .andExpect(content().string(containsString("administrator access")));
    }

    @Test
    void memberRoleHitsTheMemberSwitchCase() throws Exception {
        mockMvc.perform(get("/account").param("role", "MEMBER"))
                .andExpect(content().string(containsString("member access")));
    }

    @Test
    void unknownRoleFallsThroughToTheDefaultCase() throws Exception {
        mockMvc.perform(get("/account").param("role", "SOMETHING_ELSE"))
                .andExpect(content().string(containsString("browsing as a guest")));
    }

    @Test
    void premiumBadgeNeedsBothLoggedInAndEnoughBalance() throws Exception {
        mockMvc.perform(get("/account").param("loggedIn", "true").param("balance", "150"))
                .andExpect(content().string(containsString("Premium badge unlocked")));
    }

    @Test
    void loggedInWithTooLittleBalanceDoesNotUnlockPremium() throws Exception {
        mockMvc.perform(get("/account").param("loggedIn", "true").param("balance", "10"))
                .andExpect(content().string(not(containsString("Premium badge unlocked"))));
    }

    @Test
    void enoughBalanceWithoutBeingLoggedInDoesNotUnlockPremium() throws Exception {
        mockMvc.perform(get("/account").param("loggedIn", "false").param("balance", "150"))
                .andExpect(content().string(not(containsString("Premium badge unlocked"))));
    }
}
