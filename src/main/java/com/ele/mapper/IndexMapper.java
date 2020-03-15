package com.ele.mapper;

import com.ele.entity.Index;
import com.ele.vo.IndexVo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Author dongwf
 * @Date 2019/10/15
 */
public interface IndexMapper {

    /**
     * 添加一条指标记录
     *
     * @param indexVo
     * @return
     */
    @Insert("insert into tb_index(indexName,type,sTime,oTime,state,setPerson,changePerson,changeTime,description) " +
            "values(#{indexName},#{type},#{sTime},#{oTime},#{state},#{setPerson},#{changePerson},#{changeTime},#{description})")
    int addIndex(IndexVo indexVo);

    /**
     * 修改指标记录
     *
     * @param indexVo
     * @return
     */
    @Update("update tb_index set indexName=#{indexName},type=#{type},sTime=#{sTime},oTime=#{oTime},state=#{state}," +
            "setPerson=#{setPerson},changePerson=#{changePerson},changeTime=#{changeTime},description=#{description} where " +
            "indexId=#{indexId}")
    int updateIndex(IndexVo indexVo);

    /**
     * 删除指标记录
     *
     * @param indexId
     * @return
     */
    @Delete("delete from tb_index where indexId=#{indexId}")
    int deleteIndex(@Param("indexId") Integer indexId);

    /**
     * 查询所有指标记录
     *
     * @param indexVo
     * @return
     */
    @Select("<script> select * from tb_index <where> " +
            "<if test='indexId!=null'> and indexId like concat('%',#{indexId},'%') </if>" +
            "<if test='indexName!=null'> and indexName like concat('%',#{indexName},'%') </if>" +
            "<if test='type!=null'> and type like concat('%',#{type},'%') </if>" +
            "<if test='sTime!=null'> and sTime like concat('%',#{sTime},'%') </if>" +
            "<if test='oTime!=null'> and oTime like concat('%',#{oTime},'%') </if>" +
            "<if test='state!=null'> and state like concat('%',#{state},'%') </if>" +
            "<if test='setPerson!=null'> and setPerson like concat('%',#{setPerson},'%') </if>" +
            "<if test='changePerson!=null'> and changePerson like concat('%',#{changePerson},'%') </if>" +
            "<if test='changeTime!=null'> and changeTime like concat('%',#{changeTime},'%') </if>" +
            "<if test='description!=null'> and description like concat('%',#{description},'%') </if>" +
            " </where> " +
            "</script>")
    List<Index> queryAllIndex(IndexVo indexVo);
}
