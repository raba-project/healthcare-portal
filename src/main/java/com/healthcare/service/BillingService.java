package com.healthcare.service;

import java.util.HashMap;
import java.util.Map;

public class BillingService {
    private Map<Integer, Double> billingRecords = new HashMap<>();

    public void generateBill(int appointmentId, double amount) {
        billingRecords.put(appointmentId, amount);
        System.out.println("Bill generated for appointment " + appointmentId + ": $" + amount);
    }

    public Double getBill(int appointmentId) {
        return billingRecords.get(appointmentId);
    }
}
