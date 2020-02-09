package com.ele.service;

import com.ele.entity.Discount;
import com.ele.utils.DataGridView;
import com.ele.vo.DiscountVo;

import java.util.List;

/**
 * @Author dongwf
 * @Date 2019/11/7
 */
public interface DiscountService {
    /**
     * 添加优惠活动
     * @param discountVo
     */
    void addDiscount(DiscountVo discountVo);

    /**
     * 删除优惠活动
     * @param discountId
     */
    void deleteDiscount(Integer discountId);

    /**
     * 批量删除优惠活动
     * @param ids
     */
    void deleteBatchDiscount(Integer[] ids);

    /**
     * 修改编辑优惠活动
     * @param discountVo
     */
    void updateDiscount(DiscountVo discountVo);

    /**
     * 查询优惠活动信息列表
     * @param discountVo
     * @return
     */
    DataGridView queryDiscount(DiscountVo discountVo);

    /**
     * 查询优惠信息
     * @return
     */
    List<Discount> queryDiscountByState();
}
