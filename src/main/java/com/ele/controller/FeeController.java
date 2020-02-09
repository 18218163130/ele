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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    /**
     * 生成电费单
     *
     * @param meterDataVo
     * @return
     */
    @RequestMapping("doCreateFee")
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
                    System.out.println(fee.toString());
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
