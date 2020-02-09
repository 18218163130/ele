package com.ele.mapper;

import com.ele.entity.UserFacility;
import com.ele.vo.UserFacilityVo;
import org.apache.ibatis.annotations.*;
import org.hibernate.validator.constraints.ParameterScriptAssert;

import java.util.List;

/**
 * @Author dongwf
 * @Date 2019/12/28
 */
public interface UserFacilityMapper {
    /**
     * 添加安装设备信息
     * @param userFacilityVo
     * @return
     */
    @Insert("insert into user_facility(userId,userName,facId,facName,num,price,totalPrice,isPay,createDate) " +
            "values(#{userId},#{userName},#{facId},#{facName},#{num},#{price},#{totalPrice},#{isPay},#{createDate})")
    int insert(UserFacilityVo userFacilityVo);

    /**
     * 查询安装设备列表
     * @param userFacilityVo
     * @return
     */
    @Select("<script> select * from user_facility <where>" +
            "<if test='userId != null'> and userId like concat('%',#{userId},'%') </if> "+
            "</where></script>")
    List<UserFacility> query(UserFacilityVo userFacilityVo);

    /**
     * 删安装设备
     * @param userFacId
     * @return
     */
    @Delete("delete from user_facility where userFacId=#{userFacId}")
    int delete(@Param("userFacId")Integer userFacId);

    /**
     * 更新缴费字段
     */
    @Update("update user_facility set isPay = 1 where userFacId=#{userFacId}")
    int updatePayField(@Param("userFacId") Integer userFacId);
}
