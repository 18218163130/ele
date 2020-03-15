package com.ele.shiro;

import com.ele.entity.Auth;
import com.ele.entity.Emp;
import com.ele.service.AuthService;
import com.ele.service.EmpService;
import com.ele.vo.EmpAuthVo;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 员工认证的Realm
 *
 * @Author dongwf
 * @Date 2019/10/8
 */
public class EmpRealm extends AuthorizingRealm {
    @Autowired
    private AuthService authService;
    @Autowired
    private EmpService empService;

    /**
     * 授权逻辑
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        EmpAuthVo empAuthVo = (EmpAuthVo) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // 添加授权
        if (empAuthVo.getAuths() != null && empAuthVo.getAuths().size() > 0) {
            info.addStringPermissions(empAuthVo.getAuths());
        }
        System.out.println("授权完成...");
        return info;
    }

    /**
     * 认证逻辑
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 获取登录的员工编号
        String empCode = authenticationToken.getPrincipal().toString();
        Emp emp = empService.findByEmpCode(empCode);
        if (emp == null) {
            // 员工编号不存在
            return null; // 底层抛出用户名不存在异常 UnknownAccountException
        }
        // 查询该员工的授权信息并封装到EmpAuthVo类中
        List<Auth> permissions = authService.findPermissionByEmpType(emp.getType());
        EmpAuthVo empAuthVo = new EmpAuthVo();
        // 判断授权列表是否为空
        empAuthVo.setEmp(emp);
        if (permissions != null && permissions.size() > 0) {
            for (Auth auth : permissions) {
                empAuthVo.getAuths().add(auth.getPer());
            }
        }
        System.out.println("完成员工认证...");
        return new SimpleAuthenticationInfo(empAuthVo, emp.getPwd(), this.getName());
    }
}
