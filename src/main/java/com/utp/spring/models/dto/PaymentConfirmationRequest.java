package com.utp.spring.models.dto;

public class PaymentConfirmationRequest {
    private String paymentMethodId;

    // Getters y setters
    public String getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(String paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }
}
