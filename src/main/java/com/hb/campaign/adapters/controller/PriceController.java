package com.hb.campaign.adapters.controller;

import com.hb.campaign.adapters.request.PriceRequest;
import com.hb.campaign.adapters.response.PriceResponse;
import com.hb.campaign.application.service.PriceService;
import com.hb.campaign.domain.model.Price;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class PriceController {
    private final PriceService priceService;
    private static final Logger logger = LoggerFactory.getLogger(PriceController.class);

    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    @PostMapping("/prices")
    public ResponseEntity<?> getPrice(@RequestBody PriceRequest priceRequest) {
        logger.info("Received PriceRequest: {}", priceRequest);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss");
        LocalDateTime applicationDate = LocalDateTime.parse(priceRequest.getApplicationDate(), formatter);

        Optional<Price> price = priceService.getApplicablePrice(priceRequest.getProductId(), priceRequest.getBrandId(), applicationDate);

        if (price.isPresent()) {
            Price p = price.get();
            PriceResponse response = new PriceResponse();
            response.setProductId(p.getProductId());
            response.setBrandId(p.getBrandId());
            response.setPriceList(p.getPriceList());
            response.setStartDate(p.getStartDate());
            response.setEndDate(p.getEndDate());
            response.setPrice(p.getPrice());

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.ok("No hay datos disponibles para el producto solicitado");
        }
    }
}
