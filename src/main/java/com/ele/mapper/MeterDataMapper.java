package com.ele.mapper;

import com.ele.entity.Meter;
import com.ele.entity.MeterData;
import com.ele.vo.AnalyMonthConsumeVo;
import com.ele.vo.MeterDataVo;
import org.apache.ibatis.annotations.*;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

/**
 * @Author dongwf
 * @Date 2019/10/17
 */
public interface MeterDataMapper {
    /**
     * 添加电表数据记录
     * @param meterDataVo
     * @return
     */
    @Insert("insert into meter_data(userId,meterId,consume,displayNumber,recordDate,recordMonth,verifyPerson,type) " +
            "values(#{userId},#{meterId},#{consume},#{displayNumber},#{recordDate},#{recordMonth},#{verifyPerson},#{type})")
    int insertMeterData(MeterDataVo meterDataVo);

    /**
     * 删除指定电表记录
     * @param dataId
     * @return
     */
    @Delete("delete  from meter_data where dataId=#{dataId}")
    int deleteMeterData(@Param("dataId") Integer dataId);

    /**
     * 更新电表记录数据
     * @param meterDataVo
     * @return
     */
//    @Update("update meter_data set userId=#{userId},meterId=#{meterId},consume=#{consume},displayNumber=#{displayNumber}," +
//            "recordDate=#{recordDate},state=#{state},verifyPerson=#{verifyPerson},level=#{level} where dataId=#{dataId}")
    @Update("<script> update meter_data <set>  " +
            "<if test='userId!=null'> ,userId=#{userId} </if>" +
            "<if test='meterId!=null'> ,meterId=#{meterId} </if>" +
            "<if test='displayNumber!=null'> ,displayNumber=#{displayNumber} </if>" +
            "<if test='verifyPerson!=null'> ,verifyPerson=#{verifyPerson} </if>" +
            "<if test='type>0'> ,type=#{type} </if>" +
            "<if test='recordDate!=null'> ,recordDate=#{recordDate} </if>" +
            "<if test='consume>0'> ,consume=#{consume} </if>" +
            "<if test='recordMonth!=null'> ,recordMonth=#{recordMonth} </if>" +
            "<if test='state!=null'> ,state=#{state} </if>" +
            "</set> where dataId=#{dataId}" +
            "</script>")
    int updateMeterData(MeterDataVo meterDataVo);

    /**
     * 查询所有电表记录数据
     *  "<if test='startTime != null'> and recordDate >= #{startTime} </if>" +
     *  "<if test='endTime !=null '> and recordDate <![CDATA[<=]]> #{endTime} </if>" +
     * @param meterDataVo
     * @return
     */
    @Select("<script> select * from meter_data <where> " +
            "<if test='userId !=null'> and userId like concat('%',#{userId},'%')</if>" +

            "<if test='yearmonth != null'> and DATE_FORMAT(recordMonth,'%Y-%m')=#{yearmonth} </if>" +
            "</where></script>")
    List<MeterData> queryAllMeterData(MeterDataVo meterDataVo);

    /**
     * 查询上一个月的电表数字
     * @param meterId
     * @return
     */
//    @Select("select displayNumber from meter_data where meterId=#{meterId} order by displayNumber desc limit 0,1")
    @Select("select meterId,recordMonth,displayNumber from meter_data where meterId=#{meterId} order by recordMonth desc limit 0,1")
    MeterData queryLastDisplayNumberByMeterId(@Param("meterId") Integer meterId);

    /**
     * 根据meterid查询电表数据记录
     * @param meterId
     * @return
     */
    @Select("select * from meter_data where meterId=#{meterId} order by recordDate desc")
//    @Select("select meterId,date_format(recordMonth,'%Y-%m') as recordMonth from meter_data where meterId=#{meterId} order by recordMonth desc limit 0,1")
    List<MeterData> queryMeterDataByMeterId(@Param("meterId") Integer meterId);

    /**
     * 分析统计每月消耗电量总数
     * @return
     */
//    @Select("select DATE_FORMAT(recordDate,\"%m\") as months,sum(consume) as totals from meter_data where year(recordDate)=#{year} group by MONTH(recordDate)")
    @Select("select DATE_FORMAT(recordMonth,'%m') as months,sum(consume) as totals from meter_data where year(recordMonth)=#{year} group by MONTH(recordMonth)")
    List<AnalyMonthConsumeVo> queryMonthConsume(@Param("year") String year);

    /**
     * 分用电类型查询
     * @param state
     * @return
     */
    @Select("select sum(consume) as totals,DATE_FORMAT(recordDate,'%m') as months from meter_data " +
            "where DATE_FORMAT(recordDate,'%Y')=#{year} and state=#{state} GROUP BY month(recordDate)")
    List<AnalyMonthConsumeVo> queryState(@Param("state") Integer state,@Param("year")String year);

    /**
     * 计算yyyy-MM电费
     * @param yearmonth
     * @return
     */
    @Select("select * from meter_data where DATE_FORMAT(recordDate,'%Y-%m')=#{yearmonth2}")
    List<MeterData> calculationFee(@Param("yearmonth2") String yearmonth);

    /**
     * 判断该月是否已经抄表
     * @param recordMonth
     * @return
     */
    @Select("select meterId,date_format(recordMonth,'%Y-%m') from meter_data where meterId=#{meterId} order by recordMonth desc limit 0,1")
    MeterData checkRecordMonth(@Param("recordMonth")Date recordMonth);

    /**
     * 更新电表为已生成电费单状态
     */
    @Update("update meter_data set state=1 where dataId=#{dataId}")
    int updateStateByYearmonth(@Param("dataId")Integer dataId);

    /**
     * 查询没有生成电费单的记录
     * @return
     */
    @Select("select * from meter_data where state=0 and DATE_FORMAT(recordMonth,'%Y-%m')=#{recordMonth}")
    List<MeterData> selectByState(@Param("recordMonth")String recordMonth);

    /**
     *
     * @param dataId
     * @return
     */
    @Select("select * from meter_data where dataId=#{dataId}")
    MeterData queryByDataId(@Param("dataId")Integer dataId);

}
