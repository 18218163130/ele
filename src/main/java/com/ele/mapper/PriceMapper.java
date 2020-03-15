package com.ele.mapper;

import com.ele.entity.Price;
import com.ele.vo.PriceVo;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

/**
 * 电费单价
 *
 * @Author dongwf
 * @Date 2020/2/12
 */
@Mapper
public interface PriceMapper {

    @Insert("insert into price(gyPrice,syPrice,jtPrice,yearMonth) values(#{gyPrice},#{syPrice},#{jtPrice},#{yearMonth})")
    int insert(PriceVo priceVo);

    @Select("<script> select * from price <where> " +
            "<if test='yearMonth!=null'> yearMonth=#{yearMonth} </if>" +
            " </where></script>")
    List<Price> queryPrice(PriceVo priceVo);

    @Delete("delete from price where yearMonth=#{yearMonth}")
    int delete(@Param("yearMonth") Date yearMonth);

    @Update("update set gyPrice=#{gyPrice},jtPrice=#{jtPrice},syPrice=#{syPrice} where yearMonth=#{yearMonth}")
    int update(PriceVo priceVo);
}
