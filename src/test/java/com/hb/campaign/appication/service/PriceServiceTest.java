package com.hb.campaign.appication.service;

import com.hb.campaign.application.service.PriceService;
import com.hb.campaign.domain.model.Price;
import com.hb.campaign.domain.port.PriceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PriceServiceTest {
    @Mock
    private PriceRepository priceRepository;

    @InjectMocks
    private PriceService priceService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getApplicablePrice_ReturnsCorrectPrice() {
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 10, 0);
        int productId = 35455;
        int brandId = 1;

        Price mockPrice = new Price();
        mockPrice.setProductId(productId);
        mockPrice.setBrandId(brandId);
        mockPrice.setPrice(35.50);

        when(priceRepository.findApplicablePrice(productId, brandId, applicationDate))
                .thenReturn(Optional.of(mockPrice));

        Optional<Price> result = priceService.getApplicablePrice(productId, brandId, applicationDate);

        assertTrue(result.isPresent());
        assertEquals(35.50, result.get().getPrice());
        verify(priceRepository, times(1)).findApplicablePrice(productId, brandId, applicationDate);
    }

    @Test
    void getApplicablePrice_NoPriceFound_ReturnsEmpty() {
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 10, 0);
        int productId = 35455;
        int brandId = 1;

        when(priceRepository.findApplicablePrice(productId, brandId, applicationDate))
                .thenReturn(Optional.empty());

        Optional<Price> result = priceService.getApplicablePrice(productId, brandId, applicationDate);

        assertTrue(result.isEmpty());
        verify(priceRepository, times(1)).findApplicablePrice(productId, brandId, applicationDate);
    }
}
