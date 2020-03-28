package com.ele.controller;

import com.ele.service.FacilityService;
import com.ele.service.UserFacilityService;
import com.ele.utils.DataGridView;
import com.ele.utils.ResultObj;
import com.ele.vo.FacilityVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 电力设备操作
 *
 * @Author dongwf
 * @Date 2019/12/20
 */
@Controller
@RequestMapping("facility")
public class FacilityController {

    @Autowired
    private UserFacilityService userFacilityService;

    @Autowired
    private FacilityService facilityService;

    /**
     * 添加设备种类信息
     *
     * @param facilityVo
     * @return
     */
    @RequestMapping("addFacility")
    @ResponseBody
    public ResultObj buyFacility(FacilityVo facilityVo) {
        try {
            facilityService.addFacility(facilityVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 查询电力设备信息列表
     *
     * @param facilityVo
     * @return
     */
    @RequestMapping("queryFacility")
    @ResponseBody
    public DataGridView queryFacility(FacilityVo facilityVo) {
        return facilityService.queryFacility(facilityVo);
    }

    /**
     * 删除电力设备
     *
     * @param facilityVo
     * @return
     */
    @RequestMapping("deleteFacility")
    @ResponseBody
    public ResultObj deleteFaciltiy(FacilityVo facilityVo) {
        try {
            facilityService.deleteFacility(facilityVo.getFacId());
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 编辑电力设备信息
     *
     * @param facilityVo
     * @return
     */
    @RequestMapping("editFacility")
    @ResponseBody
    public ResultObj editFacility(FacilityVo facilityVo) {
        try {
            facilityService.editFacility(facilityVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 查询设备列表信息
     *
     * @return
     */
    @RequestMapping("findFacilityType")
    @ResponseBody
    public DataGridView findFacilityType() {
        try {
            return facilityService.findFacType();
        } catch (Exception e) {
            return null;
        }

    }

    /**
     * 按分类查询设备种类列表
     *
     * @param facilityVo
     * @return
     */
    @RequestMapping("findFacType2")
    @ResponseBody
    public DataGridView findFacType2(FacilityVo facilityVo) {
        try {
            return facilityService.findFacType2(facilityVo.getFacType());
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 分析设备列表
     * @param year
     * @return
     */
    @RequestMapping("analyFacilityList")
    @ResponseBody
    public DataGridView analyFacilityList(@RequestParam("year")String year){
        return facilityService.analyFacilityList(year);
    }


}
