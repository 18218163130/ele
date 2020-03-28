package com.ele.service;

import com.ele.entity.Fee;
import com.ele.utils.DataGridView;
import com.ele.vo.AnalyEmpSoleVo;
import com.ele.vo.AnalyFeeVo;
import com.ele.vo.FeeVo;

/**
 * 电费单业务处理接口
 *
 * @Author dongwf
 * @Date 2019/10/23
 */
public interface FeeService {
    /**
     * 生成电费单
     *
     * @param fee
     */
    void createFee(Fee fee);

    /**
     * 查询电费表单列表
     *
     * @return
     */
    DataGridView queryAllFee(FeeVo feeVo);

    /**
     * 删除电费单
     *
     * @param feeId
     */
    void deleteFee(String feeId);

    /**
     * 批量删除电费单
     *
     * @param feeIds
     */
    void deleteBatchFee(String[] feeIds);

    /**
     * 缴费
     *
     * @param feeVo
     */
    void payFee(FeeVo feeVo);

    /**
     * 统计电费营销额
     *
     * @param year
     * @return
     */
    AnalyFeeVo analyFee(String year);

    /**
     * 统计员工销售额
     *
     * @param yearMonth
     * @return
     */
    AnalyEmpSoleVo analyEmpSole(String yearMonth);

    AnalyEmpSoleVo analyEmpSole2(String yearMonth);


    AnalyFeeVo analyFeeYM();

    DataGridView analyFeeList(String year);

    DataGridView analyCompanyList();
}
