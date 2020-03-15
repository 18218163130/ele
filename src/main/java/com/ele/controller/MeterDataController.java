package com.ele.controller;

import com.ele.entity.MeterData;
import com.ele.service.MeterDataService;
import com.ele.utils.DataGridView;
import com.ele.utils.ResultObj;
import com.ele.vo.AnalyMonthConsumeVo;
import com.ele.vo.MeterDataVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 电表数据操作
 *
 * @Author dongwf
 * @Date 2019/10/17
 */
@Controller
@RequestMapping("data")
public class MeterDataController {
    @Autowired
    private MeterDataService meterDataService;

    /**
     * 添加电表数据
     *
     * @param meterDataVo
     * @return
     */
    @RequestMapping("addMeterData")
    @ResponseBody
    public ResultObj addMeterData(MeterDataVo meterDataVo) {
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
            meterDataVo.setRecordDate(new Date());
            MeterData lastMeterData = meterDataService.queryLastDisplayNumberByMeterId(meterDataVo.getMeterId());
            // 判断是否为null，首次使用是null
            if (lastMeterData == null) {
                meterDataVo.setConsume(meterDataVo.getDisplayNumber());
                meterDataService.addMeterData(meterDataVo);
                return ResultObj.ADD_SUCCESS;
            }
            float consume = meterDataVo.getDisplayNumber() - lastMeterData.getDisplayNumber();
            if (consume < 0) { // 小于0说明本月电表数<上月数
                return ResultObj.METERDATA_NUMBER_ERROR;
            }

            // 如果日期相等，说明这个月已经抄表
            if (dateFormat.format(lastMeterData.getRecordMonth()).equals(dateFormat.format(meterDataVo.getRecordMonth()))) {
                return ResultObj.HAS_CHAOBIAO_ERROR;
            }
            meterDataVo.setConsume(consume);
            meterDataService.addMeterData(meterDataVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 查询电表数据列表
     *
     * @param meterDataVo
     * @return
     */
    @RequestMapping("listMeterData")
    @ResponseBody
    public DataGridView listMeterData(MeterDataVo meterDataVo) {
        if (meterDataVo.getRecordMonth() != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
            meterDataVo.setYearmonth(sdf.format(meterDataVo.getRecordMonth()));
        }
        return meterDataService.queryAllMeterData(meterDataVo);
    }

    /**
     * 根据电表ID查询数据记录
     *
     * @param meterDataVo
     * @return
     */
    @RequestMapping("listMeterDataDescByTime")
    @ResponseBody
    public DataGridView listMeterDataDescByTime(MeterDataVo meterDataVo) {

        return meterDataService.queryMeterDataByMeterId(meterDataVo);
    }

    /**
     * 修改电表数据
     *
     * @param meterDataVo
     * @return
     */
    @RequestMapping("updateMeterData")
    @ResponseBody
    public ResultObj updateMeterData(MeterDataVo meterDataVo) {
        try {
            meterDataVo.setRecordDate(new Date());
            MeterData lastMeterData = meterDataService.queryLastDisplayNumberByMeterId(meterDataVo.getMeterId());
            float consume = meterDataVo.getDisplayNumber() - lastMeterData.getDisplayNumber();
            if (consume < 0) { // 小于0说明本月电表数<上月数
                return ResultObj.METERDATA_NUMBER_ERROR;
            }
            meterDataVo.setConsume(consume);
            meterDataService.updateMeterData(meterDataVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 删除电表数据
     *
     * @param meterDataVo
     * @return
     */
    @RequestMapping("deleteMeterData")
    @ResponseBody
    public ResultObj deleteMeterData(MeterDataVo meterDataVo) {
        try {
            meterDataService.deleteMeterData(meterDataVo.getDataId());
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 批量删除电表数据
     *
     * @param meterDataVo
     * @return
     */
    @RequestMapping("deleteBatchMeterData")
    @ResponseBody
    public ResultObj deleteBatchMeterData(MeterDataVo meterDataVo) {
        try {
            meterDataService.deleteBatchMeterData(meterDataVo.getIds());
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 分析每月消耗电量总数
     *
     * @return
     */
    @RequestMapping("analyMonthConsume")
    @ResponseBody
    public AnalyMonthConsumeVo analyMonthConsume(@RequestParam("year") String year) {
        return meterDataService.analyMothConsume(year);
    }

    /**
     * 每个月分用电类型统计
     *
     * @return
     */
    @RequestMapping("analyState")
    @ResponseBody
    public AnalyMonthConsumeVo analyState(@RequestParam("year") String year) {
        return meterDataService.analyState(year);
    }


}
