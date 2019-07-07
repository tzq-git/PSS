package edu.pss.bean;

import java.io.Serializable;
import java.util.Date;

public class Purchase implements Serializable{

    private  Long purchaseId;//购入ID
    private Long pid;//供应商ID
    private  String pName;//供应商名称
    private Date buyDate;//购买日期
    private Long gid;//商品id
    private String gName;//商品名称
    private Double num;//数目
    private Double price;//单价
    private Double totalAmount;//总价

    private static final long serialVersionUID = 412719614431102968L;

    public Purchase(){
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public void setgName(String gName) {
        this.gName = gName;
    }

    public String getgName() {
        return gName;
    }

    public Long getPurchaseId() {
        return purchaseId;
    }

    public Long getPid() {
        return pid;
    }

    public String getpName() {
        return pName;
    }

    public Date getBuyDate() {
        return buyDate;
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

    public void setPurchaseId(Long purchaseId) {
        this.purchaseId = purchaseId;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public void setBuyDate(Date buyDate) {
        this.buyDate = buyDate;
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
