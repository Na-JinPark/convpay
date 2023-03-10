package com.zerobase.convpay.service;

import com.zerobase.convpay.dto.*;
import com.zerobase.convpay.type.MoneyUseCancelResult;
import com.zerobase.convpay.type.MoneyUseResult;
import com.zerobase.convpay.type.PayCancelResult;
import com.zerobase.convpay.type.PayResult;

public class ConveniencePayService {

    private final MoneyAdapter moneyAdapter = new MoneyAdapter();
    public PayResponse pay(PayRequest payRequest){
        MoneyUseResult moneyUseResult = moneyAdapter.use(payRequest.getPayAmount());

        if(moneyUseResult == moneyUseResult.USE_FAIL){
            return new PayResponse(PayResult.FAIL, 0);
        }


        return new PayResponse(PayResult.SUCCESS, payRequest.getPayAmount());
    }
    public PayCancelResponse payCancel(PayCancelRequest payCancelRequest){

        MoneyUseCancelResult moneyUseCancelResult = moneyAdapter.useCancel(payCancelRequest.getPayCancelAmount());

        if(moneyUseCancelResult == MoneyUseCancelResult.MONEY_USE_CANCEL_FAIL){
            return new PayCancelResponse(PayCancelResult.PAY_CANCEL_FAIL,0);
        }
        return new PayCancelResponse(PayCancelResult.PAY_CANCEL_SUCCESS,payCancelRequest.getPayCancelAmount());
    }
}
