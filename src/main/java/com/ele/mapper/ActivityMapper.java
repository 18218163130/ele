package com.ele.mapper;

import com.ele.entity.Activity;
import com.ele.vo.ActivityVo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Author dongwf
 * @Date 2019/11/22
 */
public interface ActivityMapper {

    @Insert("insert into activity(userId,joinTime,discountId) values(#{userId},#{joinTime},#{discountId})")
    int insert(ActivityVo activityVo);

    @Select("select * from activity where discountId=#{discountId} and userId like concat('',#{userId},'')")
    Activity select(@Param("discountId") Integer discountId, @Param("userId") String userId);

    /**
     * 活动报名客户列表
     *
     * @return
     */
    @Results(id = "userMap", value = {
            @Result(property = "userId", column = "userId"),
            @Result(property = "realName", column = "realName"),
            @Result(property = "identify", column = "identify"),
            @Result(property = "sex", column = "sex"),
            @Result(property = "type", column = "type"),
            @Result(property = "phone", column = "phone"),
            @Result(property = "address", column = "address"),
            @Result(property = "user", column = "userId", one = @One(select = "com.ele.mapper.UserMapper.findUserById"))
    })
    @Select("<script> select * from activity <where>" +
            "<if test='activityId!=null'> activityId=#{activityId} </if>" +
            "<if test='discountId!=null'> discountId=#{discountId} </if>" +
            " </where></script>")
    List<Activity> selectActivity(ActivityVo activityVo);

    @Delete("delete from activity where activityId=#{activityId}")
    int delete(@Param("activityId") Integer activityId);


    /**
     * 批量导出
     *
     * @param ids
     * @return
     */
    @Results(id = "userList", value = {
            @Result(property = "userId", column = "userId"),
            @Result(property = "realName", column = "realName"),
            @Result(property = "identify", column = "identify"),
            @Result(property = "sex", column = "sex"),
            @Result(property = "type", column = "type"),
            @Result(property = "phone", column = "phone"),
            @Result(property = "address", column = "address"),
            @Result(property = "user", column = "userId", one = @One(select = "com.ele.mapper.UserMapper.findUserById"))
    })
    @Select("<script> select * from activity <where>" +
            " activityId in " +
            "<foreach collection ='ids' item='item' open='(' separator=',' close=')'> #{item} </foreach>" +
            " </where></script>")
    List<Activity> queryActivityById(@Param("ids") Integer[] ids);
}
