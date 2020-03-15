package com.ele;

import com.ele.entity.Activity;
import com.ele.mapper.*;
import com.ele.service.AuthService;
import com.ele.service.IndexService;
import com.ele.service.MeterDataService;
import com.ele.utils.ActivityExcelUtil;
import com.ele.vo.ActivityVo;
import com.ele.vo.AnalyEmpSoleVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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

        ActivityExcelUtil.exprot("G:/joins.xls", activities);
    }

    @Test
    public void test2() {
        List<AnalyEmpSoleVo> analyEmpSoleVos = feeMapping.analyEmpSole("2020-01");
        System.out.println();
    }

}
