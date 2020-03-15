package com.ele.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 设备信息
 *
 * @Author dongwf
 * @Date 2019/12/19
 */
public class Facility implements Serializable {

    private Integer facId;
    private String facName;
    private Float facPrice;
    private Float facTotal;
    private String facFactory;
    private String facCode;
    private Integer facType;
    private String facTypeName;


    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date facDate;
    private String facDesc;

    public Integer getFacType() {
        return facType;
    }

    public void setFacType(Integer facType) {
        this.facType = facType;
    }

    public String getFacTypeName() {
        return facTypeName;
    }

    public void setFacTypeName(String facTypeName) {
        this.facTypeName = facTypeName;
    }

    public Integer getFacId() {
        return facId;
    }

    public void setFacId(Integer facId) {
        this.facId = facId;
    }

    public String getFacName() {
        return facName;
    }

    public void setFacName(String facName) {
        this.facName = facName;
    }


    public Float getFacPrice() {
        return facPrice;
    }

    public void setFacPrice(Float facPrice) {
        this.facPrice = facPrice;
    }

    public Float getFacTotal() {
        return facTotal;
    }

    public void setFacTotal(Float facTotal) {
        this.facTotal = facTotal;
    }

    public String getFacFactory() {
        return facFactory;
    }

    public void setFacFactory(String facFactory) {
        this.facFactory = facFactory;
    }

    public String getFacCode() {
        return facCode;
    }

    public void setFacCode(String facCode) {
        this.facCode = facCode;
    }

    public Date getFacDate() {
        return facDate;
    }

    public void setFacDate(Date facDate) {
        this.facDate = facDate;
    }

    public String getFacDesc() {
        return facDesc;
    }

    public void setFacDesc(String facDesc) {
        this.facDesc = facDesc;
    }
}
