package com.ele.vo;

/**
 * 封装分页每个月消耗电量
 *
 * @Author dongwf
 * @Date 2019/10/19
 */
public class AnalyMonthConsumeVo {
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
     * 图表二使用的字段
     */

    private Object[] jts; // 家庭用电
    private Object[] sys; // 商业用电
    private Object[] gys; // 工业用电
    public AnalyMonthConsumeVo() {
    }
    public AnalyMonthConsumeVo(Integer size) {
        monthsName = new String[size];
        totalsValue = new Object[size];
    }

    public AnalyMonthConsumeVo(Integer size, boolean flag) {
        monthsName = new String[size];
        jts = new Object[size];
        sys = new Object[size];
        gys = new Object[size];

    }

    public Object[] getJts() {
        return jts;
    }

    public Object[] getSys() {
        return sys;
    }

    public Object[] getGys() {
        return gys;
    }

    public String[] getMonthsName() {
        return monthsName;
    }


    public Object[] getTotalsValue() {
        return totalsValue;
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
}
