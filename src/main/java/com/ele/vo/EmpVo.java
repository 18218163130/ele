package com.ele.vo;

import com.ele.entity.Emp;

/**
 * 员工信息包装类
 * @Author dongwf
 * @Date 2019/10/11
 */
public class EmpVo extends Emp {
    // 分页属性
    private Integer page;
    private Integer limit;
    private Integer[] ids;
    private String newPwd; // 修改密码时接收新密码
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNewPwd() {
        return newPwd;
    }

    public void setNewPwd(String newPwd) {
        this.newPwd = newPwd;
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
