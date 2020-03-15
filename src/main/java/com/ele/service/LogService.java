package com.ele.service;

import com.ele.utils.DataGridView;
import com.ele.vo.LogVo;

/**
 * 操作日子记录业务层接口
 *
 * @Author dongwf
 * @Date 2019/10/12
 */
public interface LogService {
    /**
     * 添加日志记录信息
     *
     * @param logVo
     */
    void addLogInfo(LogVo logVo);

    /**
     * 删除日志记录
     *
     * @param logId
     */
    void deleteLogInfo(Integer logId);

    /**
     * 批量删除日志记录
     *
     * @param ids
     */
    void deleteBatchLogInfo(Integer[] ids);

    /**
     * 查询所有日志记录
     *
     * @param logVo
     * @return
     */
    DataGridView queryAllLog(LogVo logVo);

}
