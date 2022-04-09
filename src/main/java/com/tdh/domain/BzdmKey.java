package com.tdh.domain;

public class BzdmKey {
    private String kind;

    private String code;

    public BzdmKey(String kind, String code) {
        this.kind = kind;
        this.code = code;
    }

    public BzdmKey() {
        super();
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}