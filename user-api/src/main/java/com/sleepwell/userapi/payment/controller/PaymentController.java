package com.sleepwell.userapi.payment.controller;

import com.sleepwell.userapi.payment.dto.PaymentDetailResponseDto;
import com.sleepwell.userapi.payment.dto.PaymentRequestDto;
import com.sleepwell.userapi.payment.entity.PaymentResult;
import com.sleepwell.userapi.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    public PaymentDetailResponseDto createPaymentResult(@RequestBody PaymentRequestDto paymentRequestDto) {
        PaymentResult paymentResult = paymentService.createPaymentResult(paymentRequestDto.getImpUid(), paymentRequestDto.getMerchantUid());
        return PaymentDetailResponseDto.toDto(paymentResult);
    }
}
