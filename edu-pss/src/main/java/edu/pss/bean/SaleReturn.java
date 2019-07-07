package edu.pss.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class SaleReturn implements Serializable {

    private Long returnId;//退货id
    private Long orderId;//订单号
    private String cName;//客户名称
    private Date returnDate;//退货日期
    private String salesMan;//销售员
    private String price;//价格
    private Long returnDetailId;//退货明细
    private static final long serialVersionUID = -8962227139533666986L;

    public SaleReturn(){

    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getReturnId() {
        return returnId;
    }

    public void setReturnId(Long returnId) {
        this.returnId = returnId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public String getSalesMan() {
        return salesMan;
    }

    public void setSalesMan(String salesMan) {
        this.salesMan = salesMan;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Long getReturnDetailId() {
        return returnDetailId;
    }

    public void setReturnDetailId(Long returnDetailId) {
        this.returnDetailId = returnDetailId;
    }
}
