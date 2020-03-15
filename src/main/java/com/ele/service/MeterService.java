package com.ele.service;

import com.ele.utils.DataGridView;
import com.ele.vo.MeterVo;

/**
 * 电表业务接口
 *
 * @Author dongwf
 * @Date 2019/10/17
 */
public interface MeterService {
    /**
     * 添加电表信息
     *
     * @param meterVo
     */
    void addMeter(MeterVo meterVo);

    /**
     * 查询所有电表信息列表
     *
     * @param meterVo
     * @return
     */
    DataGridView queryAllMeter(MeterVo meterVo);

    /**
     * 修改电表信息
     *
     * @param meterVo
     */
    void updateMeter(MeterVo meterVo);

    /**
     * 删除电表信息
     *
     * @param meterId
     */
    void deleteMeterById(Integer meterId);

    /**
     * 批量删除电表信息
     *
     * @param ids
     */
    void deleteBatchMeter(Integer[] ids);

    /**
     * 查询客户电表列表
     *
     * @param meterVo
     * @return
     */
    DataGridView queryMeterByUserId(MeterVo meterVo);

    /**
     * 更改电流容量
     *
     * @param meterVo
     */
    void changeCapaticy(MeterVo meterVo);

    /**
     * 过户
     *
     * @param meterVo
     */
    void guohu(MeterVo meterVo);
}
