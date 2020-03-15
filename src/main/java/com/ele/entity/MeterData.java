package com.ele.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 电表数据
 *
 * @Author dongwf
 * @Date 2019/10/7
 */
public class MeterData implements Serializable {
    private Integer dataId; // 数据编号
    private String userId; // 客户Id
    private Integer meterId; // 电表编号
    private float consume; // 本月消耗电力度数
    private float displayNumber; // 电表显示数
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date recordDate; // 获取数据时间
    @DateTimeFormat(pattern = "yyyy-MM")
    @JsonFormat(pattern = "yyyy-MM", timezone = "GMT+8")
    private Date recordMonth; // 获取数据时间
    private Integer state; // 状态
    private String verifyPerson; //审核人
    private float type; // 备注

    private Integer hasForm; // 是否已经生成电费单

    public Date getRecordMonth() {
        return recordMonth;
    }

    public void setRecordMonth(Date recordMonth) {
        this.recordMonth = recordMonth;
    }

    public Integer getHasForm() {
        return hasForm;
    }

    public void setHasForm(Integer hasForm) {
        this.hasForm = hasForm;
    }

    public Integer getDataId() {
        return dataId;
    }

    public void setDataId(Integer dataId) {
        this.dataId = dataId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getMeterId() {
        return meterId;
    }

    public void setMeterId(Integer meterId) {
        this.meterId = meterId;
    }

    public float getConsume() {
        return consume;
    }

    public void setConsume(float consume) {
        this.consume = consume;
    }

    public float getDisplayNumber() {
        return displayNumber;
    }

    public void setDisplayNumber(float displayNumber) {
        this.displayNumber = displayNumber;
    }

    public Date getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getVerifyPerson() {
        return verifyPerson;
    }

    public void setVerifyPerson(String verifyPerson) {
        this.verifyPerson = verifyPerson;
    }


    public float getType() {
        return type;
    }

    public void setType(float type) {
        this.type = type;
    }
}