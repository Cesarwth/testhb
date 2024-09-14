package com.hb.campaign.domain.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PriceTest {

    @Test
    public void testPriceEntityCreation() {
        // Crear una instancia de Price
        Price price = new Price();

        // Verificar que la instancia no es nula
        assertNotNull(price);

        // Configurar valores
        price.setBrandId(1);
        price.setStartDate(LocalDateTime.of(2020, 6, 14, 0, 0));
        price.setEndDate(LocalDateTime.of(2020, 12, 31, 23, 59));
        price.setPriceList(1);
        price.setProductId(35455);
        price.setPriority(0);
        price.setPrice(35.50);
        price.setCurrency("EUR");

        // Verificar los valores establecidos
        assertEquals(1, price.getBrandId());
        assertEquals(LocalDateTime.of(2020, 6, 14, 0, 0), price.getStartDate());
        assertEquals(LocalDateTime.of(2020, 12, 31, 23, 59), price.getEndDate());
        assertEquals(1, price.getPriceList());
        assertEquals(35455, price.getProductId());
        assertEquals(0, price.getPriority());
        assertEquals(35.50, price.getPrice());
        assertEquals("EUR", price.getCurrency());
    }
}
