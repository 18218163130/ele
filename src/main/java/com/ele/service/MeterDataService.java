package com.ele.service;

import com.ele.entity.MeterData;
import com.ele.utils.DataGridView;
import com.ele.vo.AnalyMonthConsumeVo;
import com.ele.vo.MeterDataVo;

import java.util.Date;
import java.util.List;

/**
 * 电表数据业务接口
 *
 * @Author dongwf
 * @Date 2019/10/17
 */
public interface MeterDataService {
    /**
     * 添加新的电表记录数据
     *
     * @param meterDataVo
     */
    void addMeterData(MeterDataVo meterDataVo);

    /**
     * 根据id删除电表记录数据
     *
     * @param dataId
     */
    void deleteMeterData(Integer dataId);

    /**
     * 批量删除电表记录数据
     *
     * @param ids
     */
    void deleteBatchMeterData(Integer[] ids);

    /**
     * 更新电表记录数据
     *
     * @param meterDataVo
     */
    void updateMeterData(MeterDataVo meterDataVo);

    /**
     * 分页查询所有电表记录数据
     *
     * @param meterDataVo
     * @return
     */
    DataGridView queryAllMeterData(MeterDataVo meterDataVo);

    /**
     * 根据电表编号查询最近一条电表的额总数
     *
     * @param meterId
     * @return
     */
    MeterData queryLastDisplayNumberByMeterId(Integer meterId);

    /**
     * 根据电表id查询所有记录，并已升序也分返回
     *
     * @param meterDataVo
     * @return
     */
    DataGridView queryMeterDataByMeterId(MeterDataVo meterDataVo);

    /**
     * 每月分耗电总数
     *
     * @return
     */
    AnalyMonthConsumeVo analyMothConsume(String year);

    /**
     * 分用电类型统计
     *
     * @param
     * @return
     */
    AnalyMonthConsumeVo analyState(String year);

    /**
     * 查询yyyy-MM月份的数据
     *
     * @param meterDataVo
     * @return
     */
    List<MeterData> queryCalculationFee(MeterDataVo meterDataVo);

    /**
     * 判断该月是否已经抄表
     *
     * @param recordMonth
     * @return
     */
    MeterData checkRecordMonth(Date recordMonth);

    int updateStateByDataId(Integer dataId);

    List<MeterData> selectByState(String recordMonth);

    MeterData queryById(Integer dataId);


}
