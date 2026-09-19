package com.learning.controller;

import com.learning.repository.ProductRepository;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Exercises the full add-to-cart -> checkout -> order flow through real HTTP requests against the
 * real database-backed product catalog and order repository - no mocking.
 */
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class ShoppingFlowTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductRepository productRepository;

    @Test
    void catalogListsSeededProducts() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Mechanical Keyboard")));
    }

    @Test
    void addingToCartThenCheckingOutWithAValidCardCreatesAPaidOrder() throws Exception {
        Long productId = productRepository.findAll().get(0).getId();

        MvcResult addResult = mockMvc.perform(post("/cart/add/" + productId).param("quantity", "2"))
                .andExpect(status().is3xxRedirection())
                .andReturn();
        HttpSession session = addResult.getRequest().getSession();

        mockMvc.perform(get("/cart").session((org.springframework.mock.web.MockHttpSession) session))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Mechanical Keyboard")));

        MvcResult checkoutResult = mockMvc.perform(post("/checkout")
                        .session((org.springframework.mock.web.MockHttpSession) session)
                        .param("email", "buyer@example.com")
                        .param("cardNumber", "4242424242424242")
                        .param("expiryMonth", "12")
                        .param("expiryYear", "2030")
                        .param("cvv", "123"))
                .andExpect(status().is3xxRedirection())
                .andReturn();

        String redirectedUrl = checkoutResult.getResponse().getRedirectedUrl();
        assertNotNull(redirectedUrl);
        assertTrue(redirectedUrl.startsWith("/orders/"));

        mockMvc.perform(get(redirectedUrl).session((org.springframework.mock.web.MockHttpSession) session))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("PAID")));

        // The cart should now be empty since the payment was approved.
        mockMvc.perform(get("/cart").session((org.springframework.mock.web.MockHttpSession) session))
                .andExpect(content().string(containsString("Your cart is empty")));
    }

    @Test
    void checkingOutWithTheDeclineTestCardCreatesADeclinedOrderAndKeepsTheCart() throws Exception {
        Long productId = productRepository.findAll().get(0).getId();

        MvcResult addResult = mockMvc.perform(post("/cart/add/" + productId).param("quantity", "1"))
                .andReturn();
        org.springframework.mock.web.MockHttpSession session =
                (org.springframework.mock.web.MockHttpSession) addResult.getRequest().getSession();

        MvcResult checkoutResult = mockMvc.perform(post("/checkout")
                        .session(session)
                        .param("email", "buyer@example.com")
                        .param("cardNumber", "4000000000000002")
                        .param("expiryMonth", "12")
                        .param("expiryYear", "2030")
                        .param("cvv", "123"))
                .andExpect(status().is3xxRedirection())
                .andReturn();

        String redirectedUrl = checkoutResult.getResponse().getRedirectedUrl();
        mockMvc.perform(get(redirectedUrl).session(session))
                .andExpect(content().string(containsString("DECLINED")));

        mockMvc.perform(get("/cart").session(session))
                .andExpect(content().string(not(containsString("Your cart is empty"))));
    }
}
