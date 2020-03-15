package com.ele.vo;

/**
 * 销售额
 *
 * @Auther: dongwf
 * @Date: 2020/3/8 22:54
 * @Description:
 */
public class AnalyFacilityVo {
    /**
     * 电表，电线，变压器
     */
    public Object[] dianxian;
    public Object[] dianbiao;
    public Object[] bianyaqi;
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
    public AnalyFacilityVo(int size) {
        monthsName = new String[size];
        totalsValue = new Object[size];
    }

    public AnalyFacilityVo(int size, boolean flag) {
        monthsName = new String[size];
        totalsValue = new Object[size];
        dianxian = new Object[size];
        dianbiao = new Object[size];
        bianyaqi = new Object[size];
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

    public Object[] getDianxian() {
        return dianxian;
    }

    public void setDianxian(Object[] dianxian) {
        this.dianxian = dianxian;
    }

    public Object[] getDianbiao() {
        return dianbiao;
    }

    public void setDianbiao(Object[] dianbiao) {
        this.dianbiao = dianbiao;
    }

    public Object[] getBianyaqi() {
        return bianyaqi;
    }

    public void setBianyaqi(Object[] bianyaqi) {
        this.bianyaqi = bianyaqi;
    }
}
