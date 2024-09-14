package com.hb.campaign.adapters.request;

import lombok.Data;

@Data
public class PriceRequest {
    private String applicationDate;
    private int productId;
    private int brandId;
}
