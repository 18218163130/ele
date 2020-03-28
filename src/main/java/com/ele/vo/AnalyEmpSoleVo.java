package com.ele.vo;

/**
 * @Auther: dongwf
 * @Date: 2020/3/14 20:36
 * @Description:
 */
public class AnalyEmpSoleVo {

    public Object[] values;
    public Float[] prizes;
    private Float totals;
    private String empCode;
    private String empName;
    private String yearMonth;
    private String[] empNames;

    public AnalyEmpSoleVo(int size) {
        empNames = new String[size];
        values = new Object[size];
        prizes = new Float[size];

    }
//
//    public AnalyEmpSoleVo(int size,boolean flag){
//        empNames = new String[size];
//    }

    public String getYearMonth() {
        return yearMonth;
    }

    public void setYearMonth(String yearMonth) {
        this.yearMonth = yearMonth;
    }

    public Float[] getPrizes() {
        return prizes;
    }

    public void setPrizes(Float[] prizes) {
        this.prizes = prizes;
    }

    public String[] getEmpNames() {
        return empNames;
    }

    public void setEmpNames(String[] empNames) {
        this.empNames = empNames;
    }

    public Object[] getValues() {
        return values;
    }

    public void setValues(Object[] values) {
        this.values = values;
    }

    public Float getTotals() {
        return totals;
    }

    public void setTotals(Float totals) {
        this.totals = totals;
    }

    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }
}
