package com.ele.mapper;

import com.ele.entity.Market;
import com.ele.utils.DataGridView;
import com.ele.vo.MarketVo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @Author dongwf
 * @Date 2019/11/1
 */
public interface MarketMapper {
    /**
     * 添加
     * @param marketVo
     * @return
     */
    @Insert("insert into market(marketName,userId,marketDate,state,remark) values(#{marketName},#{userId},#{marketDate},#{state},#{remark})")
    int insertMarket(MarketVo marketVo);

    /**
     * 查询营销信息列表
     * @param marketVo
     * @return
     */
    @Select("<script> select * from market <where> " +
            "<if test='marketId != null'> marketId like concat('%',#{marketId},'%')</if>" +
            "<if test='marketName != null'> marketName like concat('%',#{marketName},'%')</if>" +
            "<if test='userId != null'> userId like concat('%',#{userId},'%')</if>" +
            "<if test='state != null'> state like concat('%',#{state},'%')</if>" +
            " </where></script>")
    List<Market> queryAll(MarketVo marketVo);

    /**
     * 修改营销信息
     * @param marketVo
     */
    @Update("update market set marketName=#{marketName},marketDate=#{marketDate},userId=#{userId},state=#{state},remark=#{remark} where marketId=#{marketId}")
    int updateMarket(MarketVo marketVo);

    /**
     * 删除营销记录
     * @param id
     */
    @Delete("delete from market where marketId=#{marketId}")
    int delete(Integer id);
}
