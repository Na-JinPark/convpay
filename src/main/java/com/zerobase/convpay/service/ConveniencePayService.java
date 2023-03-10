package com.zerobase.convpay.service;

import com.zerobase.convpay.dto.*;
import com.zerobase.convpay.type.*;

public class ConveniencePayService {

    private final MoneyAdapter moneyAdapter = new MoneyAdapter();
    private final CardAdapter cardAdapter = new CardAdapter();
    public PayResponse pay(PayRequest payRequest){

        //방법 2 결제인터페이스를 사용
        PaymentInterface paymentInterface;
        if(payRequest.getPayMethodType() == PayMethodType.CARD){
            paymentInterface = cardAdapter;
        }else {
            paymentInterface = moneyAdapter;
        }
        PaymentResult payment = paymentInterface.payment(payRequest.getPayAmount());
        if(payment == PaymentResult.PAYMENT_FAIL){
            return new PayResponse(PayResult.FAIL, 0);
        }
        return new PayResponse(PayResult.SUCCESS, payRequest.getPayAmount());

       /* 방법 1
          현금결제만 있는 상황에서 카드결제를 추가했을때
          편결이 사용시 if문을 이용해서 현금결제 카드결제를 사용하도록 적용
          결제방식이 더 잇을 경우 코드가 더 복잡해질수있다.
        CardUseResult cardUseResult;
        MoneyUseResult moneyUseResult;

        if(payRequest.getPayMethodType() == PayMethodType.CARD){
            cardAdapter.authorization();
            cardAdapter.approval();
            cardUseResult = cardAdapter.capture(payRequest.getPayAmount());
        } else {
            moneyUseResult = moneyAdapter.use(payRequest.getPayAmount());
        }

        if(cardUseResult == CardUseResult.USE_FAIL || moneyUseResult == moneyUseResult.USE_FAIL){
            return new PayResponse(PayResult.FAIL, 0);
        }

        return new PayResponse(PayResult.SUCCESS, payRequest.getPayAmount());*/
    }
    public PayCancelResponse payCancel(PayCancelRequest payCancelRequest){

        PaymentInterface paymentInterface;
        if(payCancelRequest.getPayMethodType() == PayMethodType.CARD){
            paymentInterface = cardAdapter;
        }else {
            paymentInterface = moneyAdapter;
        }
        CancelPaymentResult moneyUseCancelResult = paymentInterface.cancelPayment(payCancelRequest.getPayCancelAmount());
        if(moneyUseCancelResult == MoneyUseCancelResult.MONEY_USE_CANCEL_FAIL){
            return new PayCancelResponse(PayCancelResult.PAY_CANCEL_FAIL,0);
        }
        return new PayCancelResponse(PayCancelResult.PAY_CANCEL_SUCCESS,payCancelRequest.getPayCancelAmount());

        /*MoneyUseCancelResult moneyUseCancelResult = moneyAdapter.useCancel(payCancelRequest.getPayCancelAmount());

        if(moneyUseCancelResult == MoneyUseCancelResult.MONEY_USE_CANCEL_FAIL){
            return new PayCancelResponse(PayCancelResult.PAY_CANCEL_FAIL,0);
        }
        return new PayCancelResponse(PayCancelResult.PAY_CANCEL_SUCCESS,payCancelRequest.getPayCancelAmount());*/
    }
}
