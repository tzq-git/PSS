package edu.pss.bean;

import java.io.Serializable;

public class Customer implements Serializable{

    private static final long serialVersionUID = -8622065375139740762L;

    public Customer(){

    }

    private Long cid;//客户id
    private String cName;//客户名
    private String companySN;//客户简称
    private String companyAN;//客户全称
    private Long sid;//负责销售员id
    private String sCName;//新加入的参照名，客户中文名
    private String rPersonNickName;//负责人简称
    private String rPersonName;//负责人名称
    private String tel;//电话
    private String phone;//手机
    private String fax;//传真
    private String address;//地址
    private String receiveAddress;//收货地址
    private Long isDeleted;//是否可删

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getsCName() {
        return sCName;
    }

    public void setsCName(String sCName) {
        this.sCName = sCName;
    }

    public Long getCid() {
        return cid;
    }

    public String getcName() {
        return cName;
    }

    public Long getSid() {
        return sid;
    }

    public String getCompanySN() {
        return companySN;
    }

    public String getCompanyAN() {
        return companyAN;
    }

    public String getrPersonNickName() {
        return rPersonNickName;
    }

    public String getrPersonName() {
        return rPersonName;
    }

    public String getTel() {
        return tel;
    }

    public String getPhone() {
        return phone;
    }

    public String getFax() {
        return fax;
    }

    public String getAddress() {
        return address;
    }

    public String getReceiveAddress() {
        return receiveAddress;
    }

    public Long getIsDeleted() {
        return isDeleted;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public void setSid(Long sid) {
        this.sid = sid;
    }

    public void setCompanySN(String companySN) {
        this.companySN = companySN;
    }

    public void setCompanyAN(String companyAN) {
        this.companyAN = companyAN;
    }

    public void setrPersonNickName(String rPersonNickName) {
        this.rPersonNickName = rPersonNickName;
    }

    public void setrPersonName(String rPersonName) {
        this.rPersonName = rPersonName;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setReceiveAddress(String receiveAddress) {
        this.receiveAddress = receiveAddress;
    }

    public void setIsDeleted(Long isDeleted) {
        this.isDeleted = isDeleted;
    }
}
