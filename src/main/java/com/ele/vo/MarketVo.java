package com.ele.vo;

import com.ele.entity.Market;

/**
 * @Author dongwf
 * @Date 2019/11/1
 */
public class MarketVo extends Market {
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
