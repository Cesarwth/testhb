package com.hb.campaign.domain.port;

import com.hb.campaign.domain.model.Price;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BasePriceRepository extends JpaRepository<Price, Long> {
}