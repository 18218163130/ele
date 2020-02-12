package com.ele.controller;
import com.ele.service.PriceService;
import com.ele.utils.DataGridView;
import com.ele.utils.ResultObj;
import com.ele.vo.PriceVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author dongwf
 * @Date 2020/2/12
 */
@Controller
public class PriceController {

    @Autowired
    private PriceService priceService;

    /**
     * 设置电费单价
     * @param priceVo
     * @return
     */
    @RequestMapping
    @ResponseBody
    public ResultObj setPrice(PriceVo priceVo){
        try{
            priceService.setYearMonthPirce(priceVo);
            return ResultObj.ADD_SUCCESS;
        }catch(Exception e){
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 删除电费单价
     * @param priceVo
     * @return
     */
    @RequestMapping
    @ResponseBody
    public ResultObj deletePrice(PriceVo priceVo){
        try{
            priceService.deleteYearMonthPirce(priceVo);
            return ResultObj.DELETE_SUCCESS;
        }catch(Exception e){
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 修改电费单价
     * @param priceVo
     * @return
     */
    @RequestMapping
    @ResponseBody
    public ResultObj updatePrice(PriceVo priceVo){
        try{
            priceService.updateYearMonthPirce(priceVo);
            return ResultObj.UPDATE_SUCCESS;
        }catch(Exception e){
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 查询单价列表
     * @param priceVo
     * @return
     */
    @RequestMapping
    @ResponseBody
    public DataGridView queryPrice(PriceVo priceVo){
        return priceService.queryYearMonthPirce(priceVo);
    }


}
