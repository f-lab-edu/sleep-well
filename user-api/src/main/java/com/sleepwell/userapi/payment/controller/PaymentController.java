package com.sleepwell.userapi.payment.controller;

import com.sleepwell.userapi.payment.dto.PaymentDetailResponseDto;
import com.sleepwell.userapi.payment.dto.PaymentRequestDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @PostMapping
    public PaymentDetailResponseDto completePayment(@RequestBody PaymentRequestDto paymentRequestDto) {
        return new PaymentDetailResponseDto();
    }
}
