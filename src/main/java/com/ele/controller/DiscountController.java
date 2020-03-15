package com.ele.controller;

import com.ele.service.DiscountService;
import com.ele.utils.DataGridView;
import com.ele.utils.ResultObj;
import com.ele.vo.DiscountVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author dongwf
 * @Date 2019/11/7
 */
@Controller
@RequestMapping("discount")
public class DiscountController {
    @Autowired
    private DiscountService discountService;

    /**
     * 查询优惠信息列表
     *
     * @param discountVo
     * @return
     */
    @RequestMapping("queryDiscount")
    @ResponseBody
    public DataGridView queryDiscount(DiscountVo discountVo) {
        return discountService.queryDiscount(discountVo);
    }

    /**
     * 添加优惠活动信息
     *
     * @param discountVo
     * @return
     */
    @RequestMapping("addDiscount")
    @ResponseBody
    public ResultObj addDiscount(DiscountVo discountVo) {
        try {
            discountVo.setCreateTime(new Date());
            discountService.addDiscount(discountVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 删除优惠活动
     *
     * @param discountVo
     * @return
     */
    @RequestMapping("deleteDiscount")
    @ResponseBody
    public ResultObj deleteDiscount(DiscountVo discountVo) {
        try {
            discountService.deleteDiscount(discountVo.getDiscountId());
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 批量删除
     *
     * @param discountVo
     * @return
     */
    @RequestMapping("deleteBatchDiscount")
    @ResponseBody
    public ResultObj deleteBatchDiscount(DiscountVo discountVo) {
        try {
            discountService.deleteBatchDiscount(discountVo.getIds());
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 编辑优惠活动信息
     *
     * @param discountVo
     * @return
     */
    @RequestMapping("editDiscount")
    @ResponseBody
    public ResultObj editDiscount(DiscountVo discountVo) {
        try {
            discountService.updateDiscount(discountVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            return ResultObj.UPDATE_ERROR;
        }
    }

    @RequestMapping("upload")
    @ResponseBody
    public Map<String, Object> uploadFile(HttpServletRequest request, @Param("file") MultipartFile file) throws IOException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String res = sdf.format(new Date());

        // 图片存储地址
        String uploadPath = "G://upload/";
        // 获取原始名字
        String filename = file.getOriginalFilename();
        // 新的文件名
        String newFileName = res + filename.substring(filename.lastIndexOf("."));
        System.out.println("newFileName:" + newFileName);
        //创建年月文件夹
        Calendar date = Calendar.getInstance();
        File dateDirs = new File(date.get(Calendar.YEAR) + File.separator + (date.get(Calendar.MONDAY) + 1));
        System.out.println("dateDirs:" + dateDirs);

        // 新文件
        File newFile = new File(uploadPath + File.separator + dateDirs + File.separator + newFileName);
        //判断目标文件所在的目录是否存在
        if (!newFile.getParentFile().exists()) {
            newFile.getParentFile().mkdirs();
        }

        System.out.println(newFile);
        //将内存中的数据写入磁盘
        file.transferTo(newFile);
        //完整的url
        String fileUrl = "upload/" + date.get(Calendar.YEAR) + "/" + (date.get(Calendar.MONTH) + 1) + "/" + newFileName;
        System.out.println("fileUrl:" + fileUrl);
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> map2 = new HashMap<String, Object>();
        map.put("code", 0); // 0代表成功，1代表失败
        map.put("msg", "上传成功");
        map2.put("src", fileUrl);
        map2.put("title", newFileName); // 图片名称，这个会显示在输入框
        map.put("data", map2);
        return map;
    }

}
