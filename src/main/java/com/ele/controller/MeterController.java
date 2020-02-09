package com.ele.controller;

import com.ele.entity.User;
import com.ele.service.MeterService;
import com.ele.service.UserService;
import com.ele.utils.DataGridView;
import com.ele.utils.ResultObj;
import com.ele.vo.MeterVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * 电表操作
 * @Author dongwf
 * @Date 2019/10/17
 */
@Controller
@RequestMapping("meter")
public class MeterController {

    @Autowired
    private MeterService meterService;
    @Autowired
    private UserService userService;

    /**
     * 根据客户账号查询所属电表信息
     * @param meterVo
     * @return
     */
    @RequestMapping("queryMeterByUserId")
    @ResponseBody
    public DataGridView queryMeterByUserId(MeterVo meterVo){
        return meterService.queryMeterByUserId(meterVo);
    }

    /**
     * 为客户分配电表
     * @param meterVo
     * @return
     */
    @RequestMapping("addMeter")
    @ResponseBody
    public ResultObj addMeter(MeterVo meterVo){
        try{
            // 判断客户账号
            User dbUser = userService.getUserById(meterVo.getUserId());
            if(dbUser==null){
                return ResultObj.NO_USERID_ERROR;
            }
            meterVo.setStartDate(new Date());
            meterService.addMeter(meterVo);
            return ResultObj.ADD_SUCCESS;
        }catch(Exception e){
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 查询电表信息列表
     * @param meterVo
     * @return
     */
    @RequestMapping("meterList")
    @ResponseBody
    public DataGridView meterList(MeterVo meterVo){
       return meterService.queryAllMeter(meterVo);
    }

    /**
     * 修改电表信息
     * @param meterVo
     * @return
     */
    @RequestMapping("updateMeter")
    @ResponseBody
    public ResultObj updateMeter(MeterVo meterVo){
        try{
            meterService.updateMeter(meterVo);
            return ResultObj.UPDATE_SUCCESS;
        }catch(Exception e){
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 删除电表信息记录
     * @param meterVo
     * @return
     */
    @RequestMapping("deleteMeter")
    @ResponseBody
    public ResultObj deleteMeter(MeterVo meterVo){
        try{
            meterService.deleteMeterById(meterVo.getMeterId());
            return ResultObj.DELETE_SUCCESS;
        }catch(Exception e){
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 批量删除电表记录信息
     * @param meterVo
     * @return
     */
    @RequestMapping("deleteBatchMeter")
    @ResponseBody
    public ResultObj deleteBatchMeter(MeterVo meterVo){
        try{
            meterService.deleteBatchMeter(meterVo.getIds());
            return ResultObj.DELETE_SUCCESS;
        }catch(Exception e){
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 更改电容
     * @param meterVo
     * @return
     */
    @RequestMapping("changeMeter")
    @ResponseBody
    public ResultObj addCapacity(MeterVo meterVo){
        try{
            // 扩容
            if(meterVo.getNewType()>meterVo.getType()){
                meterVo.setType(meterVo.getNewType());
                meterService.changeCapaticy(meterVo);
                return ResultObj.ADD_CAPACITY_SUCCESS;
            }else{
                meterVo.setType(meterVo.getNewType());
                meterService.changeCapaticy(meterVo);
                return ResultObj.JIAN_CAPACITY_SUCCESS;
            }
        }catch (Exception e){
            return ResultObj.CHANGE_CAPACITY_ERROR;
        }
    }

    /**
     * 过户操作
     * @param meterVo
     * @return
     */
    @RequestMapping("guohuMeter")
    @ResponseBody
    public ResultObj guohuMeter(MeterVo meterVo){
        try{
            meterVo.setUserId(meterVo.getNewUserId());
            meterService.guohu(meterVo);
            return ResultObj.GUOHU_SUCCESS;
        }catch (Exception e){
            return ResultObj.GUOHU_ERROR;
        }
    }
}
