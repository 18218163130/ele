package com.ele.vo;/**
 * @Auther: dongwf
 * @Date: 2020/2/12 23:08
 * @Description:
 */

import com.ele.entity.Price;

/**
 * @Author dongwf
 * @Date 2020/2/12
 */
public class PriceVo extends Price {

    // 分页属性
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
