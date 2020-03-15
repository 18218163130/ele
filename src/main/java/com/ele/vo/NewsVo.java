package com.ele.vo;

import com.ele.entity.News;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 系统公告封装类
 *
 * @Author dongwf
 * @Date 2019/10/14
 */
public class NewsVo extends News {
    private Integer page;
    private Integer limit;
    private Integer[] ids;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") // 设定日期时间格式
    private Date startTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
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
