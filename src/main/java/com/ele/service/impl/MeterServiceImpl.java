package com.ele.service.impl;

import com.ele.entity.Meter;
import com.ele.mapper.MeterMapper;
import com.ele.service.MeterService;
import com.ele.utils.DataGridView;
import com.ele.vo.MeterVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author dongwf
 * @Date 2019/10/17
 */
@Service
@Transactional
public class MeterServiceImpl implements MeterService {

    @Autowired
    private MeterMapper meterMapper;

    @Override
    public void addMeter(MeterVo meterVo) {
        meterMapper.addMeter(meterVo);
    }

    @Override
    public DataGridView queryAllMeter(MeterVo meterVo) {
        // 进行结果分页
        Page page = PageHelper.startPage(meterVo.getPage(), meterVo.getLimit());
        List<Meter> meterList = meterMapper.queryAllMeter(meterVo);
        return new DataGridView(page.getTotal(), meterList);
    }

    @Override
    public void updateMeter(MeterVo meterVo) {
        meterMapper.updateMeter(meterVo);
    }

    @Override
    public void deleteMeterById(Integer meterId) {
        meterMapper.deleteMeter(meterId);
    }

    @Override
    public void deleteBatchMeter(Integer[] ids) {
        for (Integer id : ids) {
            this.deleteMeterById(id);
        }
    }

    @Override
    public DataGridView queryMeterByUserId(MeterVo meterVo) {
        Page page = PageHelper.startPage(meterVo.getPage(), meterVo.getLimit());
        List<Meter> meterList = meterMapper.findMeterByUserId(meterVo.getUserId());
        return new DataGridView(page.getTotal(), meterList);
    }

    @Override
    public void changeCapaticy(MeterVo meterVo) {
        meterMapper.changeCapacity(meterVo.getType(), meterVo.getMeterId());
    }

    @Override
    public void guohu(MeterVo meterVo) {
        meterMapper.updateUserId(meterVo.getUserId(), meterVo.getMeterId());
    }
}
