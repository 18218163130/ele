package com.ele.service.impl;

import com.ele.entity.UserFacility;
import com.ele.mapper.FacilityMapper;
import com.ele.mapper.UserFacilityMapper;
import com.ele.service.UserFacilityService;
import com.ele.utils.DataGridView;
import com.ele.vo.AnalyFacilityVo;
import com.ele.vo.UserFacilityVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author dongwf
 * @Date 2019/12/28
 */
@Service
@Transactional
public class UserFacilityServiceImpl implements UserFacilityService {

    @Autowired
    private UserFacilityMapper userFacilityMapper;
    @Autowired
    private FacilityMapper facilityMapper;


    @Override
    public void addUserFac(UserFacilityVo userFacilityVo) {
        userFacilityMapper.insert(userFacilityVo);
        facilityMapper.updateFacTotal(userFacilityVo.getNum(), userFacilityVo.getFacId()); // 更新库存
    }

    @Override
    public DataGridView queryFacilityList(UserFacilityVo userFacilityVo) {
        Page page = PageHelper.startPage(userFacilityVo.getPage(), userFacilityVo.getLimit());
        List<UserFacility> userFacilityList = userFacilityMapper.query(userFacilityVo);
        return new DataGridView(page.getTotal(), userFacilityList);
    }

    @Override
    public void delBatchFcility(Integer[] ids) {
        for (Integer id : ids) {
            userFacilityMapper.delete(id);
        }
    }

    @Override
    public void deleteUserFacility(Integer userFacId) {
        userFacilityMapper.delete(userFacId);
    }

    @Override
    public void jiaofei(Integer userFacId) {
        userFacilityMapper.updatePayField(userFacId);
    }

    @Override
    public AnalyFacilityVo analyFacility(String year) {
        List<AnalyFacilityVo> all = userFacilityMapper.analyFacilityAll(year);
        AnalyFacilityVo analyFacilityVo = new AnalyFacilityVo(all.size(), true);
        String[] monthsName = analyFacilityVo.getMonthsName();
        Object[] totalsValue = analyFacilityVo.getTotalsValue();
        for (int i = 0; i < all.size(); i++) {
            monthsName[i] = all.get(i).getMonths();
            totalsValue[i] = all.get(i).getTotals();
        }
        return analyFacilityVo;
    }


}
