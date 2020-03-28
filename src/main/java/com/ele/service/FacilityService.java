package com.ele.service;

import com.ele.utils.DataGridView;
import com.ele.vo.FacilityVo;

/**
 * @Author dongwf
 * @Date 2019/12/20
 */
public interface FacilityService {
    /**
     * 添加设备种类信息
     *
     * @param facilityVo
     */
    void addFacility(FacilityVo facilityVo);

    /**
     * 查询电力设备信息
     *
     * @param facilityVo
     * @return
     */
    DataGridView queryFacility(FacilityVo facilityVo);

    /**
     * 删除电力设备信息
     *
     * @param facId
     */
    void deleteFacility(Integer facId);

    /**
     * 修改编辑电力设备信息
     *
     * @param facilityVo
     * @return
     */
    void editFacility(FacilityVo facilityVo);

    /**
     * 查询设备种类
     *
     * @return
     */
    DataGridView findFacType();

    /**
     * 按分类查询种类
     *
     * @param facType
     * @return
     */
    DataGridView findFacType2(Integer facType);

    DataGridView analyFacilityList(String year);
}
