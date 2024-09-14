package com.hb.campaign.adapters.repository;

import com.hb.campaign.domain.model.Price;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class PriceRepositoryImplTest {

    @Mock
    private EntityManager entityManager;

    @Mock
    private TypedQuery<Price> typedQuery;

    @InjectMocks
    private PriceRepositoryImpl priceRepository;

    private static final Logger logger = LoggerFactory.getLogger(PriceRepositoryImplTest.class);

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindApplicablePrice_Found() {
        // Arrange
        int productId = 35455;
        int brandId = 1;
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 10, 0, 0);

        Price price = new Price(); // Asume que tienes una clase de dominio Price con los atributos correspondientes
        when(entityManager.createQuery(anyString(), eq(Price.class))).thenReturn(typedQuery);
        when(typedQuery.setParameter(eq("productId"), eq(productId))).thenReturn(typedQuery);
        when(typedQuery.setParameter(eq("brandId"), eq(brandId))).thenReturn(typedQuery);
        when(typedQuery.setParameter(eq("applicationDate"), eq(applicationDate))).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(List.of(price));

        // Act
        Optional<Price> result = priceRepository.findApplicablePrice(productId, brandId, applicationDate);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(price, result.get());
        verify(typedQuery, times(1)).getResultList();
        logger.info("Test for findApplicablePrice_Found passed");
    }

    @Test
    void testFindApplicablePrice_NotFound() {
        // Arrange
        int productId = 35455;
        int brandId = 1;
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 10, 0, 0);

        when(entityManager.createQuery(anyString(), eq(Price.class))).thenReturn(typedQuery);
        when(typedQuery.setParameter(eq("productId"), eq(productId))).thenReturn(typedQuery);
        when(typedQuery.setParameter(eq("brandId"), eq(brandId))).thenReturn(typedQuery);
        when(typedQuery.setParameter(eq("applicationDate"), eq(applicationDate))).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(Collections.emptyList());

        // Act
        Optional<Price> result = priceRepository.findApplicablePrice(productId, brandId, applicationDate);

        // Assert
        assertFalse(result.isPresent());
        verify(typedQuery, times(1)).getResultList();
        logger.info("Test for findApplicablePrice_NotFound passed");
    }

    @Test
    void testFindApplicablePrice_EmptyList() {
        // Arrange
        int productId = 35455;
        int brandId = 1;
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 10, 0, 0);

        when(entityManager.createQuery(anyString(), eq(Price.class))).thenReturn(typedQuery);
        when(typedQuery.setParameter(eq("productId"), eq(productId))).thenReturn(typedQuery);
        when(typedQuery.setParameter(eq("brandId"), eq(brandId))).thenReturn(typedQuery);
        when(typedQuery.setParameter(eq("applicationDate"), eq(applicationDate))).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(Collections.emptyList());

        // Act
        Optional<Price> result = priceRepository.findApplicablePrice(productId, brandId, applicationDate);

        // Assert
        assertFalse(result.isPresent());
        verify(typedQuery, times(1)).getResultList();
        logger.info("Test for findApplicablePrice_EmptyList passed");
    }
}
