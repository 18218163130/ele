package com.ele.service.impl;

import com.ele.entity.Auth;
import com.ele.mapper.AuthMapper;
import com.ele.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 操作员工授权业务
 *
 * @Author dongwf
 * @Date 2019/10/9
 */
@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private AuthMapper authMapper;

    @Override
    public List<Auth> findPermissionByEmpType(Integer empType) {
        return authMapper.findPermissionByEmpType(empType);
    }
}
