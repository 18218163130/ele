package com.ele.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 营销表实体
 *
 * @Author dongwf
 * @Date 2019/10/7
 */
public class Market implements Serializable {
    private Integer marketId; // 营销编号
    private String marketName; // 营销名称
    private String userId; // 客户名称
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date marketDate; // 日期
    private Integer state; // 状态
    private String remark; // 备注

    public Integer getMarketId() {
        return marketId;
    }

    public void setMarketId(Integer marketId) {
        this.marketId = marketId;
    }

    public String getMarketName() {
        return marketName;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getMarketDate() {
        return marketDate;
    }

    public void setMarketDate(Date marketDate) {
        this.marketDate = marketDate;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
