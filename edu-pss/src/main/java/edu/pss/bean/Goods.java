package edu.pss.bean;

import java.io.Serializable;
import java.util.Date;

public class Goods implements Serializable{


    private Long gid;//商品ID
    private String gName;//商品名
    private Double saveStock;//安全存量
    private Double nowStock;//现有存量
    private Double buyPrice;//购入价格
    private Double price;//销售价
    private Date lastBuyTime;//最后购入时间
    private Date lastSaleTime;//最后销售时间

    private static final long serialVersionUID = -4538143538148431298L;

    public Goods(){}

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getGid() {
        return gid;
    }

    public String getgName() {
        return gName;
    }

    public Double getSaveStock() {
        return saveStock;
    }

    public Double getNowStock() {
        return nowStock;
    }

    public Double getBuyPrice() {
        return buyPrice;
    }

    public Double getPrice() {
        return price;
    }

    public Date getLastBuyTime() {
        return lastBuyTime;
    }

    public Date getLastSaleTime() {
        return lastSaleTime;
    }

    public void setGid(Long gid) {
        this.gid = gid;
    }

    public void setgName(String gName) {
        this.gName = gName;
    }

    public void setSaveStock(Double saveStock) {
        this.saveStock = saveStock;
    }

    public void setNowStock(Double nowStock) {
        this.nowStock = nowStock;
    }

    public void setBuyPrice(Double buyPrice) {
        this.buyPrice = buyPrice;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setLastBuyTime(Date lastBuyTime) {
        this.lastBuyTime = lastBuyTime;
    }

    public void setLastSaleTime(Date lastSaleTime) {
        this.lastSaleTime = lastSaleTime;
    }
}
