package com.ele.service;

import com.ele.entity.Price;
import com.ele.utils.DataGridView;
import com.ele.vo.PriceVo;

/**
 * @Author dongwf
 * @Date 2020/2/12
 */
public interface PriceService {
    /**
     * 设置对应年月电费单价
     */
    void setYearMonthPirce(PriceVo priceVo);

    /**
     * 更新年月电费单价
     * @param priceVo
     */
    void updateYearMonthPirce(PriceVo priceVo);

    /**
     * 删除年月电费单价
     * @param priceVo
     */
    void deleteYearMonthPirce(PriceVo priceVo);

    /**
     * 查询年月单价
     * @param priceVo
     * @return
     */
    DataGridView queryYearMonthPirce(PriceVo priceVo);
}
