package com.ele.vo;

import com.ele.entity.Index;

/**
 * 指标包装类
 * @Author dongwf
 * @Date 2019/10/15
 */
public class IndexVo extends Index {

    private Integer page;
    private Integer limit;
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
