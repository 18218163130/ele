package com.ele.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 电线实体
 *
 * @Author dongwf
 * @Date 2019/12/18
 */
public class Wire implements Serializable {

    private Integer wireId; // 电线id
    private String wireName; // 客户ID
    private String wireType; // 电线型号
    private float wireLength; // 电线长度
    private float wirePrice; // 单价
    private String factory; // 电线生产厂家
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateTime; // 使用时间
    private float totalPrice; // 电线总价
    private Integer num; // 数量

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getWireId() {
        return wireId;
    }

    public void setWireId(Integer wireId) {
        this.wireId = wireId;
    }

    public String getWireName() {
        return wireName;
    }

    public void setWireName(String wireName) {
        this.wireName = wireName;
    }

    public String getWireType() {
        return wireType;
    }

    public void setWireType(String wireType) {
        this.wireType = wireType;
    }

    public float getWireLength() {
        return wireLength;
    }

    public void setWireLength(float wireLength) {
        this.wireLength = wireLength;
    }

    public float getWirePrice() {
        return wirePrice;
    }

    public void setWirePrice(float wirePrice) {
        this.wirePrice = wirePrice;
    }

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }
}
