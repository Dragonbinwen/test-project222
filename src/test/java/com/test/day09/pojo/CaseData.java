package com.test.day09.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CaseData {
    private int id;             //编号
    private String title;          //标题
    private String priority;    //优先级
    private String method;      //请求方法
    private String url;         //请求地址
    private String headers;     //请求头
    private String params;      //请求参数
    private String expected;    //期望结果
    private String extractInfo; //提取响应字段
    private String sql;         //sql查询字段
}
