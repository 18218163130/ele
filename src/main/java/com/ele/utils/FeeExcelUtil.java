package com.ele.utils;

import com.ele.entity.Fee;
import com.ele.entity.User;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 导出Excel表格
 * @Author dongwf
 * @Date 2019/11/15
 */
public class FeeExcelUtil {

    public static void export(Fee fee , String path) throws Exception{

            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("客户电费单");
            // 设置默认列宽
            sheet.setDefaultColumnWidth(30);
            sheet.setDefaultRowHeight((short)(20*25));

            // 合并单元格
            CellRangeAddress range = new CellRangeAddress(0,0,0,3);
            sheet.addMergedRegion(range);
            HSSFCellStyle baseStyle = createBaseStyle(workbook);

            // 行的下标
            int index = 0;
            // 第一行
            HSSFRow row0 = sheet.createRow(index);
            HSSFCell cell0 = row0.createCell(0);
            cell0.setCellValue("客户电费缴费单");
            cell0.setCellStyle(baseStyle);
            cell0.setCellStyle(createTitleStyle(workbook)); // 设置标题样色

            // 第二行
            index ++;
            HSSFRow row1 = sheet.createRow(index);
            HSSFCell row1_cell0 = row1.createCell(0);
            row1_cell0.setCellValue("电费单号：");
            HSSFCell row1_cell1 = row1.createCell(1);
            row1_cell1.setCellValue(fee.getFeeId()+"");
            HSSFCell row1_cell2 = row1.createCell(2);
            row1_cell2.setCellValue("客户编号：");
            HSSFCell row1_cell3 = row1.createCell(3);
            row1_cell3.setCellValue(fee.getUserId());
            row1_cell0.setCellStyle(createBaseStyle(workbook));
            row1_cell1.setCellStyle(createBaseStyle(workbook));
            row1_cell2.setCellStyle(createBaseStyle(workbook));
            row1_cell3.setCellStyle(createBaseStyle(workbook));

            // 第三行
            index ++;
            HSSFRow row2 = sheet.createRow(index);
            HSSFCell row2_cell0 = row2.createCell(0);
            row2_cell0.setCellValue("客户姓名：");
            HSSFCell row2_cell1 = row2.createCell(1);
            row2_cell1.setCellValue(fee.getRealName());
            HSSFCell row2_cell2 = row2.createCell(2);
            row2_cell2.setCellValue("月份：");
            HSSFCell row2_cell3 = row2.createCell(3);
            row2_cell3.setCellValue(excelDate(fee.getRecordDate()));
            row2_cell0.setCellStyle(createBaseStyle(workbook));
            row2_cell1.setCellStyle(createBaseStyle(workbook));
            row2_cell2.setCellStyle(createBaseStyle(workbook));
            row2_cell3.setCellStyle(createBaseStyle(workbook));

            // 第四行
            index ++;
            HSSFRow row3 = sheet.createRow(index);
            HSSFCell row3_cell0 = row3.createCell(0);
            row3_cell0.setCellValue("月耗电（kW）：");
            HSSFCell row3_cell1 = row3.createCell(1);
            row3_cell1.setCellValue(fee.getAmount()+"");
            HSSFCell row3_cell2 = row3.createCell(2);
            row3_cell2.setCellValue("单价（kW/元）：");
            HSSFCell row3_cell3 = row3.createCell(3);
            row3_cell3.setCellValue(fee.getUnitPrice()+"");
            row3_cell0.setCellStyle(createBaseStyle(workbook));
            row3_cell1.setCellStyle(createBaseStyle(workbook));
            row3_cell2.setCellStyle(createBaseStyle(workbook));
            row3_cell3.setCellStyle(createBaseStyle(workbook));
            // 第五行
            index ++;
            HSSFRow row4 = sheet.createRow(index);
            HSSFCell row4_cell0 = row4.createCell(0);
            row4_cell0.setCellValue("总电费（元）：");
            HSSFCell row4_cell1 = row4.createCell(1);
            row4_cell1.setCellValue(fee.getPrize()+"");
            HSSFCell row4_cell2 = row4.createCell(2);
            row4_cell2.setCellValue("状态：");
            HSSFCell row4_cell3 = row4.createCell(3);
            row4_cell3.setCellValue(fee.getState()==0?"未缴费":"已缴费");
            row4_cell0.setCellStyle(createBaseStyle(workbook));
            row4_cell1.setCellStyle(createBaseStyle(workbook));
            row4_cell2.setCellStyle(createBaseStyle(workbook));
            row4_cell3.setCellStyle(createBaseStyle(workbook));

            // 第六行
            index ++;
            HSSFRow row5 = sheet.createRow(index);
            HSSFCell row5_cell0 = row5.createCell(0);
            row5_cell0.setCellValue("缴费方式：");
            HSSFCell row5_cell1 = row5.createCell(1);

            String payWay= "";
            if(fee.getPayWay()==2){
                payWay = "现金支付";
            }else if(fee.getPayWay()==1){
                payWay = "微信微信";
            }else {
                payWay = "--";
            }
            row5_cell1.setCellValue(payWay);
            HSSFCell row5_cell2 = row5.createCell(2);
            row5_cell2.setCellValue("打印时间：");
            HSSFCell row5_cell3 = row5.createCell(3);
            row5_cell3.setCellValue(excelTime(new Date()));
            row5_cell0.setCellStyle(createBaseStyle(workbook));
            row5_cell1.setCellStyle(createBaseStyle(workbook));
            row5_cell2.setCellStyle(createBaseStyle(workbook));
            row5_cell3.setCellStyle(createBaseStyle(workbook));

            // 第七行
            index ++;
            HSSFRow row6 = sheet.createRow(index);
            HSSFCell row6_cell2 = row6.createCell(2);
            row6_cell2.setCellValue("客户签名：");
            HSSFCell row6_cell3 = row6.createCell(3);
            row6_cell3.setCellValue("");

            row6_cell2.setCellStyle(createBaseStyle(workbook));
            row6_cell3.setCellStyle(createBaseStyle(workbook));


            // 导出到D盘
            workbook.write(new File(path));
            System.out.println("导出成功");

    }

    /**
     * 转换日期格式
     * @param date
     * @return
     */
    private static String excelDate(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月");
        return simpleDateFormat.format(date);
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
