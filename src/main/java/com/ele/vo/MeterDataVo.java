package com.ele.vo;

import com.ele.entity.MeterData;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 电表数据包装类
 * @Author dongwf
 * @Date 2019/10/17
 */
public class MeterDataVo extends MeterData {
    private Integer page;
    private Integer limit;
    private Integer[] ids;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endTime;
//    @DateTimeFormat(pattern = "yyyy-MM")
    private String yearmonth; // 根据月份查询
    private String yearmonth2;

    private float price;

    private float jtPrice;
    private float syPrice;
    private float gyPrice;

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getJtPrice() {
        return jtPrice;
    }

    public void setJtPrice(float jtPrice) {
        this.jtPrice = jtPrice;
    }

    public float getSyPrice() {
        return syPrice;
    }

    public void setSyPrice(float syPrice) {
        this.syPrice = syPrice;
    }

    public float getGyPrice() {
        return gyPrice;
    }

    public void setGyPrice(float gyPrice) {
        this.gyPrice = gyPrice;
    }

    public String getYearmonth2() {
        return yearmonth2;
    }

    public void setYearmonth2(String yearmonth2) {
        this.yearmonth2 = yearmonth2;
    }

    public String getYearmonth() {
        return yearmonth;
    }

    public void setYearmonth(String yearmonth) {
        this.yearmonth = yearmonth;
    }

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
