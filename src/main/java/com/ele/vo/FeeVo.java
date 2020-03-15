package com.ele.vo;

import com.ele.entity.Fee;

/**
 * 电费包装类
 *
 * @Author dongwf
 * @Date 2019/10/23
 */
public class FeeVo extends Fee {
    private Integer page;
    private Integer limit;
    private String yearmonth;
    private String[] ids;

    public String getYearmonth() {
        return yearmonth;
    }

    public void setYearmonth(String yearmonth) {
        this.yearmonth = yearmonth;
    }

    public String[] getIds() {
        return ids;
    }

    public void setIds(String[] ids) {
        this.ids = ids;
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


}
