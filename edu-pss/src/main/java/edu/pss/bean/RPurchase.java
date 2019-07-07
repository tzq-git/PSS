package edu.pss.bean;

import java.io.Serializable;
import java.util.Date;

public class RPurchase  implements Serializable{


    private static final long serialVersionUID = -5339651464405232640L;

    public RPurchase(){

    }

    private  Long rPurchaseId;//退货id
    private Long pid;//供应商id
    private  String pName;//供应商名称
    private Date outDate;//退货时间
    private Long gid;//商品id
    private String gName;//商品名称
    private Double num;//数目
    private Double price;//单价
    private Double totalAmount;//总价

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public void setgName(String gName) {
        this.gName = gName;
    }

    public String getgName() {
        return gName;
    }

    public Long getrPurchaseId() {
        return rPurchaseId;
    }

    public Long getPid() {
        return pid;
    }

    public String getpName() {
        return pName;
    }

    public Date getOutDate() {
        return outDate;
    }

    public Long getGid() {
        return gid;
    }

    public Double getNum() {
        return num;
    }

    public Double getPrice() {
        return price;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setrPurchaseId(Long rPurchaseId) {
        this.rPurchaseId = rPurchaseId;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public void setOutDate(Date outDate) {
        this.outDate = outDate;
    }

    public void setGid(Long gid) {
        this.gid = gid;
    }

    public void setNum(Double num) {
        this.num = num;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
