package com.ele.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 通用工具类
 * @Author dongwf
 * @Date 2019/10/12
 */
public class CommonUtil {

    /**
     * 将当前时间作为日志的ID
     * @return
     */
    public static String getLogInfoId(){
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        return format.format(new Date());
    }
}
