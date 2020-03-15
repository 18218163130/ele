package com.ele.service.impl;

import com.ele.entity.Discount;
import com.ele.mapper.DiscountMapper;
import com.ele.service.DiscountService;
import com.ele.utils.DataGridView;
import com.ele.vo.DiscountVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author dongwf
 * @Date 2019/11/7
 */
@Service
@Transactional
public class DiscountServiceImpl implements DiscountService {

    @Autowired
    private DiscountMapper discountMapper;

    @Override
    public void addDiscount(DiscountVo discountVo) {
        discountMapper.insert(discountVo);
    }

    @Override
    public void deleteDiscount(Integer discountId) {
        discountMapper.delete(discountId);
    }

    @Override
    public void deleteBatchDiscount(Integer[] ids) {
        for (Integer id : ids) {
            this.deleteDiscount(id);
        }
    }

    @Override
    public void updateDiscount(DiscountVo discountVo) {
        discountMapper.update(discountVo);
    }

    @Override
    public DataGridView queryDiscount(DiscountVo discountVo) {
        Page page = PageHelper.startPage(discountVo.getPage(), discountVo.getLimit());
        List<Discount> discountList = discountMapper.queryDiscount(discountVo);
        return new DataGridView(page.getTotal(), discountList);
    }

    @Override
    public List<Discount> queryDiscountByState() {
        return discountMapper.queryDiscountByState();
    }
}
