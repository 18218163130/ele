package com.ele.vo;

import com.ele.entity.Auth;
import com.ele.entity.Emp;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 封装员工与授权
 * @Author dongwf
 * @Date 2019/10/9
 */
public class EmpAuthVo {
    private Emp emp;
    private List<String> auths = new ArrayList<String>();

    public EmpAuthVo() {
    }

    public EmpAuthVo(Emp emp, List<String> auths) {
        this.emp = emp;
        this.auths = auths;
    }

    public Emp getEmp() {
        return emp;
    }

    public void setEmp(Emp emp) {
        this.emp = emp;
    }

    public List<String> getAuths() {
        return auths;
    }

    public void setAuths(List<String> auths) {
        this.auths = auths;
    }
}
