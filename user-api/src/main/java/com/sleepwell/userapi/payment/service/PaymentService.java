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

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final IamportClient iamportClient;
    private final PaymentRepository paymentRepository;
    private final ReservationService reservationService;

    private static final String IMP_PREFIX = "imp_";

    public PaymentResult createPaymentResult(String impUid, Long merchantUid) {
        Reservation reservation = reservationService.getReservation(merchantUid);
        Payment paymentResponse = getPaymentResponse(impUid);

        if (!PaymentStatus.PAID.isMatch(paymentResponse.getStatus())) {
            throw new RuntimeException("결제가 완료되지 않았습니다.");
        }

        if (paymentResponse.getAmount().intValue() != reservation.getAmount()) {
            throw new RuntimeException("결제 금액이 불일치합니다.");
        }

        PaymentResult paymentResult = new PaymentResult(Long.valueOf(paymentResponse.getImpUid().replace(IMP_PREFIX, "")), paymentResponse.getAmount().intValue(), PaymentStatus.valueOf(paymentResponse.getStatus().toUpperCase()), paymentResponse.getPaidAt(), reservation);
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
