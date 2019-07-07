package edu.pss.bean;

import java.io.Serializable;

public class SalePerson implements Serializable {

    private static final long serialVersionUID = 8559015864738320113L;
    private Long sid;//销售员id
    private String sCName;//销售员中文名
    private String sEName;//销售员英文名
    private String rCustomer;//负责客户
    private String tel;//电话
    private String phone;//手机
    private String email;//邮箱
    private String address;//地址
    private Long isDeleted;//是否可删除


    public SalePerson(){

    }
    public Long getIsDeleted(){
        return isDeleted;
    }

    public Long getSid() {
        return sid;
    }

    public String getsCName() {
        return sCName;
    }

    public String getsEName() {
        return sEName;
    }

    public String getrCustomer() {
        return rCustomer;
    }

    public String getTel() {
        return tel;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public void setSid(Long sid) {
        this.sid = sid;
    }

    public void setsCName(String sCName) {
        this.sCName = sCName;
    }

    public void setsEName(String sEName) {
        this.sEName = sEName;
    }

    public void setrCustomer(String rCustomer) {
        this.rCustomer = rCustomer;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setIsDeleted(Long isDeleted){ this.isDeleted=isDeleted;}
}
