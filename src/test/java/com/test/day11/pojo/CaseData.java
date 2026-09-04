package com.test.day11.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CaseData {
    @ExcelProperty("编号")
    private int id;             //编号

    @ExcelProperty("标题")
    private String title;          //标题

    @ExcelProperty("优先级")
    private String priority;    //优先级

    @ExcelProperty("请求方法")
    private String method;      //请求方法

    @ExcelProperty("接口地址")
    private String url;         //请求地址

    @ExcelProperty("请求头")
    private String headers;     //请求头

    @ExcelProperty("请求参数")
    private String params;      //请求参数

    @ExcelProperty("响应断言")
    private String expected;    //期望结果

    @ExcelProperty("提取响应")
    private String extractInfo; //提取响应字段

    @ExcelProperty("后置SQL")
    private String afterSql;    //后置sql字段

    @ExcelProperty("数据库断言")
    private String assertSql;   //SQL断言字段
}
