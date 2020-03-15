package com.ele.config;

/**
 * 系统常量配置
 *
 * @Author dongwf
 * @Date 2019/10/7
 */
public interface SysConstast {

    /**
     * 登录状态
     */
    String EMP_LOGIN_ERROR_MSG = "员工编号或密码错误";
    String EMP_LOGIN_NO_ACCOUNT_MSG = "员工编号不存在";
    String EMP_DEFAULT_PWD = "123456"; // 客户创建初始密码
    String LOGIN_CODE_ERROR = "验证码错误";

    String USER_LOGIN_SUCCESS = "登录成功";
    String USER_LOGIN_ERROR = "登录失败";

    /**
     * 重置客户密码
     */
    String USER_RESET_PWD = "123456";

    /**
     * 操作状态
     */
    String ADD_SUCCESS = "添加成功";
    String ADD_ERROR = "添加失败";

    String UPDATE_SUCCESS = "更新成功";
    String UPDATE_ERROR = "更新失败";

    String DELETE_SUCCESS = "删除成功";
    String DELETE_ERROR = "删除失败";

    String RESET_SUCCESS = "重置成功";
    String RESET_ERROR = "重置失败";

    String DISPATCH_SUCCESS = "分配成功";
    String DISPATCH_ERROR = "分配失败";

    Integer CODE_SUCCESS = 0; //操作成功
    Integer CODE_ERROR = -1;//失败

    String REGISTER_SUCCESS = "注册成功";
    String REGISTER_EMPCODE_ERROR = "员工编号已经注册";
    String REGISTER_ERROR = "注册失败";

    String EMP_PWD_ERROR = "员工密码错误";
    String EMP_PWD_SUCCESS = "员工密码正确";

    String CREATE_FEE_SUCCESS = "生成电费单成功";
    String CREATE_FEE_ERROR = "生成电费单失败";

    String PAY_FEE_SUCCESS = "缴费成功";
    String PAY_FEE_ERROR = "缴费失败";

    String EXPORT_ERROR = "导出失败";
    String EXPORT_SUCCESS = "导出成功";

    String JOIN_ERROR = "优惠活动报名失败";
    String JOIN_SUCCESS = "优惠活动报名成功";
    String JOIN_ALREADY = "已经报过名";

    String USERID_USERNAME_ERROR = "客户账号或姓名错误";
    String NO_USERID_ERROR = "客户账号不存在";

    String ADD_CAPACITY_SUCCESS = "扩容成功";
    String CHANGE_CAPACITY_ERROR = "更改失败";
    String JIAN_CAPACITY_SUCCESS = "减容成功";
    String GUOHU_SUCCESS = "过户成功";
    String GUOHU_ERROR = "过户失败";
    String METERDATA_NUMBER_ERROR = "当月电表数字小于上一个月数字";
    String HAS_CHAOBIAO_ERROR = "当月已经抄表";
}
