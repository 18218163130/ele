package com.ele.service.impl;

import com.ele.entity.Fee;
import com.ele.mapper.FeeMapping;
import com.ele.service.FeeService;
import com.ele.utils.DataGridView;
import com.ele.vo.AnalyEmpSoleVo;
import com.ele.vo.AnalyFeeVo;
import com.ele.vo.FeeVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 电费单实现类
 *
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
        Page page = PageHelper.startPage(feeVo.getPage(), feeVo.getLimit());
        List<Fee> feeList = feeMapping.queryAllFee(feeVo);
        return new DataGridView(page.getTotal(), feeList);
    }

    @Override
    public void deleteFee(String feeId) {
        feeMapping.deleteFeeById(feeId);
    }

    @Override
    public void deleteBatchFee(String[] feeIds) {
        for (String id : feeIds) {
            this.deleteFee(id);
        }
    }

    @Override
    public void payFee(FeeVo feeVo) {
        feeMapping.updateFee(feeVo);
    }

    @Override
    public AnalyFeeVo analyFee(String year) {
        List<AnalyFeeVo> list = feeMapping.analyFee(year);
        List<AnalyFeeVo> listJT = feeMapping.analyFeeJT(year);
        List<AnalyFeeVo> listGY = feeMapping.analyFeeGY(year);
        List<AnalyFeeVo> listSY = feeMapping.analyFeeSY(year);
        // 找到最长的集合
        List<AnalyFeeVo> tempList = null;
        int tempMax = 0;
        if (list.size() >= listJT.size()) {
            tempMax = list.size();
        } else {
            tempMax = listJT.size();
        }
        int max = 0;
        if (listGY.size() >= listSY.size()) {
            max = listGY.size();
        } else {
            max = listSY.size();
        }
        tempMax = tempMax >= max ? tempMax : max;

        AnalyFeeVo analyFeeVo = new AnalyFeeVo(tempMax, true);
        String[] monthsName = analyFeeVo.getMonthsName();
        Object[] totalsValue = analyFeeVo.getTotalsValue();
        for (int i = 0; i < list.size(); i++) {
            monthsName[i] = list.get(i).getMonths() + "月";
            totalsValue[i] = list.get(i).getTotals();
        }
        for (int i = 0; i < listJT.size(); i++) {
            analyFeeVo.getJts()[i] = listJT.get(i).getTotals();
        }
        for (int i = 0; i < listGY.size(); i++) {
            analyFeeVo.getGys()[i] = listGY.get(i).getTotals();
        }
        for (int i = 0; i < listSY.size(); i++) {
            analyFeeVo.getSys()[i] = listSY.get(i).getTotals();
        }
        return analyFeeVo;
    }

    /**
     * 统计员工销售额
     *
     * @param yearMonth
     * @return
     */
    @Override
    public AnalyEmpSoleVo analyEmpSole(String yearMonth) {
        List<AnalyEmpSoleVo> analyEmpSoleVos = feeMapping.analyEmpSole(yearMonth);
        AnalyEmpSoleVo analyEmpSoleVo = new AnalyEmpSoleVo(analyEmpSoleVos.size());
        String[] empNames = analyEmpSoleVo.getEmpNames();
        Object[] values = analyEmpSoleVo.getValues();

        for (int i = 0; i < analyEmpSoleVos.size(); i++) {
            // {value: 335, name: '直接访问'}
            Map<String, String> map = new HashMap<>();
            map.put("value", analyEmpSoleVos.get(i).getEmpCode());
            map.put("name", analyEmpSoleVos.get(i).getEmpName());
            values[i] = map;
            empNames[i] = analyEmpSoleVos.get(i).getEmpName();
        }

        return analyEmpSoleVo;
    }

    @Override
    public AnalyEmpSoleVo analyEmpSole2(String yearMonth) {
        List<AnalyEmpSoleVo> analyEmpSoleVos = feeMapping.analyEmpSole(yearMonth);
        AnalyEmpSoleVo analyEmpSoleVo = new AnalyEmpSoleVo(analyEmpSoleVos.size());
        String[] empNames = analyEmpSoleVo.getEmpNames();
        Float[] prizes = analyEmpSoleVo.getPrizes();

        for (int i = 0; i < analyEmpSoleVos.size(); i++) {
            prizes[i] = analyEmpSoleVos.get(i).getTotals();
            empNames[i] = analyEmpSoleVos.get(i).getEmpCode() + " " + analyEmpSoleVos.get(i).getEmpName();
            System.out.println(prizes[i] + " " + empNames[i]);
        }
        return analyEmpSoleVo;
    }

    @Override
    public AnalyFeeVo analyFeeYM() {
        // 电费
        List<AnalyFeeVo> feeList = feeMapping.analyFeeYM();
        AnalyFeeVo analyFeeVo = new AnalyFeeVo(feeList.size());
        String[] monthsName = analyFeeVo.getMonthsName();
        Object[] totalsValue = analyFeeVo.getTotalsValue();
        for (int i = 0; i < feeList.size(); i++) {
            monthsName[i] = feeList.get(i).getMonths();
            totalsValue[i] = feeList.get(i).getTotals();
        }
        return analyFeeVo;
    }
}
