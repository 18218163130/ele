package com.ele.controller;

import com.ele.service.LogService;
import com.ele.utils.DataGridView;
import com.ele.utils.ResultObj;
import com.ele.vo.LogVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;



/**
 * @Author dongwf
 * @Date 2019/10/12
 */
@Controller
@RequestMapping("logInfo")
public class LogController {
    @Autowired
    private LogService logService;

    /**
     * 加载所有日志记录
     * @param logVo
     * @return
     */
    @RequestMapping("loadAllLogInfo")
    @ResponseBody
    public DataGridView loadAllLogInfo(LogVo logVo){
        return logService.queryAllLog(logVo);
    }

    /**
     * 删除单个日志记录
     * @param logVo
     * @return
     */
    @RequestMapping("deleteLogInfo")
    @ResponseBody
    public ResultObj deleteLogInfo(LogVo logVo){
        try{
            logService.deleteLogInfo(logVo.getLogId());
            return ResultObj.DELETE_SUCCESS;
        }catch(Exception e){
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 批量删除日志记录
     * @param logVo
     * @return
     */
    @RequestMapping("deleteBatchLogInfo")
    @ResponseBody
    public ResultObj deleteBatchLogInfo(LogVo logVo){
        try{
            logService.deleteBatchLogInfo(logVo.getIds());
            return ResultObj.DELETE_SUCCESS;
        }catch(Exception e){
            return ResultObj.DELETE_ERROR;
        }
    }



}
