package com.ele.controller;

import com.ele.config.SysConstast;
import com.ele.entity.Emp;
import com.ele.service.EmpService;
import com.ele.service.LogService;
import com.ele.service.UserService;
import com.ele.utils.DataGridView;
import com.ele.utils.ResultObj;
import com.ele.utils.UserCodeUtils;
import com.ele.vo.LogVo;
import com.ele.vo.UserVo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @Author dongwf
 * @Date 2019/10/10
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private LogService logService;
    @Autowired
    private EmpService empService;

    /**
     * 添加一个客户
     *
     * @param user
     * @return
     */
    @RequestMapping("addUser")
    public ResultObj addUser(UserVo user) {
        try {
            Integer type = user.getType();
            String pre = null; // 客户账号前缀
            if (type == 0) {
                pre = "JT";
            } else if (type == 1) {
                pre = "SY";
            } else {
                pre = "GY";
            }
            // 查询销售人员是否正确
            Emp dbEmp = empService.findByEmpCode(user.getEmpCode());
            if (dbEmp == null || !dbEmp.getEmpName().equals(user.getEmpName())) {
                return ResultObj.EMPCODE_ERROR;
            }
            user.setUserId(pre + UserCodeUtils.getLocalTrmSeqNum()); // 产生并设置客户编号
            String md5Pwd = new Md5Hash(SysConstast.EMP_DEFAULT_PWD).toString(); // 以MD5加密并设置初始密码
            user.setPwd(md5Pwd);
            userService.addUser(user);
            saveLog();// 记录日志信息
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            // 添加客户出错
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 加载客户列表，返回的是DataGridView封装类对象
     *
     * @param userVo
     * @return
     */
    @RequestMapping("loadAllUser")
    public DataGridView loadAllUser(UserVo userVo) {
        return userService.getUserList(userVo);
    }

    /**
     * 修改客户信息
     *
     * @param userVo
     * @return
     */
    @RequestMapping("updateUser")
    public ResultObj updateUser(UserVo userVo) {
        try {
            userService.updateUser(userVo);
            saveLog();// 记录日志信息
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 重置客户密码
     *
     * @param userVo
     * @return
     */
    @RequestMapping("resetUserPwd")
    public ResultObj resetUserPwd(UserVo userVo) {
        try {
            // 从新生成一个客户id
            String newPwd = new Md5Hash(SysConstast.USER_RESET_PWD).toString();
            userVo.setPwd(newPwd);
            userService.resetUserPwd(userVo);
            saveLog(); // 记录日志信息
            return ResultObj.RESET_PWD_SUCCESS;
        } catch (Exception e) {
            return ResultObj.RESET_PWD_ERROR;
        }
    }

    /**
     * 删除客户信息
     *
     * @param userVo
     * @return
     */
    @RequestMapping("deleteUser")
    public ResultObj deleteUser(UserVo userVo) {
        try {
            userService.deleteUser(userVo.getUserId());
            saveLog();
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 批量删除选中的客户信息
     *
     * @param userVo
     * @return
     */
    @RequestMapping("deleteBatchUser")
    public ResultObj deleteBatchUser(UserVo userVo) {
        try {
            String[] pwds = userVo.getIds();
            for (String pwd : pwds) {
                userService.deleteUser(pwd);
            }
            saveLog();
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 保存日志记录
     */
    public void saveLog() {
        //获取到当前线程绑定的请求对象
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //已经拿到session,就可以拿到session中保存的用户信息了。
        Emp sessionEmp = (Emp) request.getSession().getAttribute("emp");
        LogVo logVo = new LogVo();
        logVo.setLogName(request.getRequestURI()); // 设置日志名称
        logVo.setLogTime(new Date()); // 设置日志记录时间
        logVo.setAssociated(sessionEmp.getEmpName()); // 设置触发人姓名
        logVo.setLoginIp(request.getRemoteAddr()); // 设置访问IP地址
        logService.addLogInfo(logVo);
    }
}
