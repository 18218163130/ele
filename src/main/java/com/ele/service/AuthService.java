package com.ele.service;

import com.ele.entity.Auth;

import java.util.List;

/**
 * 操作员工授权业务接口
 *
 * @Author dongwf
 * @Date 2019/10/9
 */
public interface AuthService {
    /**
     * 员工根据员工类型查询对应的权限
     *
     * @param empType 员工类型
     * @return
     */
    List<Auth> findPermissionByEmpType(Integer empType);
}
