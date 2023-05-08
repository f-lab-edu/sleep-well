import React from 'react';
import axios from "axios";

function Payment() {
    function onClickPayment() {
        /* 1. 가맹점 식별하기 */
        const {IMP} = window;
        IMP.init('imp86503506');

        /* 2. 결제 데이터 정의하기 */
        const data = {
            pg: 'html5_inicis',                           // PG사
            pay_method: 'card',                           // 결제수단
            merchant_uid: `491211134`,  // 주문번호
            amount: 100,                                 // 결제금액
            name: '아임포트 결제 테스트',                   // 주문명
            buyer_name: '홍길동',                           // 구매자 이름
            buyer_tel: '01012341234',                     // 구매자 전화번호
            buyer_email: 'email@email.com',               // 구매자 이메일
            buyer_addr: '신사동 661-16',                    // 구매자 주소
            buyer_postcode: '06018',                      // 구매자 우편번호
        };

        /* 4. 결제 창 호출하기 */
        IMP.request_pay(data, callback);
    }

    /* 3. 콜백 함수 정의하기 */
    function callback(response) {
        const {success, imp_uid, merchant_uid, error_msg} = response;

        if (success) {
            alert('결제 성공');
            axios({
                url: "http://localhost:8080/payment",
                method: "post",
                headers: {"Accept": "application/json, text/plain" ,"Content-Type": "application/json"},
                data: {
                    imp_uid: response.imp_uid,
                    merchant_uid: response.merchant_uid,
                },
            }).then((data) => {
                // 서버 결제 API 성공시 로직
            });
        } else {
            alert(`결제 실패: ${error_msg}`);
        }
    }

    return (
        <>
            <button onClick={onClickPayment}>결제하기</button>
        </>
    );
}

export default Payment;
