package com.hb.campaign.domain.port;

import com.hb.campaign.domain.model.Price;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceRepositoryCustom {
    Optional<Price> findApplicablePrice(int productId, int brandId, LocalDateTime applicationDate);
}
