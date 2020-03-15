package com.ele.mapper;

import com.ele.entity.Fee;
import com.ele.vo.AnalyEmpSoleVo;
import com.ele.vo.AnalyFeeVo;
import com.ele.vo.FeeVo;
import org.apache.ibatis.annotations.*;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;

/**
 * @Author dongwf
 * @Date 2019/10/23
 */
public interface FeeMapping {
    /**
     * 创建电费单
     * @param fee
     * @return
     */
    @Insert("insert into fee(feeId,userId,realName,createTime,amount,prize,state,description,payWay,unitPrice,recordDate,empCode,empName) " +
            "values(#{feeId},#{userId},#{realName},#{createTime},#{amount},#{prize},#{state},#{description},#{payWay},#{unitPrice},#{recordDate},#{empCode},#{empName})")
    int insert(Fee fee);

    /**
     * 查询所有电费单列表
     * @return
     */
    @Select("<script> select * from fee <where> " +
            "<if test='recordDate != null'> and DATE_FORMAT(recordDate,'%Y-%m')=date_format(#{recordDate},'%Y-%m')</if>" +
            "<if test='userId != null'> and userId like concat('%',#{userId},'%')</if>" +
            " </where> </script>")
//    @Select("select * from fee where DATE_FORMAT(recordDate,'%Y-%m')=#{yearmonth}")
    List<Fee> queryAllFee(FeeVo feeVo);

    /**
     * 根据id删除电费单
     * @param feeId
     * @return
     */
    @Delete("delete from fee where feeId=#{feeId}")
    int deleteFeeById(@Param("feeId") String feeId);

    /**
     * 缴费
     * @param feeVo
     * @return
     */
    @Update("update fee set payWay=#{payWay},state=1,description=#{description} where feeId=#{feeId}")
    int updateFee(FeeVo feeVo);

    /**
     * 统计每个月的电费营销额
     * @param year
     * @return
     */
    @Select("select DATE_FORMAT(recordDate,'%m') months,sum(prize) as totals from fee where year(recordDate)=#{year} and state=1 group by  MONTH(recordDate)")
    List<AnalyFeeVo> analyFee(@Param("year")String year);
    /**
     * 统计每个月家庭的电费营销额
     * @param year
     * @return
     */
    @Select("select DATE_FORMAT(recordDate,'%m') months,sum(prize) as totals from fee where year(recordDate)=#{year} and state=1 and userId like CONCAT('%','JT','%') group by  MONTH(recordDate)")
    List<AnalyFeeVo> analyFeeJT(@Param("year")String year);

    /**
     * 统计每个月工业类型的电费营销额
     * @param year
     * @return
     */
    @Select("select DATE_FORMAT(recordDate,'%m') months,sum(prize) as totals from fee where year(recordDate)=#{year} and state=1 and userId like CONCAT('%','GY','%') group by  MONTH(recordDate)")
    List<AnalyFeeVo> analyFeeGY(@Param("year")String year);

    /**
     * 统计每个月商业类型的电费营销额
     * @param year
     * @return
     */
    @Select("select DATE_FORMAT(recordDate,'%m') months,sum(prize) as totals from fee where year(recordDate)=#{year} and state=1 and userId like CONCAT('%','SY','%') group by  MONTH(recordDate)")
    List<AnalyFeeVo> analyFeeSY(@Param("year")String year);

    /**
     * 统计员工营销额
     * @param yearMonth
     * @return
     */
    @Select("select empCode,empName,sum(prize) as totals from fee where DATE_FORMAT(recordDate,'%Y-%m')=#{yearMonth} and state=1 group by  empCode")
    List<AnalyEmpSoleVo> analyEmpSole(@Param("yearMonth") String yearMonth);

    /**
     * 公司历史营销收入
     * @return
     */
    @Select("select DATE_FORMAT(recordDate,'%Y-%m') as months ,sum(prize) as totals from fee where  state=1 group by  months limit 0,36")
    List<AnalyFeeVo> analyFeeYM();

}
