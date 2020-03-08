package com.ele.mapper;

import com.ele.entity.User;
import com.ele.vo.UserVo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Author dongwf
 * @Date 2019/10/10
 */
@Mapper
public interface UserMapper {
    /**
     * 通过客户Id获取客户信息
     * @param userId
     * @return
     */
    @Select("select * from user where userId=#{userId}")
    User findUserById(@Param("userId") String userId);

    /**
     * 通过客户ID和名称查询客户
     * @param userId
     * @param userName
     * @return
     */
    @Select("select * from user where userId=#{userId} and realName=#{userName}")
    User findUser(@Param("userId")String userId,@Param("userName")String userName);
    /**
     * 向user表中添加客户信息
     * @param user
     */
    @Insert("insert into user(userId,realName,pwd,sex,type,phone,address,identify,decription,empId,empName) " +
            "values(#{userId},#{realName},#{pwd},#{sex},#{type},#{phone},#{address},#{identify},#{decription},#{empId},#{empName})")
    int insert(User user);

    /**
     * 可以根据条件查询客户信息列表
     * @param user
     * @return
     */
    @Select("<script> select * from user <where> "
            + "<if test='userId != null'> and userId like concat('%',#{userId},'%') </if>"
            + "<if test='realName != null'> and realName like concat('%',#{realName},'%') </if>"
            + "<if test='sex != null'> and sex like concat('%',#{sex},'%') </if>"
            + "<if test='type != null'> and type like concat('%',#{type},'%') </if>"
            + "<if test='phone != null'> and phone like concat('%',#{phone},'%') </if>"
            + "<if test='address != null'> and address like concat('%',#{address},'%') </if>"
            + "<if test='identify != null'> and identify like concat('%',#{identify},'%') </if>"
            + "<if test='empCode != null'> and empCode like concat('%',#{empCode},'%') </if>"
            + "<if test='empName != null'> and empName like concat('%',#{empName},'%') </if>"
            + "</where>"
            + "</script>")
    List<User> queryUserList(User user);

    /**
     * 修改客户信息
     * @param userVo
     */
    @Update("update user set realName=#{realName},sex=#{sex},type=#{type},phone=#{phone},address=#{address}," +
            "identify=#{identify},decription=#{decription},empId=#{empId},empName=#{empName} where userId=#{userId}")
    void updateUser(UserVo userVo);

    /**
     * 修改客户密码
     * @param userVo
     */
    @Update("update user set pwd=#{pwd} where userId=#{userId}")
    void resetUserPwd(UserVo userVo);

    /**
     * 根据客户Id删除用户信息
     * @param userId
     */
    @Delete("delete from user where userId=#{userId}")
    void deleteUserById(@Param("userId") String userId);

    /**
     * 登录
     * @param userVo
     * @return
     */
    @Select("select * from user where userId=#{userId} and pwd=#{pwd}")
    User login(UserVo userVo);
    /**
     * 检查员工账号和密码
     */
    @Select("select * from user where userId=#{userId} and pwd=#{pwd}")
    User checkUserIdAndPwd(@Param("userId") String userId,@Param("pwd") String pwd);

    /**
     * 更改员工密码
     * @param userId
     * @param newPwd
     */
    @Update("update user set pwd=#{newPwd} where userId=#{userId}")
    void updateNewPwd(@Param("userId")String userId,@Param("newPwd")String newPwd);
}
