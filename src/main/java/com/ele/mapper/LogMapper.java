package com.ele.mapper;

import com.ele.entity.Log;
import com.ele.vo.LogVo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 操作日志记录
 * @Author dongwf
 * @Date 2019/10/12
 */
@Mapper
public interface LogMapper {
    /**
     * 插入日志信息
     * @param logVo
     * @return
     */
    @Insert("insert into log(logName,logTime,loginIp,associated) value(#{logName},#{logTime},#{loginIp},#{associated})")
    void insertLogInfo(LogVo logVo);

    /**
     * 删除日志信息
     * @param logId
     * @return
     */
    @Delete("delete from log where logId=#{logId}")
    int deleteLogById(@Param("logId") Integer logId);

    /**
     * 查询所有日志信息
     * @return
     */
    @Select("<script> select * from log <where> " +
            "<if test = 'loginIp != null'> and loginIp like concat('%',#{loginIp},'%') </if>" +
            "<if test = 'associated != null'> and associated like concat('%',#{associated},'%') </if>" +
            "<if test = 'startTime != null'> and logTime >= #{startTime} </if>" +
            "<if test = 'endTime != null'> and logTime <![CDATA[<=]]> #{endTime} </if>" +
            "</where>" +
            " order by logTime desc " +
            "</script>")
    List<Log> queryAllLog(LogVo logVo);

}
