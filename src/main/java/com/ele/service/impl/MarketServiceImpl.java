package com.ele.service.impl;

import com.ele.entity.Market;
import com.ele.mapper.MarketMapper;
import com.ele.service.MarketService;
import com.ele.utils.DataGridView;
import com.ele.vo.MarketVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author dongwf
 * @Date 2019/11/3
 */
@Service
@Transactional
public class MarketServiceImpl implements MarketService {

    @Autowired
    private MarketMapper marketMapper;

    @Override
    public void addMarket(MarketVo marketVo) {
        marketMapper.insertMarket(marketVo);
    }

    @Override
    public DataGridView queryAllMarket(MarketVo marketVo) {
        // 分页
        Page page = PageHelper.startPage(marketVo.getPage(), marketVo.getLimit());
        List<Market> marketList = marketMapper.queryAll(marketVo);
        return new DataGridView(page.getTotal(), marketList);
    }

    @Override
    public void editMarket(MarketVo marketVo) {
        marketMapper.updateMarket(marketVo);
    }

    @Override
    public void deleteMarket(Integer id) {
        marketMapper.delete(id);
    }

    @Override
    public void deleteBatchMarket(Integer[] ids) {
        for (Integer id : ids) {
            this.deleteMarket(id);
        }
    }
}
