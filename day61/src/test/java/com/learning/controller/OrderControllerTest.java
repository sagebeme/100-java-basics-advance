package com.learning.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private OrderController orderController;

    @Test
    void bindsANestedCustomerAndAListOfItemsFromFormFields() throws Exception {
        int before = orderController.getOrders().size();

        mockMvc.perform(post("/orders")
                        .param("customer.name", "Amina")
                        .param("customer.email", "amina@example.com")
                        .param("items[0].product", "Notebook")
                        .param("items[0].quantity", "2")
                        .param("items[1].product", "Pen")
                        .param("items[1].quantity", "5"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/orders"));

        var order = orderController.getOrders().get(before);
        assertEquals("Amina", order.getCustomer().getName());
        assertEquals("amina@example.com", order.getCustomer().getEmail());
        assertEquals(2, order.getItems().size());
        assertEquals("Notebook", order.getItems().get(0).getProduct());
        assertEquals(2, order.getItems().get(0).getQuantity());
        assertEquals("Pen", order.getItems().get(1).getProduct());
        assertEquals(5, order.getItems().get(1).getQuantity());
    }
}
