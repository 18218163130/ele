package com.ele.vo;

import com.ele.entity.Activity;

/**
 * @Author dongwf
 * @Date 2019/11/22
 */
public class ActivityVo extends Activity {
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
