package edu.pss.bean;

import java.io.Serializable;

public class Provider implements Serializable {
    private static final long serialVersionUID = 121672567336171600L;

    public Provider(){};

    private Long pid;//供应商ID
    private String pName;//供应商名称
    private String legalRepresentative;//法人
    private String contactPerson;//负责人
    private String contactTel;//负责人电话
    private String companyFax;//公司传真
    private String companyTel;//公司联系电话
    private String providerAddress;//供应商地址
    private String notes;//备注

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getPid() {
        return pid;
    }

    public String getpName() {
        return pName;
    }

    public String getLegalRepresentative() {
        return legalRepresentative;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public String getContactTel() {
        return contactTel;
    }

    public String getCompanyFax() {
        return companyFax;
    }

    public String getCompanyTel() {
        return companyTel;
    }

    public String getProviderAddress() {
        return providerAddress;
    }

    public String getNotes() {
        return notes;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public void setLegalRepresentative(String legalRepresentative) {
        this.legalRepresentative = legalRepresentative;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public void setContactTel(String contactTel) {
        this.contactTel = contactTel;
    }

    public void setCompanyFax(String companyFax) {
        this.companyFax = companyFax;
    }

    public void setCompanyTel(String companyTel) {
        this.companyTel = companyTel;
    }

    public void setProviderAddress(String providerAddress) {
        this.providerAddress = providerAddress;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
