package com.ele.controller;

import com.ele.entity.User;
import com.ele.service.UserFacilityService;
import com.ele.service.UserService;
import com.ele.utils.DataGridView;
import com.ele.utils.ResultObj;
import com.ele.vo.AnalyFacilityVo;
import com.ele.vo.UserFacilityVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author dongwf
 * @Date 2019/12/28
 */
@Controller
@RequestMapping("userFacility")
public class UserFacilityController {

    @Autowired
    private UserFacilityService userFacilityService;
    @Autowired
    private UserService userService;

    /**
     * 添加客户安装所需的设备
     *
     * @param userFacilityVo
     * @return
     */
    @RequestMapping("addUserFacility")
    @ResponseBody
    public ResultObj addUserFacility(UserFacilityVo userFacilityVo) {
        try {
            System.out.println(userFacilityVo.getCreateDate());
            // 判断客户Id和姓名
            User dbUser = userService.getUser(userFacilityVo.getUserId(), userFacilityVo.getUserName());
            if (dbUser == null) { // 客户账号或姓名错误
                return ResultObj.USERID_USERNAME_ERROR;
            }
            // 计算总价格
            float totalPrice = userFacilityVo.getPrice() * userFacilityVo.getNum();
            userFacilityVo.setTotalPrice(totalPrice);
            userFacilityVo.setIsPay(0); // 代表未支付状态
            userFacilityService.addUserFac(userFacilityVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 查询安装设备列表
     *
     * @param userFacilityVo
     * @return
     */
    @RequestMapping("queryFacilityList")
    @ResponseBody
    public DataGridView queryFacilityList(UserFacilityVo userFacilityVo) {
        return userFacilityService.queryFacilityList(userFacilityVo);
    }

    /**
     * 分析设备营销额
     *
     * @param year
     * @return
     */
    @RequestMapping("ananyFacilityMonth")
    @ResponseBody
    public AnalyFacilityVo ananyFacilityMonth(@RequestParam("year") String year) {
        return userFacilityService.analyFacility(year);
    }

    /**
     * 批量删除安装设备信息
     *
     * @return
     */
    @RequestMapping("deleteBatchFacility")
    @ResponseBody
    public ResultObj deleteBatchFacility(UserFacilityVo userFacilityVo) {
        try {
            userFacilityService.delBatchFcility(userFacilityVo.getIds());
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 删除安装登记信息
     *
     * @param userFacilityVo
     * @return
     */
    @RequestMapping("deleteUserFacility")
    @ResponseBody
    public ResultObj deleteUserFacility(UserFacilityVo userFacilityVo) {
        try {
            userFacilityService.deleteUserFacility(userFacilityVo.getUserFacId());
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 缴费
     *
     * @param userFacilityVo
     * @return
     */
    @RequestMapping("jiaofei")
    @ResponseBody
    public ResultObj jiaofei(UserFacilityVo userFacilityVo) {
        try {
            userFacilityService.jiaofei(userFacilityVo.getUserFacId());
            return ResultObj.PAY_FEE_SUCCESS;
        } catch (Exception e) {
            return ResultObj.PAY_FEE_ERROR;
        }
    }
}
