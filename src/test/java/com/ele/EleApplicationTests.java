package com.ele;

import com.ele.entity.*;
import com.ele.mapper.*;
import com.ele.service.AuthService;
import com.ele.service.FeeService;
import com.ele.service.IndexService;
import com.ele.service.MeterDataService;
import com.ele.utils.*;
import com.ele.vo.*;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EleApplicationTests {

    @Autowired
    private AuthService authService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private LogMapper logMapper;
    @Autowired
    private IndexService indexService;
    @Autowired
    private MeterDataMapper meterDataMapper;
    @Autowired
    private MeterMapper meterMapper;
    @Autowired
    private DiscountMapper discountMapper;

    @Autowired
    private FeeMapping feeMapping;
    @Autowired
    private ActivityMapper activityMapper;

    @Autowired
    private FacilityMapper facilityMapper;

    @Autowired
    private MeterDataService meterDataService;




    /**
     * 测试分页
     */
    @Test
    public void testPage() {
//        C:/Users/dongwf/Desktop/fee.xls
        List<Activity> activities = activityMapper.selectActivity(new ActivityVo());

        ActivityExcelUtil.exprot("G:/joins.xls",activities);
    }

    @Test
    public void test2(){
        List<AnalyEmpSoleVo> analyEmpSoleVos = feeMapping.analyEmpSole("2020-01");
        System.out.println();
    }

}
