package com.zerobase.convpay.service;

import com.zerobase.convpay.type.CancelPaymentResult;
import com.zerobase.convpay.type.PaymentResult;

public interface PaymentInterface {
    // MoneyAdaper와 CardAdapter가 PaymentInterface에 따라서 결제수단을 사용하고 취소하기 위해 만든 인터페이스

    PaymentResult payment(Integer payAmount);
    CancelPaymentResult cancelPayment(Integer cancelAmount);
}
