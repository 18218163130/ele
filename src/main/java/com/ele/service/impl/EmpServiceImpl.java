package com.ele.service.impl;

import com.ele.config.SysConstast;
import com.ele.entity.Emp;
import com.ele.mapper.EmpMapper;
import com.ele.service.EmpService;
import com.ele.utils.DataGridView;
import com.ele.vo.EmpVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 公司员工业务操作的接口实现类
 *
 * @Author dongwf
 * @Date 2019/10/7
 */
@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;

    @Override
    public Emp login(Emp emp) {
        Emp resultEmp = empMapper.login(emp);
        return resultEmp;
    }

    @Override
    public Emp findByEmpCode(String empCode) {
        return empMapper.findByEmpCode(empCode);
    }

    @Override
    public DataGridView loadAllEmp(EmpVo empVo) {
        // 分页
        Page page = PageHelper.startPage(empVo.getPage(), empVo.getLimit());
        List<Emp> emps = empMapper.queryAllEmp(empVo);
        return new DataGridView(page.getTotal(), emps);
    }

    @Transactional
    @Override
    public void register(EmpVo empVo) {
        empMapper.addEmp(empVo);
    }

    @Transactional
    @Override
    public void updateEmp(EmpVo empVo) {
        empMapper.updateEmp(empVo);
    }

    @Transactional
    @Override
    public void updateEmpInfo(EmpVo empVo) {
        empMapper.updateEmpInfo(empVo);
    }


    @Transactional
    @Override
    public void resetEmpPwd(Integer empId) {
        String restPwd = new Md5Hash(SysConstast.EMP_DEFAULT_PWD).toString();
        empMapper.updateEmpPwd(empId, restPwd);
    }

    @Transactional
    @Override
    public void deleteEmp(Integer empId) {
        empMapper.deleteByEmpId(empId);
    }

    @Override
    @Transactional
    public Emp checkEmpPwd(String empCode, String pwd) {
        return empMapper.checkEmpCodeAndPwd(empCode, pwd);
    }

    @Transactional
    @Override
    public void updateEmpPwd(String empCode, String newPwd) {
        empMapper.updateNewPwd(empCode, newPwd);
    }
}
