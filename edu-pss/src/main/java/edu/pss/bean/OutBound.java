package edu.pss.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class OutBound implements Serializable {

    private static List<String > tableName;
    private Long orderId;//订单号
    private String cName;//客户名
    private String gName;//商品名
    private Date dealDate;//交易日期
    private Date outDate;//出库日期
    private String address;//送货地址
    private Double num;//购买数量
    private Double price;//出售价格
    private Double Amount;//总金额
    private Long status;//退货状态
    private String salesMan;//销售员
    private Long saleDetailId;//销售明细
    private static final long serialVersionUID = 6847302121362587938L;

    static {
        tableName = new ArrayList<String >();
        tableName.add("销售单号");
        tableName.add("客户名称");
        tableName.add("交易日期");
        tableName.add("出库日期");
        tableName.add("送货地址");
        tableName.add("总金额");
        tableName.add("订单状态");
        tableName.add("销售员");
        tableName.add("销售明细");
    }

    public OutBound(){

    }


    public void setgName(String gName) {
        this.gName = gName;
    }

    public String getgName() {

        return gName;
    }

    public void setNum(Double num) {
        this.num = num;
    }

    public void setAmount(Double amount) {
        Amount = amount;
    }


    public Double getNum() {
        return num;
    }

    public Double getAmount() {
        return Amount;
    }

    public static List<String> getTableName() {
        return tableName;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
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

    public Date getDealDate() {
        return dealDate;
    }

    public void setDealDate(Date dealDate) {
        this.dealDate = dealDate;
    }

    public Date getOutDate() {
        return outDate;
    }

    public void setOutDate(Date outDate) {
        this.outDate = outDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public String getSalesMan() {
        return salesMan;
    }

    public void setSalesMan(String salesMan) {
        this.salesMan = salesMan;
    }

    public Long getSaleDetailId() {
        return saleDetailId;
    }

    public void setSaleDetailId(Long saleDetailId) {
        this.saleDetailId = saleDetailId;
    }
}
