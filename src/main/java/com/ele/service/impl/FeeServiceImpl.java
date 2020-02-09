package com.ele.service.impl;

import com.ele.entity.Fee;
import com.ele.mapper.FeeMapping;
import com.ele.service.FeeService;
import com.ele.utils.DataGridView;
import com.ele.vo.FeeVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 电费单实现类
 * @Author dongwf
 * @Date 2019/10/23
 */
@Service
@Transactional
public class FeeServiceImpl implements FeeService {

    @Autowired
    private FeeMapping feeMapping;

    @Override
    public void createFee(Fee fee) {
        feeMapping.insert(fee);
    }

    @Override
    public DataGridView queryAllFee(FeeVo feeVo) {
        Page page = PageHelper.startPage(feeVo.getPage(),feeVo.getLimit());
        List<Fee> feeList = feeMapping.queryAllFee(feeVo);
        return new DataGridView(page.getTotal(),feeList);
    }

    @Override
    public void deleteFee(String feeId) {
        feeMapping.deleteFeeById(feeId);
    }

    @Override
    public void deleteBatchFee(String[] feeIds) {
        for(String id:feeIds){
            this.deleteFee(id);
        }
    }

    @Override
    public void payFee(FeeVo feeVo){
        feeMapping.updateFee(feeVo);
    }
}
