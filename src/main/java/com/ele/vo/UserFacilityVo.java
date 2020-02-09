package com.ele.vo;

import com.ele.entity.UserFacility;

/**
 * @Author dongwf
 * @Date 2019/12/28
 */
public class UserFacilityVo extends UserFacility {

    private Integer page; // 第几页
    private Integer limit; // 每页条数
    private Integer[] ids;

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
