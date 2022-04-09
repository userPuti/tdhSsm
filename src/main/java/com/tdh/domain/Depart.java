package com.tdh.domain;

public class Depart {
    private String bmdm;

    private String dwdm;

    private String bmid;

    private String bmmc;

    private String sfjy;

    private Integer pxh;

    public Depart(String bmdm, String dwdm, String bmid, String bmmc, String sfjy, Integer pxh) {
        this.bmdm = bmdm;
        this.dwdm = dwdm;
        this.bmid = bmid;
        this.bmmc = bmmc;
        this.sfjy = sfjy;
        this.pxh = pxh;
    }

    public Depart() {
        super();
    }

    public String getBmdm() {
        return bmdm;
    }

    public void setBmdm(String bmdm) {
        this.bmdm = bmdm;
    }

    public String getDwdm() {
        return dwdm;
    }

    public void setDwdm(String dwdm) {
        this.dwdm = dwdm;
    }

    public String getBmid() {
        return bmid;
    }

    public void setBmid(String bmid) {
        this.bmid = bmid;
    }

    public String getBmmc() {
        return bmmc;
    }

    public void setBmmc(String bmmc) {
        this.bmmc = bmmc;
    }

    public String getSfjy() {
        return sfjy;
    }

    public void setSfjy(String sfjy) {
        this.sfjy = sfjy;
    }

    public Integer getPxh() {
        return pxh;
    }

    public void setPxh(Integer pxh) {
        this.pxh = pxh;
    }
}