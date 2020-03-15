package com.ele.vo;

import com.ele.entity.Facility;

/**
 * 电力设备包装类
 *
 * @Author dongwf
 * @Date 2019/12/20
 */
public class FacilityVo extends Facility {

    private Integer page;
    private Integer limit;
    private String[] ids;

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
