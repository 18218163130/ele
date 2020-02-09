package com.ele.controller;

import com.ele.entity.Activity;
import com.ele.service.ActivityService;
import com.ele.utils.ActivityExcelUtil;
import com.ele.utils.DataGridView;
import com.ele.utils.ResultObj;
import com.ele.vo.ActivityVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author dongwf
 * @Date 2019/11/23
 */
@Controller
@RequestMapping("activity")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    /**
     * 查询报名列表
     * @param activityVo
     * @return
     */
    @RequestMapping("listActivity")
    @ResponseBody
    public DataGridView listActivity(ActivityVo activityVo){
        return activityService.findActivityList(activityVo);
    }

    /**
     * 查询活动报名
     * @param activityVo
     * @return
     */
    @RequestMapping("deleteActivity")
    @ResponseBody
    public ResultObj deleteActivity(ActivityVo activityVo){
        try{
            activityService.deleteActivityById(activityVo.getActivityId());
            return ResultObj.DELETE_SUCCESS;
        }catch(Exception e){
            return ResultObj.DELETE_ERROR;
        }

    }

    /**
     * 批量删除活动报名
     * @param activityVo
     * @return
     */
    @RequestMapping("deleteBatchActivity")
    @ResponseBody
    public ResultObj deleteBatchActivity(ActivityVo activityVo){
        try{
            activityService.deleteBatchActivity(activityVo.getIds());
            return ResultObj.DELETE_SUCCESS;
        }catch(Exception e){
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 批量导出Excel报名名单
     * @param activityVo
     * @return
     */
    @RequestMapping("exprotBatchActivity")
    @ResponseBody
    public ResultObj exprotBatchActivity(ActivityVo activityVo){
        try{
            List<Activity> activityList = activityService.batchExceportActivity(activityVo.getIds());
           ActivityExcelUtil.exprot("F:/activitys.xls",activityList);
            return ResultObj.EXPORT_SUCCESS;
        }catch(Exception e){
            return ResultObj.EXPORT_ERROR;
        }
    }
}
