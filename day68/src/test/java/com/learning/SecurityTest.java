package com.learning;

import com.learning.repository.AppUserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class SecurityTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void registeringStoresAHashedPasswordNeverThePlaintext() throws Exception {
        mockMvc.perform(post("/register").with(csrf()).param("username", "amina").param("password", "secret123"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"));

        var saved = appUserRepository.findByUsername("amina").orElseThrow();
        assertNotEquals("secret123", saved.getPassword());
        assertTrue(passwordEncoder.matches("secret123", saved.getPassword()));
    }

    @Test
    void anUnauthenticatedRequestToTheDashboardIsRedirectedToLogin() throws Exception {
        mockMvc.perform(get("/dashboard"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"));
    }

    @Test
    void theHomeAndRegisterPagesAreOpenWithoutLoggingIn() throws Exception {
        mockMvc.perform(get("/register")).andExpect(status().isOk());
    }

    @Test
    void loggingInWithARealAccountReachesTheDashboard() throws Exception {
        mockMvc.perform(post("/register").with(csrf()).param("username", "kip").param("password", "hunter2"));

        mockMvc.perform(formLogin().user("kip").password("hunter2"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/dashboard"));
    }

    @Test
    void loggingInWithTheWrongPasswordIsRejected() throws Exception {
        mockMvc.perform(post("/register").with(csrf()).param("username", "zawadi").param("password", "correct-password"));

        mockMvc.perform(formLogin().user("zawadi").password("wrong-password"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login?error"));
    }
}
