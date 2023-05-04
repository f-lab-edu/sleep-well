package com.sleepwell.userapi.payment.repository;

import com.sleepwell.userapi.payment.entity.PaymentResult;
import org.springframework.stereotype.Repository;

@Repository
public class PaymentRepository {
    public PaymentResult save(PaymentResult paymentResult) {
        return paymentResult;
    }
}
