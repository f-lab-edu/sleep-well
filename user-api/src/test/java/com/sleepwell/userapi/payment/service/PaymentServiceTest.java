package com.sleepwell.userapi.payment.service;

import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;
import com.sleepwell.userapi.payment.entity.PaymentResult;
import com.sleepwell.userapi.payment.repository.PaymentRepository;
import com.sleepwell.userapi.reservation.entity.Reservation;
import com.sleepwell.userapi.reservation.service.ReservationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PaymentServiceTest {

    @Mock
    private IamportClient iamportClient;

    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private ReservationService reservationService;

    @InjectMocks
    private PaymentService paymentService;

    private Payment payment;

    private Reservation reservation;

    @BeforeEach
    void setup() throws IamportResponseException, IOException {
        payment = mock(Payment.class);
        reservation = mock(Reservation.class);
        when(reservationService.getReservation(any())).thenReturn(reservation);
        when(iamportClient.paymentByImpUid(any())).thenReturn(mock(IamportResponse.class));
        when(iamportClient.paymentByImpUid(any()).getResponse()).thenReturn(payment);
    }

    @DisplayName("결제가 완료되지 않은 상태라면 예외 발생")
    @Test
    void createPaymentResultWithInvalidStatus() {
        //given
        when(payment.getStatus()).thenReturn("FAILED");

        //then
        assertThrows(RuntimeException.class, () -> paymentService.createPaymentResult("1", "1"));
    }

    @DisplayName("결제 금액이 예약 금액과 일치하지 않으면 예외 발생")
    @Test
    void createPaymentResultWithInvalidAmount() {
        //given
        when(payment.getStatus()).thenReturn("PAID");
        when(payment.getAmount()).thenReturn(BigDecimal.TEN);
        when(reservation.getAmount()).thenReturn(BigDecimal.ONE);

        //then
        assertThrows(RuntimeException.class, () -> paymentService.createPaymentResult("1", "1"));
    }

    @DisplayName("정상 결제 요청 시 결제 정보 반환")
    @Test
    void createPaymentResultWithValidPaymentRequest() {
        //given
        PaymentResult paymentResult = mock(PaymentResult.class);
        when(payment.getStatus()).thenReturn("PAID");
        when(payment.getAmount()).thenReturn(BigDecimal.TEN);
        when(reservation.getAmount()).thenReturn(BigDecimal.TEN);
        when(payment.getImpUid()).thenReturn("1");
        when(payment.getPaidAt()).thenReturn(mock(Date.class));
        doNothing().when(reservation).updatePayment(any());
        when(paymentRepository.save(any())).thenReturn(paymentResult);

        //then
        assertEquals(paymentResult, paymentService.createPaymentResult("1", "1"));
    }
}
