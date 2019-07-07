package edu.pss.bean;

import java.io.Serializable;

public class Authority implements Serializable {
    private static final long serialVersionUID = 5693742850545768128L;
    private Long userId;
    private String userName;

    //用户权限描述
    private String description = "无";

    /*以下字段均为权限字段，1代表拥有权限，0代表无权限*/

    //是否拥有管理销售员资料权限
    private Long haveSaleMan = 0L;
    //是否拥有管理商品资料权限
    private Long haveGoods = 0L;
    //是否拥有管理客户资料权限
    private Long haveCustomer = 0L;
    //是否拥有供应商资料管理权限
    private Long haveSupplier = 0L;
    //是否拥有采购入库单管理权限
    private Long havePurchaseIn = 0L;
    //是否拥有采购出库单管理权限
    private Long havePurchaseOut = 0L;
    //是否拥有销售出库单管理权限
    private Long haveSaleOut = 0L;
    //是否拥有销售退货单管理权限
    private Long haveSaleReturn = 0L;

    public Authority(){}

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getHaveSaleMan() {
        return haveSaleMan;
    }

    public void setHaveSaleMan(Long haveSaleMan) {
        this.haveSaleMan = haveSaleMan;
    }

    public Long getHaveGoods() {
        return haveGoods;
    }

    public void setHaveGoods(Long haveGoods) {
        this.haveGoods = haveGoods;
    }

    public Long getHaveCustomer() {
        return haveCustomer;
    }

    public void setHaveCustomer(Long haveCustomer) {
        this.haveCustomer = haveCustomer;
    }

    public Long getHaveSupplier() {
        return haveSupplier;
    }

    public void setHaveSupplier(Long haveSupplier) {
        this.haveSupplier = haveSupplier;
    }

    public Long getHavePurchaseIn() {
        return havePurchaseIn;
    }

    public void setHavePurchaseIn(Long havePurchaseIn) {
        this.havePurchaseIn = havePurchaseIn;
    }

    public Long getHavePurchaseOut() {
        return havePurchaseOut;
    }

    public void setHavePurchaseOut(Long havePurchaseOut) {
        this.havePurchaseOut = havePurchaseOut;
    }

    public Long getHaveSaleOut() {
        return haveSaleOut;
    }

    public void setHaveSaleOut(Long haveSaleOut) {
        this.haveSaleOut = haveSaleOut;
    }

    public Long getHaveSaleReturn() {
        return haveSaleReturn;
    }

    public void setHaveSaleReturn(Long haveSaleReturn) {
        this.haveSaleReturn = haveSaleReturn;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
