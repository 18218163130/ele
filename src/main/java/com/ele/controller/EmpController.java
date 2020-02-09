package com.ele.controller;

import com.ele.config.SysConstast;
import com.ele.entity.Emp;
import com.ele.service.EmpService;
import com.ele.service.LogService;
import com.ele.utils.CaptchaCodeUtil;
import com.ele.utils.CommonUtil;
import com.ele.utils.DataGridView;
import com.ele.utils.ResultObj;
import com.ele.vo.EmpAuthVo;
import com.ele.vo.EmpVo;
import com.ele.vo.LogVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.session.HttpServletSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

/**
 * 公司员工的控制器
 * @Author dongwf
 * @Date 2019/10/7
 */
@Controller
@RequestMapping("emp")
public class EmpController {

    @Autowired
    private EmpService empService;
    @Autowired
    private LogService logService; // 注入日记

    /**
     * 员工登录
     * @param emp
     * @param model
     * @param session
     * @return
     */
    @PostMapping("login")
    public String Login(EmpVo empVo, Model model, HttpSession session){
        //获取到当前线程绑定的请求对象
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        // 判断验证码是否正确
        String code = (String)request.getSession().getAttribute("code");
        if(!code.equalsIgnoreCase(empVo.getCode())){
            model.addAttribute("msg",SysConstast.LOGIN_CODE_ERROR);
            return "main/login";
        }

        // 将密码转换成MD5
        String md5Pwd = new Md5Hash(empVo.getPwd()).toString();
        UsernamePasswordToken token = new UsernamePasswordToken(empVo.getEmpCode(),md5Pwd);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            EmpAuthVo empAuthVo = (EmpAuthVo)subject.getPrincipal();
            // 保存到session
            session.setAttribute("emp",empAuthVo.getEmp());
            /**
             * 添加日志记录
             */
//
//            //已经拿到session,就可以拿到session中保存的用户信息了。
//            Emp sessionEmp = (Emp)request.getSession().getAttribute("emp");
//            LogVo logVo = new LogVo();
//            logVo.setLogName(request.getRequestURI()); // 设置日志名称
//            logVo.setLogTime(new Date()); // 设置日志记录时间
//            logVo.setAssociated("员工编号:"+emp.getEmpCode()); // 设置触发人姓名
//            logVo.setLoginIp(request.getRemoteAddr()); // 设置访问IP地址
//            logService.addLogInfo(logVo);

            return "main/index";
        } catch (UnknownAccountException e) {
            // 员工编号不存在
            model.addAttribute("msg",SysConstast.EMP_LOGIN_NO_ACCOUNT_MSG);
            return "main/login";
        } catch(IncorrectCredentialsException e){
            // 密码错误
            model.addAttribute("msg",SysConstast.EMP_LOGIN_ERROR_MSG);
            return "main/login";
        }
    }

    @GetMapping("getCode")
    public  void  drawCaptcha(HttpServletResponse response,HttpSession session) throws IOException {
        //生成4位随机验证码
        String code= new CaptchaCodeUtil().randomStr(4);
        session.setAttribute("code",code);
        //id和验证码存入redis，3分钟有效
       // redisTemplate.opsForValue().set("CODE:"+captchaId,code,3l,TimeUnit.MINUTES);
        //指定验证码长宽
        CaptchaCodeUtil vCode = new CaptchaCodeUtil(116,36,4,10,code);
        vCode.write(response.getOutputStream());
    }


    /**
     * 查询所有员工列表
     * @param empVo
     * @return
     */
    @RequestMapping("loadAllEmp")
    @ResponseBody
    public DataGridView loadAllEmp(EmpVo empVo){
        return empService.loadAllEmp(empVo);
    }

    /**
     * 添加员工信息
     * @param empVo
     * @return
     */
    @RequestMapping("addEmp")
    @ResponseBody
    public ResultObj addEmp(EmpVo empVo){
        try{
            // 先判断该员工编号是否存在
            Emp dbEmp = empService.findByEmpCode(empVo.getEmpCode());
            if(dbEmp != null){
                return ResultObj.EMPCODE_EXIS; // 表示该员工编号已经存在
            }
            // 设置员工初始密码
            String initPwd = new Md5Hash(SysConstast.EMP_DEFAULT_PWD).toString();
            empVo.setPwd(initPwd);
            empService.register(empVo);
            /**
             * 记录日志信息
             */
            saveLog();

            return ResultObj.EMPCODE_SUCCESS;
        }catch(Exception e){
            return ResultObj.EMPCODE_ERROR;
        }
    }

    /**
     * 修改员工信息
     * @param empVo
     * @return
     */
    @RequestMapping("updateEmp")
    @ResponseBody
    public ResultObj updateEmp(EmpVo empVo){
        try{
            empService.updateEmp(empVo);
            /**
             * 记录日志信息
             */
            saveLog();
            return ResultObj.UPDATE_SUCCESS;
        }catch(Exception e){
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 重置员工密码
     * @param empVo
     * @return
     */
    @RequestMapping("resetEmpPwd")
    @ResponseBody
    public ResultObj resetEmpPwd(EmpVo empVo){
        try{
            empService.resetEmpPwd(empVo.getEmpId());
            /**
             * 记录日志信息
             */
            saveLog();
            return ResultObj.RESET_PWD_SUCCESS;
        }catch(Exception e){
            return ResultObj.RESET_PWD_ERROR;
        }
    }

    /**
     * 删除员工信息
     * @param empVo
     * @return
     */
    @RequestMapping("deleteEmp")
    @ResponseBody
    public ResultObj deleteEmp(EmpVo empVo){
        try{
            empService.deleteEmp(empVo.getEmpId());
            /**
             * 记录日志
             */
            saveLog();
            return ResultObj.DELETE_SUCCESS;
        }catch(Exception e){
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 批量删除员工信息
     * @param empVo
     * @return
     */
    @RequestMapping("deleteBatchEmp")
    @ResponseBody
    public ResultObj deleteBatchEmp(EmpVo empVo){
        try{
            Integer[] ids = empVo.getIds();
            for(Integer empId:ids){
                empService.deleteEmp(empId);
            }
            /**
             * 记录日志
             */
            saveLog();
            return ResultObj.DELETE_SUCCESS;
        }catch(Exception e){
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 模糊查询
     * @return
     */
    @RequestMapping("loadAll")
    @ResponseBody
    public DataGridView loadAll(EmpVo empVo){
        return empService.loadAllEmp(empVo);
    }
    @RequestMapping("empInfo")
    @ResponseBody
    public ResultObj empInfo(EmpVo empVo){
        try{
            empService.updateEmpInfo(empVo);
            return ResultObj.UPDATE_SUCCESS;
        }catch(Exception e){
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 校对员工编号和密码
     * @param empVo
     * @return
     */
    @RequestMapping("checkOldPwd")
    @ResponseBody
    public ResultObj checkOldPwd(EmpVo empVo){
        try{
            String md5Pwd = new Md5Hash(empVo.getPwd()).toString();
            Emp dbEmp = empService.checkEmpPwd(empVo.getEmpCode(),md5Pwd);
            if(dbEmp == null){
                System.out.println("查询结果为空");
                return ResultObj.FIND_EMP_PWD_ERROR;
            }
            return ResultObj.FIND_EMP_PWD_SUCCESS;
        }catch(Exception e){
            return ResultObj.FIND_EMP_PWD_ERROR;
        }
    }

    /**
     * 修改员工密码
     * @param empVo
     * @return
     */
    @RequestMapping("updateEmpPwd")
    @ResponseBody
    public ResultObj updateEmpPwd(EmpVo empVo){
        try{
            System.out.println(empVo.getNewPwd()+"=="+empVo.getEmpCode());
            String newPwd = new Md5Hash(empVo.getNewPwd()).toString();
            empService.updateEmpPwd(empVo.getEmpCode(),newPwd);
            /**
             * 记录日志
             */
            saveLog();
            return ResultObj.FIND_EMP_PWD_SUCCESS;
        }catch(Exception e){
            return ResultObj.FIND_EMP_PWD_ERROR;
        }
    }

    /**
     * 保存日志记录
     */
    public void saveLog(){
        //获取到当前线程绑定的请求对象
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //已经拿到session,就可以拿到session中保存的用户信息了。
        Emp sessionEmp = (Emp)request.getSession().getAttribute("emp");
        LogVo logVo = new LogVo();
        logVo.setLogName(request.getRequestURI()); // 设置日志名称
        logVo.setLogTime(new Date()); // 设置日志记录时间
        logVo.setAssociated(sessionEmp.getEmpName()); // 设置触发人姓名
        logVo.setLoginIp(request.getRemoteAddr()); // 设置访问IP地址
        logService.addLogInfo(logVo);
    }
}
