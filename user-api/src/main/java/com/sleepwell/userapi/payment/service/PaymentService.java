package com.sleepwell.userapi.payment.service;

import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.Payment;
import com.sleepwell.userapi.payment.entity.PaymentResult;
import com.sleepwell.userapi.payment.entity.PaymentStatus;
import com.sleepwell.userapi.payment.repository.PaymentRepository;
import com.sleepwell.userapi.reservation.entity.Reservation;
import com.sleepwell.userapi.reservation.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final IamportClient iamportClient;
    private final PaymentRepository paymentRepository;
    private final ReservationService reservationService;

    public PaymentResult createPaymentResult(String impUid, String merchantUid) {
        Reservation reservation = reservationService.getReservation(Long.valueOf(merchantUid));
        Payment paymentResponse = getPaymentResponse(impUid);

        if (PaymentStatus.PAID.isMatch(paymentResponse.getStatus())) {
            throw new RuntimeException("결제가 완료되지 않았습니다.");
        }

        //TODO: 결제 금액 불일치 예외 추가예정
        if (!Objects.equals(paymentResponse.getAmount(), reservation.getAmount())) {
            throw new RuntimeException("결제 금액이 불일치합니다.");
        }

        PaymentResult paymentResult = new PaymentResult(paymentResponse);
        reservation.updatePayment(paymentResult);
        return paymentRepository.save(paymentResult);
    }

    private Payment getPaymentResponse(String impUid) {
        try {
            return iamportClient.paymentByImpUid(impUid).getResponse();
        } catch (IamportResponseException | IOException e) {
            throw new RuntimeException("존재하지 않는 결제 정보입니다.");
        }
    }
}
