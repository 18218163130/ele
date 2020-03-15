package com.ele.vo;

import com.ele.entity.User;

import java.awt.*;

/**
 * 客户信息包装类
 * @Author dongwf
 * @Date 2019/10/7
 */
public class UserVo extends User {

    private Integer page; // 第几页
    private Integer limit; // 每页条数
    private String[] ids; // 用于接收批量删除传递的客户id

    private String code;
    private String newPwd;

    private String emoCode;
    private String emoName;

    public String getEmoCode() {
        return emoCode;
    }

    public void setEmoCode(String emoCode) {
        this.emoCode = emoCode;
    }

    public String getEmoName() {
        return emoName;
    }

    public void setEmoName(String emoName) {
        this.emoName = emoName;
    }

    public String getNewPwd() {
        return newPwd;
    }

    public void setNewPwd(String newPwd) {
        this.newPwd = newPwd;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public String[] getIds() {
        return ids;
    }

    public void setIds(String[] ids) {
        this.ids = ids;
    }
}
