package com.tdh.domain;

public class User {
    /**
     * 用户id
     */
    private String yhid;

    /**
     * 用户姓名
     */
    private String yhxm;

    /**
     * 用户口令
     */
    private String yhkl;

    /**
     * 用户性别
     */
    private String yhxb;

    /**
     * 用户部门
     */
    private String yhbm;

    /**
     * 出生日期
     */
    private String csrq;

    /**
     * 登记日期
     */
    private String djrq;

    /**
     * 是否禁用
     */
    private String sfjy;

    /**
     * 排序号
     */
    private Integer pxh;

    /**
     * 头像名称
     */
    private String photoname;

    /**
     * 头像的格式
     */
    private String phototype;

    /**
     * 头像的二进制文件
     */
    private byte[] photo;

    public User(String yhid, String yhxm, String yhkl, String yhxb, String yhbm, String csrq, String djrq, String sfjy, Integer pxh, String photoname, String phototype) {
        this.yhid = yhid;
        this.yhxm = yhxm;
        this.yhkl = yhkl;
        this.yhxb = yhxb;
        this.yhbm = yhbm;
        this.csrq = csrq;
        this.djrq = djrq;
        this.sfjy = sfjy;
        this.pxh = pxh;
        this.photoname = photoname;
        this.phototype = phototype;
    }

    public User(String yhid, String yhxm, String yhkl, String yhxb, String yhbm, String csrq, String djrq, String sfjy, Integer pxh, String photoname, String phototype, byte[] photo) {
        this.yhid = yhid;
        this.yhxm = yhxm;
        this.yhkl = yhkl;
        this.yhxb = yhxb;
        this.yhbm = yhbm;
        this.csrq = csrq;
        this.djrq = djrq;
        this.sfjy = sfjy;
        this.pxh = pxh;
        this.photoname = photoname;
        this.phototype = phototype;
        this.photo = photo;
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

    public String getPhotoname() {
        return photoname;
    }

    public void setPhotoname(String photoname) {
        this.photoname = photoname;
    }

    public String getPhototype() {
        return phototype;
    }

    public void setPhototype(String phototype) {
        this.phototype = phototype;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }
}