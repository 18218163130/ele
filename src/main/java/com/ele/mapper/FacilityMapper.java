package com.ele.mapper;

import com.ele.entity.Facility;
import com.ele.vo.FacilityVo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Author dongwf
 * @Date 2019/12/19
 */
public interface FacilityMapper {
    /**
     * 添加设备种类信息
     * @param facilityVo
     * @return
     */
    @Insert("insert into facility(facName,facPrice,facTotal,facFactory,facCode,facDate,facDesc,facTypeName,facType) " +
            "values(#{facName},#{facPrice},#{facTotal},#{facFactory},#{facCode},#{facDate},#{facDesc},#{facTypeName},#{facType})")
    int insertFacility(FacilityVo facilityVo);

    /**
     * 查询电力设备信息列表
     * @param facilityVo
     * @return
     */
    @Select("<script> select * from facility <where> " +
            "<if test='facId != null'> and facId =#{facId}</if>"+
            "<if test='facName != null'> and facName like concat('%',#{facName},'%')</if>"+
            "<if test='facType != null'> and facType =#{facType}</if>"+
            "<if test='facTypeName != null'> and facTypeName like concat('%',#{facTypeName},'%')</if>"+

            "<if test='facFactory != null'> and facFactory like concat('%',#{facFactory},'%')</if>"+
            "<if test='facCode != null'> and facCode like concat('%',#{facCode},'%')</if>"+
            " </where></script>")
    List<Facility> queryFacility(FacilityVo facilityVo);

    /**
     * 删除电力设备
     * @param facId
     * @return
     */
    @Delete("delete from facility where facId=#{facId}")
    int deleteFacility(@Param("facId") Integer facId);

    /**
     * 编辑修改电力设备
     * @param facilityVo
     */
    @Update("update facility set facName=#{facName},facPrice=#{facPrice},facTotal=#{facTotal}" +
            ",facFactory=#{facFactory},facCode=#{facCode},facDate=#{facDate},facDesc=#{facDesc}," +
            "facType=#{facType},facTypeName=#{facTypeName} where facId=#{facId}")
    int updateFacility(FacilityVo facilityVo);

    /**
     * 根据Id更新库存数量
     * @param facTotal
     * @param facId
     * @return
     */
    @Update("update facility set facTotal=facTotal-#{facTotal} where facId=#{facId}")
    int updateFacTotal(@Param("facTotal") Integer facTotal,@Param("facId") Integer facId);

    @Select("select * from facility")
    List<Facility> findFacType();

    /**
     * 分类查询设备种类
     * @param facType
     * @return
     */
    @Select("select * from facility where facType=#{facType}")
    List<Facility> findFacType2(@Param("facType") Integer facType);
}
