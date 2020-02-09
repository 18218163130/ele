package com.ele.mapper;

import com.ele.entity.Fee;
import com.ele.vo.FeeVo;
import org.apache.ibatis.annotations.*;

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
    @Insert("insert into fee(feeId,userId,realName,createTime,amount,prize,state,description,payWay,unitPrice,recordDate) " +
            "values(#{feeId},#{userId},#{realName},#{createTime},#{amount},#{prize},#{state},#{description},#{payWay},#{unitPrice},#{recordDate})")
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
    @Update("update fee set payWay=#{payWay},state=#{state},description=#{description} where feeId=#{feeId}")
    int updateFee(FeeVo feeVo);
}
