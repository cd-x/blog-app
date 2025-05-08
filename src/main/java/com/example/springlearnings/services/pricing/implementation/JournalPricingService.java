package com.example.springlearnings.services.pricing.implementation;

import com.example.springlearnings.services.pricing.interfaces.IJournalPricingService;
import com.example.springlearnings.services.pricing.models.Pricing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JournalPricingService implements IJournalPricingService {

    @Autowired
    private RedisTemplate redisTemplate;
    private static final String PRICING_KEY = "pricing";

    @Override
    public List<Pricing> getPricing() {
        return (List<Pricing>) redisTemplate.opsForValue().get(PRICING_KEY);
    }

    @Override
    public void updatePricing(List<Pricing> pricingList) {
        redisTemplate.opsForValue().getAndSet(PRICING_KEY, pricingList);
    }
}
