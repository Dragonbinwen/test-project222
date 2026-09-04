package com.test.day07.common.util;

import com.alibaba.excel.EasyExcel;
import com.test.day07.common.pojo.CaseData;

import java.util.List;

/**
 * Excel操作的工具类
 */
public class ExcelUtil {
    /**
     * 读取Excel工具方法
     * @param path excel文件路径
     * @param sheetName 要读取的sheet名称
     * @return 读取的全部数据
     */
    public static List<CaseData> readExcel(String path, String sheetName){
        List<CaseData> datas = EasyExcel.read(path).head(CaseData.class).
                sheet(sheetName).doReadSync();
        return datas;
    }
}
