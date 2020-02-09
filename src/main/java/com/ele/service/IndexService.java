package com.ele.service;

import com.ele.utils.DataGridView;
import com.ele.vo.IndexVo;

/**
 * 指标管理业务接口
 * @Author dongwf
 * @Date 2019/10/15
 */
public interface IndexService {
    /**
     * 添加一条新的指标
     * @param indexVo
     */
    void addIndex(IndexVo indexVo);

    /**
     * 修改已有的指标记录信息
     * @param indexVo
     */
    void updateIndex(IndexVo indexVo);

    /**
     * 根据id删除指标记录信息
     * @param indexId
     */
    void deleteIndexById(Integer indexId);

    /**
     * 批量删除记录信息
     */
    void deleteBatchIndex(Integer[] ids);

    /**
     * 查询记录信息列表
     * @param indexVo
     * @return
     */
    DataGridView queryAllIndex(IndexVo indexVo);
}
