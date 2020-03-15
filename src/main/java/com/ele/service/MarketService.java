package com.ele.service;

import com.ele.utils.DataGridView;
import com.ele.vo.MarketVo;

/**
 * @Author dongwf
 * @Date 2019/11/3
 */
public interface MarketService {
    /**
     * 添加营销信息
     *
     * @param marketVo
     * @return
     */
    void addMarket(MarketVo marketVo);

    /**
     * 查询营销信息列表
     *
     * @param marketVo
     * @return
     */
    DataGridView queryAllMarket(MarketVo marketVo);

    /**
     * 修改营销信息
     *
     * @param marketVo
     */
    void editMarket(MarketVo marketVo);

    /**
     * 删除营销记录
     *
     * @param marketId
     */
    void deleteMarket(Integer marketId);

    /**
     * 批量删除营销记录
     *
     * @param ids
     */
    void deleteBatchMarket(Integer[] ids);
}
