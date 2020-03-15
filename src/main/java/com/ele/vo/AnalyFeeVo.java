package com.ele.vo;

/**
 * 销售额
 *
 * @Auther: dongwf
 * @Date: 2020/3/8 22:54
 * @Description:
 */
public class AnalyFeeVo {
    /**
     * 封装数据库统计字段
     */
    private String months;
    private Object totals;
    /**
     * 返回页面的json数据字段
     */
    private String[] monthsName;
    private Object[] totalsValue;
    /**
     * 分别统计家庭、工业、商业类型
     */
    private Object[] jts;
    private Object[] gys;
    private Object[] sys;
    public AnalyFeeVo(int size) {
        monthsName = new String[size];
        totalsValue = new Object[size];
    }

    public AnalyFeeVo(int size, boolean flag) {
        monthsName = new String[size];
        totalsValue = new Object[size];
        jts = new Object[size];
        gys = new Object[size];
        sys = new Object[size];
    }

    public String getMonths() {
        return months;
    }

    public void setMonths(String months) {
        this.months = months;
    }

    public Object getTotals() {
        return totals;
    }

    public void setTotals(Object totals) {
        this.totals = totals;
    }

    public String[] getMonthsName() {
        return monthsName;
    }

    public void setMonthsName(String[] monthsName) {
        this.monthsName = monthsName;
    }

    public Object[] getTotalsValue() {
        return totalsValue;
    }

    public void setTotalsValue(Object[] totalsValue) {
        this.totalsValue = totalsValue;
    }

    public Object[] getJts() {
        return jts;
    }

    public void setJts(Object[] jts) {
        this.jts = jts;
    }

    public Object[] getGys() {
        return gys;
    }

    public void setGys(Object[] gys) {
        this.gys = gys;
    }

    public Object[] getSys() {
        return sys;
    }

    public void setSys(Object[] sys) {
        this.sys = sys;
    }
}
