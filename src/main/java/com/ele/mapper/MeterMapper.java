package com.ele.mapper;

import com.ele.entity.Meter;
import com.ele.vo.MeterVo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Author dongwf
 * @Date 2019/10/17
 */
public interface MeterMapper {
    /**
     * 添加新的电表
     * @param meterVo
     * @return
     */
    @Insert("insert into meter(userId,code,startDate,factory,state,type,meterName) values(#{userId},#{code},#{startDate},#{factory},#{state},#{type},#{meterName})")
    int addMeter(MeterVo meterVo);

    /**
     * 查询电表信息列表
     * @param meterVo
     * @return
     */
    @Select("<script> select * from meter <where>" +
            "<if test='meterId != null'> and meterId like concat('%',#{meterId},'%') </if>" +
            "<if test='userId != null'> and userId like concat('%',#{userId},'%') </if>" +
            "<if test='code != null'> and code like concat('%',#{code},'%') </if>" +
            "<if test='startDate != null'> and startDate like concat('%',#{startDate},'%') </if>" +
            "<if test='factory != null'> and factory like concat('%',#{factory},'%') </if>" +
            "<if test='state != null'> and state like concat('%',#{state},'%') </if>" +
            "<if test='type != null'> and type like concat('%',#{type},'%') </if>" +
            " </where></script>")
    List<Meter> queryAllMeter(MeterVo meterVo);

    /**
     * 修改电表信息记录
     * @param meterVo
     * @return
     */
    @Update("<script> update meter <set> " +
            "<if test='userId != null'> userId=#{userId},</if>" +
            "<if test='code != null'> code=#{code},</if>" +
            "<if test='startDate != null'> startDate=#{startDate},</if>" +
            "<if test='factory != null'> factory=#{factory},</if>" +
            "<if test='state != null'> state=#{state},</if>" +
            "<if test='type != null'> type=#{type},</if>" +
            "<if test='meterName != null'> meterName=#{meterName},</if>" +
            " </set>" +
            " where meterId=#{meterId}" +
            "</script>")
    int updateMeter(MeterVo meterVo);

    /**
     * 删除地点表记录
     * @param meterId
     * @return
     */
    @Delete("delete from meter where meterId=#{meterId}")
    int deleteMeter(@Param("meterId") Integer meterId);

    /**
     * 查询客户电表列表
     * @param userId
     * @return
     */
    @Select("select * from meter where userId=#{userId}")
    List<Meter> findMeterByUserId(@Param("userId") String userId);

    /**
     * 更改电路电容
     * @param type
     * @param meterId
     * @return
     */
    @Update("update meter set type=#{type} where meterId=#{meterId}")
    int changeCapacity(@Param("type") Float type,@Param("meterId") Integer meterId);

    /**
     * 过户
     * @param userId
     * @param meterId
     * @return
     */
    @Update("update meter set userId=#{userId} where meterId=#{meterId}")
    int updateUserId(@Param("userId")String userId,@Param("meterId")Integer meterId);
}
