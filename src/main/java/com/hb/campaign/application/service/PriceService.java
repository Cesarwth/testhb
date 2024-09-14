package com.hb.campaign.application.service;

import com.hb.campaign.domain.model.Price;
import com.hb.campaign.domain.port.PriceRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PriceService {
    private final PriceRepository priceRepository;

    public PriceService(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    public Optional<Price> getApplicablePrice(int productId, int brandId, LocalDateTime applicationDate) {
        return priceRepository.findApplicablePrice(productId, brandId, applicationDate);
    }
}
