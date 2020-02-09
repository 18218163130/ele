package com.ele.entity;

import java.io.Serializable;

/**
 * 权限表实体
 * @Author dongwf
 * @Date 2019/10/7
 */
public class Auth implements Serializable {
    private Integer authId;
    private String per; // 权限
    private String desc;

    public Auth() {
    }

    public Auth(Integer authId, String per, String desc) {
        this.authId = authId;
        this.per = per;
        this.desc = desc;
    }

    public Integer getAuthId() {
        return authId;
    }

    public void setAuthId(Integer authId) {
        this.authId = authId;
    }

    public String getPer() {
        return per;
    }

    public void setPer(String per) {
        this.per = per;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
