package com.zerobase.convpay.dto;

import com.zerobase.convpay.type.ConvenienceType;
import com.zerobase.convpay.type.PayMethodType;

public class PayRequest {

    //편의점 종류
    ConvenienceType convenienceType;
    //
    Integer payAmount;
    //결제수단
    PayMethodType payMethodType;

    public PayRequest(PayMethodType payMethodType, ConvenienceType convenienceType, Integer payAmount) {
        this.payMethodType = payMethodType;
        this.convenienceType = convenienceType;
        this.payAmount = payAmount;
    }

    public ConvenienceType getConvenienceType() {
        return convenienceType;
    }

    public void setConvenienceType(ConvenienceType convenienceType) {
        this.convenienceType = convenienceType;
    }

    public Integer getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(Integer payAmount) {
        this.payAmount = payAmount;
    }

    public PayMethodType getPayMethodType() {
        return payMethodType;
    }

    public void setPayMethodType(PayMethodType payMethodType) {
        this.payMethodType = payMethodType;
    }
}
