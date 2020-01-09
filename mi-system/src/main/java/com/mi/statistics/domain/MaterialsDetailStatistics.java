package com.mi.statistics.domain;

import com.mi.common.annotation.Excel;

import java.io.Serializable;
import java.util.Date;

public class MaterialsDetailStatistics implements Serializable {
    /** 入库数量**/
    @Excel(name = "入库数量")
    private Integer putNumber;

    /** 入库总额**/
    @Excel(name = "入库总额")
    private Integer putMoney;

    /** 出库数量**/
    @Excel(name = "出库数量")
    private Integer deliveryNumber;

    /** 出库总额**/
    @Excel(name = "出库总额")
    private Integer deliveryMoney;

    /**退回数量**/
    @Excel(name = "退回数量")
    private Integer backNumber;

    /**退回总额**/
    @Excel(name = "退回总额")
    private Integer backMoney;

    @Excel(name = "报废数量")
    private Integer scrapNumber;

    /**退回总额**/
    @Excel(name = "报废总额")
    private Integer scrapMoney;

    public Integer getBackNumber() {
        return backNumber;
    }

    public void setBackNumber(Integer backNumber) {
        this.backNumber = backNumber;
    }

    public Integer getBackMoney() {
        return backMoney;
    }

    public void setBackMoney(Integer backMoney) {
        this.backMoney = backMoney;
    }

    /** 开始时间 */
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date beginTime;

    /** 结束时间 */
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

    public Integer getPutNumber() {
        return putNumber;
    }

    public void setPutNumber(Integer putNumber) {
        this.putNumber = putNumber;
    }

    public Integer getPutMoney() {
        return putMoney;
    }

    public void setPutMoney(Integer putMoney) {
        this.putMoney = putMoney;
    }

    public Integer getDeliveryNumber() {
        return deliveryNumber;
    }

    public void setDeliveryNumber(Integer deliveryNumber) {
        this.deliveryNumber = deliveryNumber;
    }

    public Integer getDeliveryMoney() {
        return deliveryMoney;
    }

    public void setDeliveryMoney(Integer deliveryMoney) {
        this.deliveryMoney = deliveryMoney;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "MaterialsDetailStatistics{" +
                "putNumber=" + putNumber +
                ", putMoney=" + putMoney +
                ", deliveryNumber=" + deliveryNumber +
                ", deliveryMoney=" + deliveryMoney +
                ", beginTime=" + beginTime +
                ", endTime=" + endTime +
                '}';
    }
}
