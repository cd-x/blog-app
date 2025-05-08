package com.example.springlearnings.services.pricing.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pricing {
    private PricingType type;
    private Double charge;
    List<String> benefits;
}
