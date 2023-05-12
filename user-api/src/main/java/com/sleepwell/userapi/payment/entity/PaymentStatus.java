package com.sleepwell.userapi.payment.entity;

public enum PaymentStatus {

    READY, PAID, FAILED, CANCELED;

    public boolean isMatch(String paymentStatus) {
        return this.name().equals(paymentStatus.toUpperCase());
    }
}
