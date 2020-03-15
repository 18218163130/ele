package com.ele.controller;

import com.ele.service.MarketService;
import com.ele.utils.DataGridView;
import com.ele.utils.ResultObj;
import com.ele.vo.MarketVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author dongwf
 * @Date 2019/11/3
 */
@Controller
@RequestMapping("market")
public class MarketController {

    @Autowired
    private MarketService marketService;

    /**
     * 查询营销信息类表
     *
     * @param marketVo
     * @return
     */
    @RequestMapping("queryMarket")
    @ResponseBody
    public DataGridView queryAllMarket(MarketVo marketVo) {
        return marketService.queryAllMarket(marketVo);
    }

    /**
     * 添加营销信息
     *
     * @param marketVo
     * @return
     */
    @RequestMapping("addMarket")
    @ResponseBody
    public ResultObj addMarket(MarketVo marketVo) {
        try {
            marketService.addMarket(marketVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 修改营销信息
     *
     * @param marketVo
     * @return
     */
    @RequestMapping("editMarket")
    @ResponseBody
    public ResultObj editMarket(MarketVo marketVo) {
        try {
            marketService.editMarket(marketVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 删除营销信息
     *
     * @param marketVo
     * @return
     */
    @RequestMapping("deleteMatket")
    @ResponseBody
    public ResultObj deleteMarket(MarketVo marketVo) {
        try {
            marketService.deleteMarket(marketVo.getMarketId());
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 批量删除
     *
     * @param marketVo
     * @return
     */
    @RequestMapping("deleteBatchMarket")
    @ResponseBody
    public ResultObj deleteBatchMarket(MarketVo marketVo) {
        try {
            marketService.deleteBatchMarket(marketVo.getIds());
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            return ResultObj.DELETE_ERROR;
        }

    }
}
