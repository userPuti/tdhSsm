package com.tdh.domain;

public class Bzdm extends BzdmKey {
    private String bt;

    private String mc;

    private String sfjy;

    private Integer pxh;

    public Bzdm(String kind, String code, String bt, String mc, String sfjy, Integer pxh) {
        super(kind, code);
        this.bt = bt;
        this.mc = mc;
        this.sfjy = sfjy;
        this.pxh = pxh;
    }

    public Bzdm() {
        super();
    }

    public String getBt() {
        return bt;
    }

    public void setBt(String bt) {
        this.bt = bt;
    }

    public String getMc() {
        return mc;
    }

    public void setMc(String mc) {
        this.mc = mc;
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