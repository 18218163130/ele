package com.ele.mapper;

import com.ele.entity.Emp;
import com.ele.vo.AnalyEmpSoleVo;
import com.ele.vo.EmpVo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Author dongwf
 * @Date 2019/10/7
 */

public interface EmpMapper {
    /**
     * 公司员工登录
     *
     * @return
     */
    @Select("select * from emp where empCode=#{empCode} and pwd=#{pwd}")
    Emp login(Emp emp);

    /**
     * 根据员工编号查询员工信息
     *
     * @param empCode
     * @return
     */
    @Select("select * from emp where empCode=#{empCode}")
    Emp findByEmpCode(String empCode);

    /**
     * 查询所有员工信息列表
     *
     * @param empVo
     * @return
     */
    @Select("<script> select * from emp <where>" +
            "<if test='empId != null'> and empId like concat('%',#{empId},'%')</if>" +
            "<if test='empName != null'> and empName like concat('%',#{empName},'%')</if>" +
            "<if test='pwd != null'> and pwd like concat('%',#{pwd},'%')</if>" +
            "<if test='phone != null'> and phone like concat('%',#{phone},'%')</if>" +
            "<if test='email != null'> and email like concat('%',#{email},'%')</if>" +
            "<if test='birthday != null'> and birthday like concat('%',#{birthday},'%')</if>" +
            "<if test='type != null'> and type like concat('%',#{type},'%')</if>" +
            "<if test='empCode != null'> and empCode like concat('%',#{empCode},'%')</if>" +
            "<if test='sex != null'> and sex like concat('%',#{sex},'%')</if>" +
            "<if test='address != null'> and address like concat('%',#{address},'%')</if>" +
            "<if test='description != null'> and description like concat('%',#{description},'%')</if>" +
            "</where></script>")
    List<Emp> queryAllEmp(EmpVo empVo);

    /**
     * 插入新的员工记录
     *
     * @param empVo
     */
    @Insert("insert into emp(empName,pwd,phone,email,birthday,type,empCode,sex,address,description) values(" +
            "#{empName},#{pwd},#{phone},#{email},#{birthday},#{type},#{empCode},#{sex},#{address},#{description})")
    void addEmp(EmpVo empVo);

    /**
     * 修改员工信息记录
     *
     * @param empVo
     */
    @Update("update emp set empName=#{empName},phone=#{phone},email=#{email},birthday=#{birthday},type=#{type}," +
            "sex=#{sex},address=#{address} where empCode=#{empCode}")
    void updateEmp(EmpVo empVo);

    /**
     * 员工修改自己的信息
     *
     * @param empVo
     */
    @Update("update emp set empName=#{empName},phone=#{phone},email=#{email},birthday=#{birthday}," +
            "sex=#{sex},address=#{address},description=#{description} where empCode=#{empCode}")
    void updateEmpInfo(EmpVo empVo);

    /**
     * 重置员工密码
     *
     * @param empId
     * @param restPwd
     */
    @Update("update emp set pwd=#{pwd} where empId=#{empId}")
    void updateEmpPwd(@Param("empId") Integer empId, @Param("pwd") String restPwd);

    /**
     * 根据员工Id删除员工记录
     *
     * @param empId
     */
    @Delete("delete from emp where empId=#{empId}")
    void deleteByEmpId(@Param("empId") Integer empId);

    /**
     * 检查员工账号和密码
     */
    @Select("select * from emp where empCode=#{empCode} and pwd=#{pwd}")
    Emp checkEmpCodeAndPwd(@Param("empCode") String empCode, @Param("pwd") String pwd);

    /**
     * 更改员工密码
     *
     * @param empCode
     * @param newPwd
     */
    @Update("update emp set pwd=#{newPwd} where empCode=#{empCode}")
    void updateNewPwd(@Param("empCode") String empCode, @Param("newPwd") String newPwd);

    @Select("select empCode,empName,sum(prize) as totals,DATE_FORMAT(recordDate,'%Y-%m') as yearMonth from fee  where DATE_FORMAT(recordDate,'%Y-%m')=#{year} and state=1 group by  empCode")
    List<AnalyEmpSoleVo> analyEmpList(@Param("year")String year);
}
