package com.ele.service;

import com.ele.utils.DataGridView;
import com.ele.vo.UserFacilityVo;
import org.apache.ibatis.annotations.Update;

/**
 * @Author dongwf
 * @Date 2019/12/28
 */
public interface UserFacilityService {
    /**
     * 添加客户所需要的设备信息
     * @param userFacilityVo
     */
    void addUserFac(UserFacilityVo userFacilityVo);

    /**
     * 查询设备开通列表
     * @param userFacilityVo
     * @return
     */
    DataGridView queryFacilityList(UserFacilityVo userFacilityVo);

    /**
     * 批量删除安装设备信息
     * @param ids
     */
    void delBatchFcility(Integer[] ids);

    /**
     * 删除
     * @param userFacId
     */
    void deleteUserFacility(Integer userFacId);

    /**
     * 缴费
     * @param userFacId
     */
    void jiaofei(Integer userFacId);
}
