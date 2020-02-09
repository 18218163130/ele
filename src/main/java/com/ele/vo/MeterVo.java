package com.ele.vo;

import com.ele.entity.Meter;

/**
 * 电表信息封装类
 * @Author dongwf
 * @Date 2019/10/17
 */
public class MeterVo extends Meter {
    private Integer page;
    private Integer limit;
    private Integer[] ids;
    private Float newType;
    private String newUserId;

    public String getNewUserId() {
        return newUserId;
    }

    public void setNewUserId(String newUserId) {
        this.newUserId = newUserId;
    }

    public Float getNewType() {
        return newType;
    }

    public void setNewType(Float newType) {
        this.newType = newType;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer[] getIds() {
        return ids;
    }

    public void setIds(Integer[] ids) {
        this.ids = ids;
    }
}
