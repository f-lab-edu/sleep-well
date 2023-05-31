package com.sleepwell.userapi.payment.repository;

import com.sleepwell.userapi.payment.entity.PaymentResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentResult, Long> {

}
