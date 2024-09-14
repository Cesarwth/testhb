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
    private PriceRepository priceRepository;  // Simulamos el repositorio

    @InjectMocks
    private PriceService priceService;  // Servicio que estamos probando

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);  // Inicializamos los mocks
    }

    @Test
    void testGetApplicablePrice_ReturnsCorrectPrice() {
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 10, 0);
        int productId = 35455;
        int brandId = 1;

        Price mockPrice = new Price();
        mockPrice.setProductId(productId);
        mockPrice.setBrandId(brandId);
        mockPrice.setPrice(35.50);

        // Simulamos que el repositorio devuelve un precio válido
        when(priceRepository.findApplicablePrice(productId, brandId, applicationDate))
                .thenReturn(Optional.of(mockPrice));

        // Cuando
        Optional<Price> result = priceService.getApplicablePrice(productId, brandId, applicationDate);

        // Entonces
        assertTrue(result.isPresent());
        assertEquals(35.50, result.get().getPrice());
        verify(priceRepository, times(1)).findApplicablePrice(productId, brandId, applicationDate);
    }

    @Test
    void testGetApplicablePrice_NoPriceFound_ReturnsEmpty() {
        // Dado
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 10, 0);
        int productId = 35455;
        int brandId = 1;

        // Simulamos que no se encuentra ningún precio
        when(priceRepository.findApplicablePrice(productId, brandId, applicationDate))
                .thenReturn(Optional.empty());

        // Cuando
        Optional<Price> result = priceService.getApplicablePrice(productId, brandId, applicationDate);

        // Entonces
        assertTrue(result.isEmpty());
        verify(priceRepository, times(1)).findApplicablePrice(productId, brandId, applicationDate);
    }
}
