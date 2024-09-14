package com.hb.campaign.adapters.repository;

import com.hb.campaign.domain.model.Price;
import com.hb.campaign.domain.port.PriceRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class PriceRepositoryImpl implements PriceRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    private static final Logger logger = LoggerFactory.getLogger(PriceRepositoryImpl.class);

    @Override
    public Optional<Price> findApplicablePrice(int productId, int brandId, LocalDateTime applicationDate) {
        String queryStr = "SELECT p FROM Price p WHERE p.productId = :productId " +
                "AND p.brandId = :brandId " +
                "AND :applicationDate BETWEEN p.startDate AND p.endDate " +
                "ORDER BY p.priority DESC";

        TypedQuery<Price> query = entityManager.createQuery(queryStr, Price.class);
        query.setParameter("productId", productId);
        query.setParameter("brandId", brandId);
        query.setParameter("applicationDate", applicationDate);

        List<Price> prices = query.getResultList();

        if (prices.isEmpty()) {
            logger.info("No se encontraron precios aplicables para productId={}, brandId={}, applicationDate={}", productId, brandId, applicationDate);
        } else {
            logger.info("Se encontraron {} precios aplicables para productId={}, brandId={}, applicationDate={}", prices.size(), productId, brandId, applicationDate);
        }

        return prices.stream().findFirst();
    }
}