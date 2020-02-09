package com.ele.utils;

import com.ele.entity.Activity;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 导出活动报名名单
 * @Author dongwf
 * @Date 2019/11/25
 */
public class ActivityExcelUtil {

    private static  String[] titles = {"ID","客户账号","客户名称","活动信息ID","报名时间","性别","联系方式","地址"};
//    private List<Activity> activityList = new ArrayList<Activity>();

    /**
     * 导出报名名单
     * @param path 保存路径
     * @param activities 名单列表
     */
    public static void exprot(String path,List<Activity> activities) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("活动报名名单");
        // 设置默认列宽
        sheet.setDefaultColumnWidth(25);
        sheet.setDefaultRowHeight((short)(20*25));

        // 合并单元格
        CellRangeAddress range = new CellRangeAddress(0,0,0,7);
        sheet.addMergedRegion(range);
        HSSFCellStyle baseStyle = createBaseStyle(workbook);

        // 行的下标
        int index = 0;
        // 第一行
        HSSFRow row0 = sheet.createRow(index);
        HSSFCell cell0 = row0.createCell(0);
        cell0.setCellValue("活动报名名单");
        cell0.setCellStyle(baseStyle);
        cell0.setCellStyle(createTitleStyle(workbook)); // 设置标题样色
        row0.setHeight((short)(25*25));

        // 第二行 小标题
        index ++ ;
        HSSFRow row1 = sheet.createRow(index);
        row1.setRowStyle(baseStyle);
        row1.setHeight((short)(20*25));

        for(int i=0;i<titles.length;i++){
            HSSFCell row1_cell = row1.createCell(i);
            row1_cell.setCellValue(titles[i]);
            row1_cell.setCellStyle(baseStyle);
        }

        for(int i=0;i<activities.size();i++){
            index ++;
            Activity activity = activities.get(i);
            HSSFRow row = sheet.createRow(index);
            HSSFCell cell_0 = row.createCell(0); // 报名ID
            cell_0.setCellValue(activity.getActivityId()+"");
            HSSFCell cell_1 = row.createCell(1); // 客户账号
            cell_1.setCellValue(activity.getUserId());
            HSSFCell cell_2 = row.createCell(2); // 客户姓名
            cell_2.setCellValue(activity.getUser().getRealName());
            HSSFCell cell_3 = row.createCell(3); // 活动信息ID
            cell_3.setCellValue(activity.getDiscountId()+"");
            HSSFCell cell_4 = row.createCell(4); // 报名时间
            cell_4.setCellValue(excelTime(activity.getJoinTime()));
            HSSFCell cell_5 = row.createCell(5); // 性别
            cell_5.setCellValue(activity.getUser().getSex()==1?"男":"女");
            HSSFCell cell_6 = row.createCell(6); // 联系电话
            cell_6.setCellValue(activity.getUser().getPhone());
            HSSFCell cell_7 = row.createCell(7); // 地址
            cell_7.setCellValue(activity.getUser().getAddress());
        }



        // 导出到D盘
        try {
            workbook.write(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("导出成功");
    }

    /**
     * 转换打印缴费单时间格式
     * @param date
     * @return
     */
    private static String excelTime(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分");
        return simpleDateFormat.format(date);
    }

    /**
     * 创建基本样式：水平垂直居中
     * @param workbook
     * @return
     */
    public static HSSFCellStyle createBaseStyle(HSSFWorkbook workbook){
        HSSFCellStyle style = workbook.createCellStyle();
        // 设置垂直水平居中
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        // 设置字体
        HSSFFont font = workbook.createFont();
        font.setFontHeightInPoints((short)12);
        style.setFont(font);
        return style;
    }

    /**
     * 创建标题样色
     * @param workbook
     * @return
     */
    public static HSSFCellStyle createTitleStyle(HSSFWorkbook workbook){
        HSSFCellStyle style = workbook.createCellStyle();
        // 设置垂直水平居中
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        // 设置字体
        HSSFFont font = workbook.createFont();
        font.setFontHeightInPoints((short)22);
        style.setFont(font);
        return style;
    }

}
