package com.ele.service.impl;

import com.ele.entity.MeterData;
import com.ele.mapper.MeterDataMapper;
import com.ele.service.MeterDataService;
import com.ele.utils.DataGridView;
import com.ele.vo.AnalyMonthConsumeVo;
import com.ele.vo.MeterDataVo;
import com.ele.vo.MeterVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 电表数据业务操作
 * @Author dongwf
 * @Date 2019/10/17
 */
@Service
@Transactional
public class MeterDataServiceImpl implements MeterDataService {

    @Autowired
    private MeterDataMapper meterDataMapper;
    @Override
    public void addMeterData(MeterDataVo meterDataVo) {
        meterDataMapper.insertMeterData(meterDataVo);
    }

    @Override
    public void deleteMeterData(Integer dataId) {
        meterDataMapper.deleteMeterData(dataId);
    }

    @Override
    public void deleteBatchMeterData(Integer[] ids) {
        for(Integer id:ids){
            this.deleteMeterData(id);
        }

    }

    @Override
    public void updateMeterData(MeterDataVo meterDataVo) {
        meterDataMapper.updateMeterData(meterDataVo);
    }

    @Override
    public DataGridView queryAllMeterData(MeterDataVo meterDataVo) {
        // 分页处理
        Page page = PageHelper.startPage(meterDataVo.getPage(),meterDataVo.getLimit());
        List<MeterData> meterDataList = meterDataMapper.queryAllMeterData(meterDataVo);
        return new DataGridView(page.getTotal(),meterDataList);
    }

    @Override
    public MeterData queryLastDisplayNumberByMeterId(Integer meterId) {
        return meterDataMapper.queryLastDisplayNumberByMeterId(meterId);
    }

    @Override
    public DataGridView queryMeterDataByMeterId(MeterDataVo meterDataVo) {
        // 分页处理
        Page page = PageHelper.startPage(meterDataVo.getPage(),meterDataVo.getLimit());
        List<MeterData> meterDataList = meterDataMapper.queryMeterDataByMeterId(meterDataVo.getMeterId());
        return new DataGridView(page.getTotal(),meterDataList);
    }



    @Override
    public AnalyMonthConsumeVo analyMothConsume(String year) {
        List<AnalyMonthConsumeVo> voList = meterDataMapper.queryMonthConsume(year);
        AnalyMonthConsumeVo vo = new AnalyMonthConsumeVo(voList.size());
        String[] monthsName = vo.getMonthsName();
        Object[] totalsValue = vo.getTotalsValue();
        for(int i=0;i<voList.size();i++){
            monthsName[i] = voList.get(i).getMonths()+"月";
            totalsValue[i] = voList.get(i).getTotals();
        }
        return vo;
    }

    @Override
    public AnalyMonthConsumeVo analyState(String year) {
        List<AnalyMonthConsumeVo> voList0 = meterDataMapper.queryState(0,year);
        List<AnalyMonthConsumeVo> voList1 = meterDataMapper.queryState(1,year);
        List<AnalyMonthConsumeVo> voList2 = meterDataMapper.queryState(2,year);
        // 找到最长的集合
        List<AnalyMonthConsumeVo> tempList = null;
        int tempMax = 0;
        if(voList0.size()>=voList1.size()){
            tempMax=voList0.size();
            tempList = voList0;
        }else{
            tempMax = voList1.size();
            tempList = voList1;
        }
        if(tempList.size()<voList2.size()){
            tempMax = voList2.size();
            tempList = voList2;
        }


        //  存储返回结果
        AnalyMonthConsumeVo vo = new AnalyMonthConsumeVo(tempMax,true);
        String[] names = vo.getMonthsName();
        for(int i=0;i<tempList.size();i++){
            names[i]= tempList.get(i).getMonths()+"月";
        }
        for(int i=0;i<voList0.size();i++){
            vo.getJts()[i] = voList0.get(i).getTotals();
        }
        for(int i=0;i<voList1.size();i++){
            vo.getSys()[i] = voList1.get(i).getTotals();
        }
        for(int i=0;i<voList2.size();i++){
            vo.getGys()[i] = voList2.get(i).getTotals();
        }
        return vo;
    }

    @Override
    public List<MeterData> queryCalculationFee(MeterDataVo meterDataVo) {
        return meterDataMapper.calculationFee(meterDataVo.getYearmonth2());
    }

    @Override
    public MeterData checkRecordMonth(Date recordMonth) {
        return meterDataMapper.checkRecordMonth(recordMonth);
    }

    @Override
    public int updateStateByDataId(Integer dataId) {
        return meterDataMapper.updateStateByYearmonth(dataId);
    }

    @Override
    public List<MeterData> selectByState(String recordMonth) {
        return meterDataMapper.selectByState(recordMonth);
    }
}
