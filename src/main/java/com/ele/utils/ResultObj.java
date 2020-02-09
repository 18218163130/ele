package com.ele.utils;

import com.ele.config.SysConstast;

/**
 * 作为封装Controller返回的操作结果
 * @Author dongwf
 * @Date 2019/10/10
 */
public class ResultObj {



    private Integer code = 0;
    private String msg;

    /**
     * 添加成功
     */
    public static final ResultObj ADD_SUCCESS=new ResultObj(SysConstast.CODE_SUCCESS, SysConstast.ADD_SUCCESS);
    /**
     * 添加失败
     */
    public static final ResultObj ADD_ERROR=new ResultObj(SysConstast.CODE_ERROR, SysConstast.ADD_ERROR);
    /**
     * 更新成功
     */
    public static final ResultObj UPDATE_SUCCESS=new ResultObj(SysConstast.CODE_SUCCESS, SysConstast.UPDATE_SUCCESS);
    /**
     * 更新失败
     */
    public static final ResultObj UPDATE_ERROR=new ResultObj(SysConstast.CODE_ERROR, SysConstast.UPDATE_ERROR);
    /**
     * 删除成功
     */
    public static final ResultObj DELETE_SUCCESS=new ResultObj(SysConstast.CODE_SUCCESS, SysConstast.DELETE_SUCCESS);
    /**
     * 删除失败
     */
    public static final ResultObj DELETE_ERROR=new ResultObj(SysConstast.CODE_ERROR, SysConstast.DELETE_ERROR);
    /**
     * 重置密码失败
     */
    public static final ResultObj RESET_PWD_ERROR=new ResultObj(SysConstast.CODE_ERROR, SysConstast.RESET_ERROR);
    /**
     * 重置密码成功
     */
    public static final ResultObj RESET_PWD_SUCCESS=new ResultObj(SysConstast.CODE_SUCCESS, SysConstast.RESET_SUCCESS);
    /**
     * 注册员工成功
     */
    public static final ResultObj EMPCODE_SUCCESS=new ResultObj(SysConstast.CODE_SUCCESS, SysConstast.REGISTER_SUCCESS);
    /**
     * 注册员工编号已经存在
     */
    public static final ResultObj EMPCODE_EXIS=new ResultObj(SysConstast.CODE_ERROR, SysConstast.REGISTER_EMPCODE_ERROR);

    /**
     * 注册员工失败
     */
    public static final ResultObj EMPCODE_ERROR=new ResultObj(SysConstast.CODE_ERROR, SysConstast.REGISTER_ERROR);
    /**
     * 员工密码错误
     */
    public static final ResultObj FIND_EMP_PWD_ERROR = new ResultObj(SysConstast.CODE_ERROR,SysConstast.EMP_PWD_ERROR);
    /**
     * 员工密码正确
     */
    public static final ResultObj FIND_EMP_PWD_SUCCESS = new ResultObj(SysConstast.CODE_SUCCESS,SysConstast.EMP_PWD_SUCCESS);
    /**
     * 生成电费单成功
     */
    public static final ResultObj  CREATE_FEE_SUCCESS = new ResultObj(SysConstast.CODE_SUCCESS,SysConstast.CREATE_FEE_SUCCESS);
    public static final ResultObj  CREATE_FEE_ERROR = new ResultObj(SysConstast.CODE_ERROR,SysConstast.CREATE_FEE_ERROR);

    /**
     * 缴费成功失败
     */
    public static final ResultObj  PAY_FEE_SUCCESS = new ResultObj(SysConstast.CODE_SUCCESS,SysConstast.PAY_FEE_SUCCESS);
    public static final ResultObj  PAY_FEE_ERROR = new ResultObj(SysConstast.CODE_ERROR,SysConstast.PAY_FEE_ERROR);
    /**
     * 导出Excel
     */
    public static final ResultObj  EXPORT_SUCCESS = new ResultObj(SysConstast.CODE_SUCCESS,SysConstast.EXPORT_SUCCESS);
    public static final ResultObj  EXPORT_ERROR = new ResultObj(SysConstast.CODE_ERROR,SysConstast.EXPORT_ERROR);
    /**
     * 参加优惠活动
     */
    public static final ResultObj JOIN_ALREADY = new ResultObj(SysConstast.CODE_ERROR,SysConstast.JOIN_ALREADY);
    public static final ResultObj JOIN_SUCCESS = new ResultObj(SysConstast.CODE_SUCCESS,SysConstast.JOIN_SUCCESS);
    public static final ResultObj JOIN_ERROR = new ResultObj(SysConstast.CODE_ERROR,SysConstast.JOIN_ERROR);

    /**
     * 客户账号或姓名错误
     */
    public static final ResultObj USERID_USERNAME_ERROR = new ResultObj(SysConstast.CODE_ERROR,SysConstast.USERID_USERNAME_ERROR);
    /**
     * 客户账号不存在
     */
    public static final ResultObj NO_USERID_ERROR = new ResultObj(SysConstast.CODE_ERROR,SysConstast.NO_USERID_ERROR);

    /*
     *增容/减容
     */
    public static final ResultObj ADD_CAPACITY_SUCCESS = new ResultObj(SysConstast.CODE_SUCCESS,SysConstast.ADD_CAPACITY_SUCCESS);
    public static final ResultObj CHANGE_CAPACITY_ERROR = new ResultObj(SysConstast.CODE_ERROR,SysConstast.CHANGE_CAPACITY_ERROR);
    public static final ResultObj JIAN_CAPACITY_SUCCESS = new ResultObj(SysConstast.CODE_SUCCESS,SysConstast.JIAN_CAPACITY_SUCCESS);

    /**
     * 过户
     */
    public static final ResultObj GUOHU_SUCCESS = new ResultObj(SysConstast.CODE_SUCCESS,SysConstast.GUOHU_SUCCESS);
    public static final ResultObj GUOHU_ERROR = new ResultObj(SysConstast.CODE_SUCCESS,SysConstast.GUOHU_ERROR);

    public static final ResultObj METERDATA_NUMBER_ERROR = new ResultObj(SysConstast.CODE_ERROR,SysConstast.METERDATA_NUMBER_ERROR);
    public static final ResultObj HAS_CHAOBIAO_ERROR = new ResultObj(SysConstast.CODE_ERROR,SysConstast.HAS_CHAOBIAO_ERROR);
    public ResultObj() {

    }
    public ResultObj(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
