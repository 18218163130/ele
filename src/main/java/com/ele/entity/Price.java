package com.ele.entity;/**
 * @Auther: dongwf
 * @Date: 2020/2/12 23:01
 * @Description:
 */

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 电费单价
 * @Author dongwf
 * @Date 2020/2/12
 */
public class Price implements Serializable {

    private Integer id;
    private Float gyPrice;
    private Float jtPrice;
    private Float syPrice;
    @JsonFormat(pattern = "yyyy-MM",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM")
    private Date yearMonth;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getGyPrice() {
        return gyPrice;
    }

    public void setGyPrice(Float gyPrice) {
        this.gyPrice = gyPrice;
    }

    public Float getJtPrice() {
        return jtPrice;
    }

    public void setJtPrice(Float jtPrice) {
        this.jtPrice = jtPrice;
    }

    public Float getSyPrice() {
        return syPrice;
    }

    public void setSyPrice(Float syPrice) {
        this.syPrice = syPrice;
    }

    public Date getYearMonth() {
        return yearMonth;
    }

    public void setYearMonth(Date yearMonth) {
        this.yearMonth = yearMonth;
    }
}
