package com.ele.controller;

import com.ele.service.IndexService;
import com.ele.utils.DataGridView;
import com.ele.utils.ResultObj;
import com.ele.vo.IndexVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * 指标管理操作
 * @Author dongwf
 * @Date 2019/10/15
 */
@Controller
@RequestMapping("ind")
public class IndexController {
    @Autowired
    private IndexService indexService;

    /**
     * 添加新的指标
     * @param indexVo
     * @return
     */
    @RequestMapping("addIndex")
    @ResponseBody
    public ResultObj addIndex(IndexVo indexVo){
        try{
            System.out.println(indexVo.toString());
            indexService.addIndex(indexVo);
            return ResultObj.ADD_SUCCESS;
        }catch(Exception e){
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 查询指标列表
     * @param indexVo
     * @return
     */
    @RequestMapping("loadAllIndex")
    @ResponseBody
    public DataGridView loadAllIndex(IndexVo indexVo){
        return indexService.queryAllIndex(indexVo);
    }

    /**
     * 修改指标内容
     * @param indexVo
     * @return
     */
    @RequestMapping("updateIndex")
    @ResponseBody
    public ResultObj updateIndex(IndexVo indexVo){
        try{
            indexVo.setChangeTime(new Date());
            indexService.updateIndex(indexVo);
            return ResultObj.UPDATE_SUCCESS;
        }catch(Exception e){
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 删除指标信息
     * @param indexVo
     * @return
     */
    @RequestMapping("deleteIndex")
    @ResponseBody
    public ResultObj deleteIndex(IndexVo indexVo){
        try{
            indexService.deleteIndexById(indexVo.getIndexId());
            return ResultObj.DELETE_SUCCESS;
        }catch(Exception e){
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 批量删除指标
     * @param indexVo
     * @return
     */
    @RequestMapping("deleteBatchIndex")
    @ResponseBody
    public ResultObj deleteBatchIndex(IndexVo indexVo){
        try{
            Integer[] ids = indexVo.getIds();
            indexService.deleteBatchIndex(ids);
            return ResultObj.DELETE_SUCCESS;
        }catch(Exception e){
            return ResultObj.DELETE_ERROR;
        }
    }



}
