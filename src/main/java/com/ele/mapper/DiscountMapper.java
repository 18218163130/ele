package com.ele.mapper;

import com.ele.entity.Discount;
import com.ele.vo.DiscountVo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Author dongwf
 * @Date 2019/11/7
 */
public interface DiscountMapper {
    /**
     * 添加营销信息
     * @param discountVo
     * @return
     */
    @Insert("insert into discount(title,content,createTime,state,startTime,endTime) values(#{title},#{content},#{createTime},#{state},#{startTime},#{endTime})")
    int insert(DiscountVo discountVo);

    /**
     * 删除营销信息
     * @param discountId
     * @return
     */
    @Delete("delete from discount where discountId=#{discountId}")
    int delete(@Param("discountId") Integer discountId);

    /**
     * 查询营销信息列表
     * @param discountVo
     * @return
     */
    @Select("<script>select * from discount <where> " +
            "<if test='discountId != null'> discountId like concat('%',#{discountId},'%')</if>" +
            "<if test='title != null'> title like concat('%',#{title},'%')</if>" +
            " </where></script>")
    List<Discount> queryDiscount(DiscountVo discountVo);

    /**
     * 更新营销信息
     * @param discountVo
     * @return
     */
    @Update("<script> update discount set " +
            "<if test='title != null'> title=#{title}, </if>" +
            "<if test='content != null'> content=#{content}, </if>" +
            "<if test='createTime != null'> createTime=#{createTime}, </if>" +
            "<if test='state != null'> state=#{state}, </if>" +
            "<if test='startTime != null'> startTime=#{startTime}, </if>" +
            "<if test='endTime != null'> endTime=#{endTime} </if>" +
            "where discountId=#{discountId}</script>")
    int update(DiscountVo discountVo);

    /**
     * 查询前面4条有效的优惠信息
     * @return
     */
    @Select("select * from discount where state=1 order by createTime desc limit 0,4")
    List<Discount> queryDiscountByState();
}
