package com.example.springlearnings.services.pricing.interfaces;

import com.example.springlearnings.services.pricing.models.Pricing;

import java.util.List;

public interface IJournalPricingService {
    List<Pricing> getPricing();

    void updatePricing(List<Pricing> pricingList);
}
