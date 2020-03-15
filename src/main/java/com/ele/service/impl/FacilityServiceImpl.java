package com.ele.service.impl;

import com.ele.entity.Facility;
import com.ele.mapper.FacilityMapper;
import com.ele.service.FacilityService;
import com.ele.utils.DataGridView;
import com.ele.vo.FacilityVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 电力设备业务实现
 *
 * @Author dongwf
 * @Date 2019/12/20
 */
@Service
@Transactional
public class FacilityServiceImpl implements FacilityService {

    @Autowired
    private FacilityMapper facilityMapper;

    @Override
    public void addFacility(FacilityVo facilityVo) {
        facilityMapper.insertFacility(facilityVo);
    }

    @Override
    public DataGridView queryFacility(FacilityVo facilityVo) {
        Page page = PageHelper.startPage(facilityVo.getPage(), facilityVo.getLimit());
        List<Facility> facilityList = facilityMapper.queryFacility(facilityVo);
        return new DataGridView(page.getTotal(), facilityList);
    }

    @Override
    public void deleteFacility(Integer facId) {
        facilityMapper.deleteFacility(facId);
    }

    @Override
    public void editFacility(FacilityVo facilityVo) {
        facilityMapper.updateFacility(facilityVo);
    }

    @Override
    public DataGridView findFacType() {
        List<Facility> facilityList = facilityMapper.findFacType();
        return new DataGridView(facilityList);
    }

    @Override
    public DataGridView findFacType2(Integer facType) {
        List<Facility> facilityList = facilityMapper.findFacType2(facType);
        return new DataGridView(facilityList);
    }
}
