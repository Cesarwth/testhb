package com.hb.campaign.adapters.controller;

import com.hb.campaign.application.service.PriceService;
import com.hb.campaign.domain.model.Price;
import com.hb.campaign.domain.port.PriceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PriceControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PriceRepository priceRepository;

    @Autowired
    private PriceService priceService;

    @BeforeEach
    void setUp() {
        priceRepository.deleteAll();

        Price price = new Price();
        price.setProductId(35455);
        price.setBrandId(1);
        price.setPriceList(1);
        price.setStartDate(LocalDateTime.of(2020, 6, 14, 10, 0));
        price.setEndDate(LocalDateTime.of(2020, 6, 14, 20, 0));
        price.setPrice(35.50);
        price.setCurrency("EUR");
        priceRepository.save(price);
    }

    @Test
    void testGetPrice() throws Exception {
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 10, 0);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss");
        String applicationDateStr = applicationDate.format(formatter);

        mockMvc.perform(post("/api/prices")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"applicationDate\": \"" + applicationDateStr + "\", \"productId\": 35455, \"brandId\": 1 }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId", is(35455)))
                .andExpect(jsonPath("$.brandId", is(1)))
                .andExpect(jsonPath("$.price", is(35.50)));
    }
}
