package com.ele.service;

import com.ele.entity.Emp;
import com.ele.utils.DataGridView;
import com.ele.vo.EmpVo;

/**
 * 公司员工业务层接口
 *
 * @Author dongwf
 * @Date 2019/10/7
 */
public interface EmpService {

    /**
     * 公司员工登录
     *
     * @param emp
     * @return
     */
    Emp login(Emp emp);

    /**
     * 根据员工编号查询员工信息
     *
     * @param empCode
     * @return
     */
    Emp findByEmpCode(String empCode);

    /**
     * 加载所有员工列表信息
     *
     * @return
     */
    DataGridView loadAllEmp(EmpVo empVo);

    /**
     * 注册员工
     *
     * @param empVo
     */
    void register(EmpVo empVo);

    /**
     * 修改员工信息
     *
     * @param empVo
     */
    void updateEmp(EmpVo empVo);

    /**
     * 员工修改自己的信息
     *
     * @param empVo
     */
    void updateEmpInfo(EmpVo empVo);

    /**
     * 重置员密码
     *
     * @param empId
     */
    void resetEmpPwd(Integer empId);

    /**
     * 删除员工信息
     *
     * @param empId
     */
    void deleteEmp(Integer empId);

    /**
     * 检查员工账号和密码是否正确
     *
     * @param empCode
     * @param pwd
     */
    Emp checkEmpPwd(String empCode, String pwd);

    /**
     * 更改员工密码
     *
     * @param empCode
     * @param newPwd
     */
    void updateEmpPwd(String empCode, String newPwd);
}
