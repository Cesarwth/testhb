package com.hb.campaign.adapters.controller;

import com.hb.campaign.adapters.request.PriceRequest;
import com.hb.campaign.adapters.response.PriceResponse;
import com.hb.campaign.application.service.PriceService;
import com.hb.campaign.domain.model.Price;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class PriceControllerTest {

    @Mock
    private PriceService priceService;

    @InjectMocks
    private PriceController priceController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(priceController).build();
    }

    private void executeTest(String applicationDate, int productId, int brandId, PriceResponse expectedResponse) throws Exception {
        PriceRequest priceRequest = new PriceRequest();
        priceRequest.setApplicationDate(applicationDate);
        priceRequest.setProductId(productId);
        priceRequest.setBrandId(brandId);

        mockMvc.perform(post("/api/prices")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"applicationDate\": \"" + applicationDate + "\", \"productId\": " + productId + ", \"brandId\": " + brandId + " }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(expectedResponse.getProductId()))
                .andExpect(jsonPath("$.brandId").value(expectedResponse.getBrandId()))
                .andExpect(jsonPath("$.price").value(expectedResponse.getPrice()));
    }

    @Test
    void test2020_06_14_10_00_00() throws Exception {
        String applicationDate = "2020-06-14-10.00.00";
        int productId = 35455;
        int brandId = 1;

        Price mockPrice = new Price();
        mockPrice.setProductId(productId);
        mockPrice.setBrandId(brandId);
        mockPrice.setPrice(35.50);

        when(priceService.getApplicablePrice(productId, brandId, LocalDateTime.of(2020, 6, 14, 10, 0)))
                .thenReturn(Optional.of(mockPrice));

        PriceResponse expectedResponse = new PriceResponse();
        expectedResponse.setProductId(productId);
        expectedResponse.setBrandId(brandId);
        expectedResponse.setPrice(35.50);

        executeTest(applicationDate, productId, brandId, expectedResponse);
    }

    @Test
    void test2020_06_14_16_00_00() throws Exception {
        String applicationDate = "2020-06-14-16.00.00";
        int productId = 35455;
        int brandId = 1;

        Price mockPrice = new Price();
        mockPrice.setProductId(productId);
        mockPrice.setBrandId(brandId);
        mockPrice.setPrice(25.45); // Ajusta el precio según tu caso de prueba

        when(priceService.getApplicablePrice(productId, brandId, LocalDateTime.of(2020, 6, 14, 16, 0)))
                .thenReturn(Optional.of(mockPrice));

        PriceResponse expectedResponse = new PriceResponse();
        expectedResponse.setProductId(productId);
        expectedResponse.setBrandId(brandId);
        expectedResponse.setPrice(25.45); // Ajusta el precio según tu caso de prueba

        executeTest(applicationDate, productId, brandId, expectedResponse);
    }

    @Test
    void test2020_06_14_21_00_00() throws Exception {
        String applicationDate = "2020-06-14-21.00.00";
        int productId = 35455;
        int brandId = 1;

        Price mockPrice = new Price();
        mockPrice.setProductId(productId);
        mockPrice.setBrandId(brandId);
        mockPrice.setPrice(35.50);

        when(priceService.getApplicablePrice(productId, brandId, LocalDateTime.of(2020, 6, 14, 21, 0)))
                .thenReturn(Optional.of(mockPrice));

        PriceResponse expectedResponse = new PriceResponse();
        expectedResponse.setProductId(productId);
        expectedResponse.setBrandId(brandId);
        expectedResponse.setPrice(35.50);

        executeTest(applicationDate, productId, brandId, expectedResponse);
    }

    @Test
    void test2020_06_15_10_00_00() throws Exception {
        String applicationDate = "2020-06-15-10.00.00";
        int productId = 35455;
        int brandId = 1;

        Price mockPrice = new Price();
        mockPrice.setProductId(productId);
        mockPrice.setBrandId(brandId);
        mockPrice.setPrice(30.50);

        when(priceService.getApplicablePrice(productId, brandId, LocalDateTime.of(2020, 6, 15, 10, 0)))
                .thenReturn(Optional.of(mockPrice));

        PriceResponse expectedResponse = new PriceResponse();
        expectedResponse.setProductId(productId);
        expectedResponse.setBrandId(brandId);
        expectedResponse.setPrice(30.50);

        executeTest(applicationDate, productId, brandId, expectedResponse);
    }

    @Test
    void test2020_06_16_21_00_00() throws Exception {
        String applicationDate = "2020-06-16-21.00.00";
        int productId = 35455;
        int brandId = 1;

        Price mockPrice = new Price();
        mockPrice.setProductId(productId);
        mockPrice.setBrandId(brandId);
        mockPrice.setPrice(38.95);

        when(priceService.getApplicablePrice(productId, brandId, LocalDateTime.of(2020, 6, 16, 21, 0)))
                .thenReturn(Optional.of(mockPrice));

        PriceResponse expectedResponse = new PriceResponse();
        expectedResponse.setProductId(productId);
        expectedResponse.setBrandId(brandId);
        expectedResponse.setPrice(38.95);

        executeTest(applicationDate, productId, brandId, expectedResponse);
    }
}
