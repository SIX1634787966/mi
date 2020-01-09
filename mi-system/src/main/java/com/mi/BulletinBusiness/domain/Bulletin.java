package com.mi.BulletinBusiness.domain;

import java.text.NumberFormat;

/**
 * 简报
 */
public class Bulletin {
    /** 入库价值总额**/
    private  Double putPriceCount=0D;
    /** 上时段入库价值总额**/
    private  Double LastPutPriceCount=0D;
    /** 出价值总额**/
    private  Double deliveryPrice=0D;
    /** 上时段出价值总额**/
    private  Double LastDeliveryPrice=0D;
    /** 同比时期前段时期升降百分比**/
    private String percentage;
    /** 类型0=上升，1=下降，2=不变**/
    private short msgType;
    /** 利润**/
    private  Double profit;

    public Double getLastPutPriceCount() {
        return LastPutPriceCount;
    }

    public void setLastPutPriceCount(Double lastPutPriceCount) {
        LastPutPriceCount = lastPutPriceCount;
        if(putPriceCount>LastPutPriceCount){
            this.msgType=0;
        }else if(putPriceCount<LastPutPriceCount){
            this.msgType=1;
        }else {
            this.msgType=2;
        }
    }

    public Double getLastDeliveryPrice() {
        return LastDeliveryPrice;
    }

    public void setLastDeliveryPrice(Double lastDeliveryPrice) {
        LastDeliveryPrice = lastDeliveryPrice;
        if(deliveryPrice>LastDeliveryPrice){
            this.msgType=0;
        }else if(deliveryPrice<LastDeliveryPrice){
            this.msgType=1;
        }else {
            this.msgType=2;
        }
        LastDeliveryPrice = lastDeliveryPrice;
    }

    public Double getLastProfit() {
        return LastProfit;
    }

    public void setLastProfit(Double lastMonthPutPriceCount,Double lastMonthDeliveryPrice) {
        Double res=lastMonthDeliveryPrice-lastMonthPutPriceCount;//本时段和上时段差额
        if(profit>res){
            this.msgType=0;
        }else if(profit<res){
            this.msgType=1;
        }else {
            this.msgType=2;
        }
        LastProfit = res;
    }

    /** 上时段利润**/
    private  Double LastProfit;
    public void setPercentage(String percentage){
        this.percentage=percentage;
    }
    public void setPercentage(Double putPriceCount,Double deliveryPrice) {
        Double thisMonth=this.deliveryPrice-this.putPriceCount;//本时段盈利
        Double lastMonth=deliveryPrice-putPriceCount;//上时段盈利
        if(0D==lastMonth){
            this.percentage=thisMonth+"%";
            return ;
        }
        Double res=thisMonth-lastMonth;//本时段和上时段差额
        NumberFormat nf  =  NumberFormat.getPercentInstance();
        nf.setMinimumFractionDigits( 2 );
        if(res>0){
            this.msgType=0;
            this.percentage  =  nf.format((res)/lastMonth);
        }else if(res==0D){
            this.msgType=2;
            this.percentage="0%";
        }else {
            this.msgType=1;
            if(this.deliveryPrice-this.putPriceCount==0D)
            this.percentage=res+"%";
            else
            this.percentage  =  nf.format((res)/lastMonth);
        }







    }
    public Double getProfit() {
        return profit;
    }

    public void setProfit() {
        this.profit = deliveryPrice-putPriceCount;
    }

    public String getPercentage() {
        return percentage;
    }



    public short getMsgType() {
        return msgType;
    }

    public void setMsgType(short msgType) {
        this.msgType = msgType;
    }

    public Double getPutPriceCount() {
        return putPriceCount;
    }

    public void setPutPriceCount(Double putPriceCount) {
        this.putPriceCount = putPriceCount;
    }

    public Double getDeliveryPrice() {
        return deliveryPrice;
    }

    public void setDeliveryPrice(Double deliveryPrice) {
        this.deliveryPrice = deliveryPrice;
    }
}
