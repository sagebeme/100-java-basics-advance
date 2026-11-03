package com.learning.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
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
                .andExpect(redirectedUrl("/orders"))
                .andExpect(flash().attribute("message", "Order for Amina saved."));

        var order = orderController.getOrders().get(before);
        assertEquals("Amina", order.getCustomer().getName());
        assertEquals("amina@example.com", order.getCustomer().getEmail());
        assertEquals(2, order.getItems().size());
        assertEquals("Notebook", order.getItems().get(0).getProduct());
        assertEquals(2, order.getItems().get(0).getQuantity());
        assertEquals("Pen", order.getItems().get(1).getProduct());
        assertEquals(5, order.getItems().get(1).getQuantity());
    }

    @Test
    void showsTheOrderFormWithThreeItemRows() throws Exception {
        mockMvc.perform(get("/orders"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("name=\"customer.name\"")))
                .andExpect(content().string(containsString("name=\"items[2].product\"")));
    }

    @Test
    void emptyItemRowsAreDropped() throws Exception {
        int before = orderController.getOrders().size();

        mockMvc.perform(post("/orders")
                        .param("customer.name", "Baraka")
                        .param("items[0].product", "Cement 50kg")
                        .param("items[0].quantity", "3")
                        .param("items[1].product", "")
                        .param("items[1].quantity", "1"))
                .andExpect(redirectedUrl("/orders"));

        assertEquals(1, orderController.getOrders().get(before).getItems().size());
    }

    @Test
    void anOrderWithNoItemsIsShownAgainWithAMessage() throws Exception {
        int before = orderController.getOrders().size();

        mockMvc.perform(post("/orders").param("customer.name", "Baraka").param("items[0].product", " "))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Add at least one item.")))
                .andExpect(content().string(containsString("value=\"Baraka\"")));

        assertEquals(before, orderController.getOrders().size());
    }

    @Test
    void aQuantityThatIsNotANumberIsShownAgainWithAMessage() throws Exception {
        mockMvc.perform(post("/orders").param("customer.name", "Baraka")
                        .param("items[0].product", "Nails").param("items[0].quantity", "many"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Each quantity must be a whole number.")));
    }
}
