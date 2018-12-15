package com.epam.fitness.uitls;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public enum PeriodCost {

    for_14_days(new BigDecimal(10)),
    for_30_days(new BigDecimal(15)),
    for_60_days(new BigDecimal(20)),
    for_90_days(new BigDecimal(25));


    public static Map<String,BigDecimal> getAllCosts(){
        Map<String,BigDecimal> tariffs = new HashMap<>();
        for(PeriodCost tariff : PeriodCost.values()){
            tariffs.put(tariff.toString(),tariff.getCost());
        }
        return tariffs;
    }

    private BigDecimal cost;
    public BigDecimal getCost(){
        return cost;
    }

    private PeriodCost(BigDecimal cost) {
        this.cost = cost;
    }
}
