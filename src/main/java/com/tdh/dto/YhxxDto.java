package com.tdh.dto;

import org.springframework.stereotype.Component;

/**
 * @author Puti
 * @date 2022/4/2 17:56
 */
public class YhxxDto {

    private int start;

    private int limit;

    private String yhid;

    private String yhbm;

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getYhid() {
        return yhid;
    }

    public void setYhid(String yhid) {
        this.yhid = yhid;
    }

    public String getYhbm() {
        return yhbm;
    }

    public void setYhbm(String yhbm) {
        this.yhbm = yhbm;
    }

    @Override
    public String toString() {
        return "YhxxDto{" +
                "start=" + start +
                ", limit=" + limit +
                ", yhid='" + yhid + '\'' +
                ", yhbm='" + yhbm + '\'' +
                '}';
    }
}
