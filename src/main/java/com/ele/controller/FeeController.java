package com.ele.controller;

import com.ele.config.SysConstast;
import com.ele.entity.Fee;
import com.ele.entity.MeterData;
import com.ele.entity.User;
import com.ele.service.FeeService;
import com.ele.service.MeterDataService;
import com.ele.service.UserService;
import com.ele.utils.DataGridView;
import com.ele.utils.FeeExcelUtil;
import com.ele.utils.ResultObj;
import com.ele.vo.FeeVo;
import com.ele.vo.MeterDataVo;
import com.ele.vo.PriceVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 电费管理操作
 *
 * @Author dongwf
 * @Date 2019/10/22
 */
@Controller
@RequestMapping("fee")
public class FeeController {

    @Autowired
    private MeterDataService meterDataService;
    @Autowired
    private FeeService feeService;
    @Autowired
    private UserService userService;

    @RequestMapping("doCreateFee")
    @ResponseBody
    public ResultObj doCreateFee(PriceVo priceVo){
        try{
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
            //需要生成电费单记录
            List<MeterData> needCreateList = meterDataService.selectByState(dateFormat.format(priceVo.getYearMonth()));
            // 修改电表状态位state=1,代表生成电费单
            for (MeterData need : needCreateList) {
                meterDataService.updateStateByDataId(need.getDataId());
                User dbUser = userService.getUserById(need.getUserId());

                Fee fee = new Fee();
                String feeId = new StringBuilder().append(System.currentTimeMillis()).toString();
                fee.setFeeId(feeId);
                /*
                    private String feeId; // 电费单号
                    private Date createTime; // 客户姓名
                    private String realName;
                    private String userId;
                    private float amount; // 用电度数
                    private float prize; // 单价
                    private Integer state; // 缴费状态
                    private String description; // 描述
                    private Integer payWay;
                    private float unitPrice; // 每度电价格
                    private Date recordDate; // 抄表日期
                 */
                fee.setUserId(need.getUserId());
                fee.setRealName(dbUser.getRealName());
                fee.setCreateTime(new Date());
                fee.setAmount(need.getConsume());
                fee.setRecordDate(need.getRecordDate());
                fee.setPayWay(0);
                fee.setState(0);
                // 判断用电类型
                String userId = need.getUserId();
                if(userId.startsWith("GY")){
                    float f =priceVo.getGyPrice()* need.getConsume(); // 家庭用电
                    fee.setPrize(f);
                    fee.setUnitPrice(priceVo.getGyPrice());
                }else if (userId.startsWith("SY")){
                    float f =priceVo.getSyPrice()* need.getConsume(); // 家庭用电
                    fee.setPrize(f);
                    fee.setUnitPrice(priceVo.getSyPrice());
                }else if(userId.startsWith("JT")){
                    float f =priceVo.getJtPrice()* need.getConsume(); // 家庭用电
                    fee.setPrize(f);
                    fee.setUnitPrice(priceVo.getJtPrice());
                }
                // 生成电费单
                feeService.createFee(fee);
            }
            return ResultObj.CREATE_FEE_SUCCESS;
        }catch(Exception e){
            return ResultObj.CREATE_FEE_ERROR;
        }
    }

    /**
     * 生成电费单
     *
     * @param meterDataVo
     * @return
     */
//    @RequestMapping("doCreateFee")
    @ResponseBody
    public ResultObj loadMeterDataByMonth(MeterDataVo meterDataVo) {
        try {
            List<MeterData> meterDataList = meterDataService.queryCalculationFee(meterDataVo);
            for (MeterData meterData : meterDataList) {
                if (meterData.getHasForm() == 0) {
                    Fee fee = new Fee();
                    String feeId = new StringBuilder().append(System.currentTimeMillis()).toString();
                    fee.setFeeId(feeId);
                    fee.setUserId(meterData.getUserId());
                    // 获取客户名称
                    User user = userService.getUserById(meterData.getUserId());
                    if(user == null){
                        return ResultObj.CREATE_FEE_ERROR;
                    }
                    fee.setRealName(user.getRealName());
                    fee.setCreateTime(new Date());
                    fee.setAmount(meterData.getConsume());
                    fee.setDescription("");
                    fee.setRecordDate(meterData.getRecordDate());
                    fee.setPayWay(0);
                    fee.setState(0);
                    Integer state = meterData.getState();
                    if (state == 0) {
                        float f =meterDataVo.getJtPrice()* meterData.getConsume(); // 家庭用电
                        fee.setPrize(f);
                        fee.setUnitPrice((float)meterDataVo.getJtPrice());
                    } else if (state == 1) {
                        float f =meterDataVo.getSyPrice()* meterData.getConsume(); // 家庭用电
                        fee.setPrize(f);
                        fee.setUnitPrice((float)meterDataVo.getSyPrice());
                    } else{
                        float f =meterDataVo.getGyPrice()* meterData.getConsume(); // 家庭用电
                        fee.setPrize(f);
                        fee.setUnitPrice((float)meterDataVo.getGyPrice());
                    }
                    // 生成电费单
                    feeService.createFee(fee);
                }
            }
            return ResultObj.CREATE_FEE_SUCCESS;
        } catch (Exception e) {
            return ResultObj.CREATE_FEE_ERROR;
        }
    }

    /**
     * 加载电费表单列表
     * @param feeVo
     * @return
     */
    @RequestMapping("loadFee")
    @ResponseBody
    public DataGridView loadFee(FeeVo feeVo){
        return feeService.queryAllFee(feeVo);
    }

    /**
     * 删除电费单
     * @param feeVo
     * @return
     */
    @RequestMapping("deleteFee")
    @ResponseBody
    public ResultObj deleteFee(FeeVo feeVo){
        try{
            feeService.deleteFee(feeVo.getFeeId());
            return ResultObj.DELETE_SUCCESS;
        }catch(Exception e){
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 批量删除电费单
     * @param feeVo
     * @return
     */
    @RequestMapping("deleteBatchFee")
    @ResponseBody
    public ResultObj deleteBatchFee(FeeVo feeVo){
        try{
            feeService.deleteBatchFee(feeVo.getIds());
            return ResultObj.DELETE_SUCCESS;
        }catch(Exception e){
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 缴费
     * @param feeVo
     * @return
     */
    @RequestMapping("jiaofei")
    @ResponseBody
    public ResultObj jiaofei(FeeVo feeVo){
        try{
            feeService.payFee(feeVo);
            return ResultObj.PAY_FEE_SUCCESS;
        }catch(Exception e){
            return ResultObj.PAY_FEE_ERROR;
        }
    }

    /**
     * 根据客户账号查询缴费记录
     * @param feeVo
     * @return
     */
    @RequestMapping("feeRecord")
    @ResponseBody
    public DataGridView feeRecord(FeeVo feeVo){
        return feeService.queryAllFee(feeVo);
    }

    /**
     * 导出电费单
     * @param fee
     */
    @RequestMapping("export")
    @ResponseBody
    public ResultObj export(Fee fee){
        try{
            FeeExcelUtil.export(fee,"C:/Users/dongwf/Desktop/fee.xls");
            return ResultObj.EXPORT_SUCCESS;
        }catch(Exception e){
            return ResultObj.EXPORT_ERROR;
        }

    }
}
