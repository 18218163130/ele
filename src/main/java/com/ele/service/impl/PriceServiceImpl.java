package com.ele.service.impl;

import com.ele.entity.Price;
import com.ele.mapper.PriceMapper;
import com.ele.service.PriceService;
import com.ele.utils.DataGridView;
import com.ele.vo.PriceVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author dongwf
 * @Date 2020/2/12
 */
@Service
@Transactional
public class PriceServiceImpl implements PriceService {

    @Autowired
    private PriceMapper priceMapper;

    @Override
    public void setYearMonthPirce(PriceVo priceVo) {
        priceMapper.insert(priceVo);
    }

    @Override
    public void updateYearMonthPirce(PriceVo priceVo) {
        priceMapper.update(priceVo);
    }

    @Override
    public void deleteYearMonthPirce(PriceVo priceVo) {
        priceMapper.delete(priceVo.getYearMonth());
    }

    @Override
    public DataGridView queryYearMonthPirce(PriceVo priceVo) {
        Page<Object> page = PageHelper.startPage(priceVo.getPage(), priceVo.getLimit());
        List<Price> pricesList = priceMapper.queryPrice(priceVo);
        return new DataGridView(page.getTotal(), pricesList);
    }
}
