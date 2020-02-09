package com.ele.controller;

import com.ele.config.SysConstast;
import com.ele.entity.Activity;
import com.ele.entity.Discount;
import com.ele.entity.User;
import com.ele.service.ActivityService;
import com.ele.service.DiscountService;
import com.ele.service.UserService;
import com.ele.utils.ResultObj;
import com.ele.vo.ActivityVo;
import com.ele.vo.UserVo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * 门户页面
 * @Author dongwf
 * @Date 2019/11/15
 */
@Controller
@RequestMapping("front")
public class FrontController {

    @Autowired
    private UserService userService;
    @Autowired
    private DiscountService discountService;
    @Autowired
    private ActivityService activityService;

    /**
     * 客户登录
     * @param userVo
     * @param model
     * @param session
     * @return
     */
    @PostMapping("login")
    public String login(UserVo userVo, Model model, HttpSession session){
            //获取到当前线程绑定的请求对象
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            // 判断验证码是否正确
            String code = (String)request.getSession().getAttribute("code");
            if(!code.equalsIgnoreCase(userVo.getCode())){
                model.addAttribute("msg",SysConstast.LOGIN_CODE_ERROR);
                return "front/front_login";
            }
            // 将密码转换成MD5
            String md5Pwd = new Md5Hash(userVo.getPwd()).toString();
            userVo.setPwd(md5Pwd);
            User dbUser = userService.login(userVo);
            if(dbUser != null){
                session.setAttribute("user",dbUser);
                model.addAttribute("msg",SysConstast.USER_LOGIN_SUCCESS);
                return "front/front_index";
            }
            model.addAttribute("msg",SysConstast.USER_LOGIN_ERROR);
            return "front/front_login";

    }

    /**
     * 退出登录
     * @return
     */
    @RequestMapping("front_out")
    public String out(){
        try{
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            User user = (User)request.getSession().getAttribute("user");
            if(user !=null){
                request.getSession().removeAttribute("user");
            }
            return "front/front_index";
        }catch(Exception e){
            return "front/front_index";
        }
    }

    /**
     * 查询优惠信息列表
     */
    @RequestMapping("queryDiscount")
    @ResponseBody
    public List<Discount> queryDiscount(){
        List<Discount> discounts = discountService.queryDiscountByState();
        return discounts;
    }

    /**
     * 参加优惠活动报名
     * @param activityVo
     * @return
     */
    @RequestMapping("joinActivity")
    @ResponseBody
    public ResultObj joinActivity(ActivityVo activityVo){
        try{
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            User user = (User) request.getSession().getAttribute("user");
            if (user != null) {
                // 判断是否已经报名
                Activity dbActivity = activityService.findActivity(activityVo.getDiscountId(),user.getUserId());
                if (dbActivity != null) {
                    return ResultObj.JOIN_ALREADY;
                }
                activityVo.setUserId(user.getUserId());
                activityVo.setJoinTime(new Date());
                activityService.joinActivity(activityVo);
                return ResultObj.JOIN_SUCCESS;
            }
            return ResultObj.JOIN_ERROR;
        }catch(Exception e){
            return ResultObj.JOIN_ERROR;
        }
    }

    /**
     * 校对员工编号和密码
     * @param empVo
     * @return
     */
    @RequestMapping("checkOldPwd")
    @ResponseBody
    public ResultObj checkOldPwd(UserVo userVo){
        try{
            System.out.println(userVo.getUserId()+"="+userVo.getPwd());
            String md5Pwd = new Md5Hash(userVo.getPwd()).toString();
            User dbUser = userService.checkUserPwd(userVo.getUserId(),md5Pwd);
            if(dbUser == null){
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
    @RequestMapping("updateUserPwd")
    @ResponseBody
    public ResultObj updateUserPwd(UserVo userVo){
        try{
            String newPwd = new Md5Hash(userVo.getNewPwd()).toString();
            userService.updateUserPwd(userVo.getUserId(),newPwd);
            return ResultObj.FIND_EMP_PWD_SUCCESS;
        }catch(Exception e){
            return ResultObj.FIND_EMP_PWD_ERROR;
        }
    }


}
