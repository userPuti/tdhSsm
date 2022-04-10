package com.tdh.domain;

import org.springframework.stereotype.Component;

public class User {
    private String yhid;

    private String yhxm;

    private String yhkl;

    private String yhxb;

    private String yhbm;

    private String csrq;

    private String djrq;

    private String sfjy;

    private Integer pxh;

    public User(String yhid, String yhxm, String yhkl, String yhxb, String yhbm, String csrq, String djrq, String sfjy, Integer pxh) {
        this.yhid = yhid;
        this.yhxm = yhxm;
        this.yhkl = yhkl;
        this.yhxb = yhxb;
        this.yhbm = yhbm;
        this.csrq = csrq;
        this.djrq = djrq;
        this.sfjy = sfjy;
        this.pxh = pxh;
    }

    public User() {
        super();
    }

    public String getYhid() {
        return yhid;
    }

    public void setYhid(String yhid) {
        this.yhid = yhid;
    }

    public String getYhxm() {
        return yhxm;
    }

    public void setYhxm(String yhxm) {
        this.yhxm = yhxm;
    }

    public String getYhkl() {
        return yhkl;
    }

    public void setYhkl(String yhkl) {
        this.yhkl = yhkl;
    }

    public String getYhxb() {
        return yhxb;
    }

    public void setYhxb(String yhxb) {
        this.yhxb = yhxb;
    }

    public String getYhbm() {
        return yhbm;
    }

    public void setYhbm(String yhbm) {
        this.yhbm = yhbm;
    }

    public String getCsrq() {
        return csrq;
    }

    public void setCsrq(String csrq) {
        this.csrq = csrq;
    }

    public String getDjrq() {
        return djrq;
    }

    public void setDjrq(String djrq) {
        this.djrq = djrq;
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