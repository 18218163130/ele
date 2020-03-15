package com.ele.mapper;

import com.ele.entity.News;
import com.ele.vo.NewsVo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Author dongwf
 * @Date 2019/10/14
 */
public interface NewsMapper {

    /**
     * 添加一条公告
     *
     * @param newsVo
     * @return
     */
    @Insert("insert into news(title,content,createTime,editer) values(#{title},#{content},#{createTime},#{editer})")
    int insertNews(NewsVo newsVo);

    /**
     * 删除公告
     *
     * @param newsId
     * @return
     */
    @Delete("delete from news where newsId = #{newsId}")
    int deleteNewsById(@Param("newsId") Integer newsId);

    /**
     * 修改公告内容
     *
     * @param newsVo
     * @return
     */
    @Update("update news set title=#{title},content=#{content},createTime=#{createTime},editer=#{editer} where newsId=#{newsId}")
    int updateNews(NewsVo newsVo);

    /**
     * 查询所有公告记录
     *
     * @return
     */
    @Select("<script> select * from news <where>" +
            "<if test='newsId !=null '> and newsId like concat('%',#{newsId},'%')</if>" +
            "<if test='title !=null '> and title like concat('%',#{title},'%')</if>" +
            "<if test='content !=null '> and content like concat('%',#{content},'%')</if>" +
            "<if test='editer !=null '> and editer like concat('%',#{editer},'%')</if>" +
            "<if test='startTime !=null '> and createTime >= #{startTime} </if>" +
            "<if test='endTime !=null '> and createTime <![CDATA[<=]]> #{endTime} </if>" +
            "</where>" +
            " order by createTime desc " +
            "</script>")
    List<News> queryAllNews(NewsVo newsVo);

}
