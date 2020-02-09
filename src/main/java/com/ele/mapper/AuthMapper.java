package com.ele.mapper;

import com.ele.entity.Auth;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 员工授权
 * @Author dongwf
 * @Date 2019/10/7
 */
public interface AuthMapper {

    /**
     * 根据员工类型查询拥有的权限列表
     * @param empType 员工类型
     * @return
     */
    @Select("select a.* from auth a,ele_auth ea where a.authId=ea.authId and ea.eleType=#{empType}")
    public List<Auth> findPermissionByEmpType(Integer empType);
}
