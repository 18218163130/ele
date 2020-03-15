package com.ele.controller;

import com.ele.service.FacilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 该页面只是系统跳转页面，没有任何业务逻辑
 *
 * @Author dongwf
 * @Date 2019/10/7
 */
@Controller
public class SysController {

    @Autowired
    private FacilityService facilityService;

    /**
     * 访问main页面
     *
     * @return
     */
    @GetMapping({"main", "main.html"})
    public String toMain() {
        return "main/main";
    }

    /**
     * 访问登录页面
     *
     * @return
     */
    @GetMapping({"login", "login.html"})
    public String toLogin() {
        return "main/login";
    }

    /**
     * 转发到登录页面
     *
     * @return
     */
    @GetMapping({"index", "index.html"})
    public String toIndex() {
        return "main/login";
    }

    /**
     * 访问客户页面
     *
     * @return
     */
    @GetMapping("user.html")
    public String toUser() {
        return "user/user";
    }

    /**
     * 访问员工管理页面
     *
     * @return
     */
    @GetMapping("empManager.html")
    public String toEmpManager() {
        return "emp/empManager";
    }

    /**
     * 访问当前员工信息修改页面
     *
     * @return
     */
    @GetMapping("empInfo.html")
    public String toEmpInfo() {
        return "emp/empInfo";
    }

    /**
     * 访问员工修改密码页面
     *
     * @return
     */
    @GetMapping("changeEmpPwd.html")
    public String toEmpNewPwd() {
        return "emp/changeEmpPwd";
    }

    /**
     * 访问日志管理页面
     *
     * @return
     */
    @GetMapping("logInfo.html")
    public String toLogInfo() {
        return "log/logInfo";
    }

    /**
     * 访问系统公告页面
     *
     * @return
     */
    @GetMapping("news.html")
    public String toNews() {
        return "news/news";
    }

    /**
     * 访问指标添加页面
     *
     * @return
     */
    @GetMapping("indexAdd.html")
    public String toIndexAdd() {
        return "index/indexAdd";
    }

    /**
     * 访问指标列表页面
     *
     * @return
     */
    @GetMapping("indexList.html")
    public String toIndexList() {
        return "index/indexList";
    }


    /**
     * 访问电表数据列表
     *
     * @return
     */
    @RequestMapping("listMeterData.html")
    public String toListMeterData() {
        return "meterdata/listMeterData";
    }

    /**
     * 访问电表管理列表
     *
     * @return
     */
    @RequestMapping("meterManager.html")
    public String toMeterList() {
        return "meterdata/meterManager";
    }

    /**
     * 访问数据统计页面
     *
     * @return
     */
    @RequestMapping("meterDataChart.html")
    public String toMeterDataChart() {
        return "meterdata/meterDataChart";
    }

    /**
     * 访问电费收缴单管理页面
     *
     * @return
     */
    @RequestMapping("marketinfo.html")
    public String toMarketInfo() {
        return "market/marketinfo";
    }

    /**
     * 访问优惠页面
     *
     * @return
     */
    @RequestMapping("discount.html")
    public String toDiscount() {
        return "market/discount.html";
    }

    /**
     * 访问电费收缴单管理页面
     *
     * @return
     */
    @RequestMapping("feeform.html")
    public String toFeeForm() {
        return "fee/feeform";
    }

    /**
     * 访问缴费记录管理页面
     *
     * @return
     */
    @RequestMapping("feeRecord.html")
    public String toFeeRecord() {
        return "fee/feeRecord";
    }

    /**
     * 访问测试
     *
     * @return
     */
    @RequestMapping("test.html")
    public String toTest() {
        return "test/test.html";
    }

    /**
     * 访问门户首页
     *
     * @return
     */
    @RequestMapping({"front_index", "front_index.html"})
    public String toFrontIndex() {
        return "front/front_index";
    }

    @RequestMapping("front_login.html")
    public String toFrontLogin() {
        return "front/front_login";
    }

    /**
     * 访问活动报名
     *
     * @return
     */
    @RequestMapping({"activityManager", "activityManager.html"})
    public String toActivityManager() {
        return "market/activityManager";
    }

    /**
     * 访问电线管理页面
     *
     * @return
     */
    @RequestMapping({"wire.html", "wire"})
    public String toWire() {
        return "wire/wire.html";
    }

    /**
     * 访问设备管理
     *
     * @return
     */
    @RequestMapping("facility.html")
    public String toFacility() {
        return "facility/facility";
    }

    /**
     * 访问修改客户密码
     *
     * @return
     */
    @RequestMapping("changeUserPwd.html")
    public String toChangeUserPwd() {
        return "front/changeUserPwd";
    }

    /**
     * 访问报装管理页面
     *
     * @return
     */
    @RequestMapping("instanceManager.html")
    public String toInstanceManager() {
        return "user/instanceManager";
    }

    /**
     * 访问设备安装列表
     *
     * @return
     */
    @RequestMapping("facilityList.html")
    public String toFacilityList() {
        return "facility/facilityList";
    }

    /**
     * 访问设备安装列表
     *
     * @return
     */
    @RequestMapping("incOrDec.html")
    public String toIncOrDec() {
        return "user/incOrDec";

    }

    /**
     * 报装登记
     *
     * @return
     */
    @RequestMapping("baozhuang.html")
    public String toBaozhuang() {
        return "user/baozhuang";
    }

    /**
     * 抄表
     *
     * @return
     */
    @RequestMapping("chaobiao.html")
    public String toChaobiaoMeterData() {
        return "meterdata/chaobiao";
    }

    /**
     * 营销统计
     *
     * @return
     */
    @RequestMapping("feeChart.html")
    public String toFeeChart() {
        return "fee/feeChart";
    }

    /**
     * 员工销售统计
     *
     * @return
     */
    @RequestMapping("empSoleChart.html")
    public String toEmpSoleChart() {
        return "fee/empSoleChart";
    }

    /**
     * 公司历史营销统计
     *
     * @return
     */
    @RequestMapping("companyChart.html")
    public String toCompanyChart() {
        return "fee/companyChart";
    }
}
