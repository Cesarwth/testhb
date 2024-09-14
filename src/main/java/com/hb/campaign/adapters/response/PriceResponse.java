package com.hb.campaign.adapters.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PriceResponse {
    private int productId;
    private int brandId;
    private int priceList;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private double price;
}