package edu.pss.bean;

import java.io.Serializable;
import java.util.Date;

public class DealRecord implements Serializable{

    private static final long serialVersionUID = -6180705600667126741L;

    public DealRecord(){

    }
    private Long orderId;//订单号
    private Long cid;//客户id
    private Date dealDate;//交易日期
    private String salesMan;//销售员
    private String receiveAddress;//收货地址
    private Long billId;//订单号
    private Double amount;//总金额


    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    public void setDealDate(Date dealDate) {
        this.dealDate = dealDate;
    }

    public void setSalesMan(String salesMan) {
        this.salesMan = salesMan;
    }

    public void setReceiveAddress(String receiveAddress) {
        this.receiveAddress = receiveAddress;
    }

    public void setBillId(Long billId) {
        this.billId = billId;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getOrderId() {
        return orderId;
    }

    public Long getCid() {
        return cid;
    }

    public Date getDealDate() {
        return dealDate;
    }

    public String getSalesMan() {
        return salesMan;
    }

    public String getReceiveAddress() {
        return receiveAddress;
    }

    public Long getBillId() {
        return billId;
    }

    public Double getAmount() {
        return amount;
    }
}
