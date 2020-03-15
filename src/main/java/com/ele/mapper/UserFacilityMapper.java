package com.ele.mapper;

import com.ele.entity.UserFacility;
import com.ele.vo.AnalyFacilityVo;
import com.ele.vo.UserFacilityVo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Author dongwf
 * @Date 2019/12/28
 */
public interface UserFacilityMapper {
    /**
     * 添加安装设备信息
     *
     * @param userFacilityVo
     * @return
     */
    @Insert("insert into user_facility(userId,userName,facId,facName,num,price,totalPrice,isPay,createDate,facType) " +
            "values(#{userId},#{userName},#{facId},#{facName},#{num},#{price},#{totalPrice},#{isPay},#{createDate},#{facType})")
    int insert(UserFacilityVo userFacilityVo);

    /**
     * 查询安装设备列表
     *
     * @param userFacilityVo
     * @return
     */
    @Select("<script> select * from user_facility <where>" +
            "<if test='userId != null'> and userId like concat('%',#{userId},'%') </if> " +
            "</where></script>")
    List<UserFacility> query(UserFacilityVo userFacilityVo);

    /**
     * 删安装设备
     *
     * @param userFacId
     * @return
     */
    @Delete("delete from user_facility where userFacId=#{userFacId}")
    int delete(@Param("userFacId") Integer userFacId);

    /**
     * 更新缴费字段
     */
    @Update("update user_facility set isPay = 1 where userFacId=#{userFacId}")
    int updatePayField(@Param("userFacId") Integer userFacId);

    /**
     * 统计每月设备营销总额
     *
     * @param year
     * @return
     */
    @Select("select DATE_FORMAT(createDate,'%m') months,sum(totalPrice) as totals from user_facility where year(createDate)=#{year} and isPay=1 group by  MONTH(createDate)")
    List<AnalyFacilityVo> analyFacilityAll(@Param("year") String year);

    /**
     * 统计每月电表营销总额
     *
     * @param year
     * @return
     */
    @Select("select DATE_FORMAT(createDate,'%m') months,sum(totalPrice) as totals from user_facility where year(createDate)=#{year} and isPay=1 and facType=1 group by  MONTH(createDate)")
    List<AnalyFacilityVo> analyFacilityDianbiao(@Param("year") String year);

    /**
     * 统计每月电线营销总额
     *
     * @param year
     * @return
     */
    @Select("select DATE_FORMAT(createDate,'%m') months,sum(totalPrice) as totals from user_facility where year(createDate)=#{year} and isPay=1 and facType=2 group by  MONTH(createDate)")
    List<AnalyFacilityVo> analyFacilityDianxian(@Param("year") String year);

    /**
     * 统计每月变压器营销总额
     *
     * @param year
     * @return
     */
    @Select("select DATE_FORMAT(createDate,'%m') months,sum(totalPrice) as totals from user_facility where year(createDate)=#{year} and isPay=1 and facType=3 group by  MONTH(createDate)")
    List<AnalyFacilityVo> analyFacilityBianyaqi(@Param("year") String year);
}
