package com.test.day04;

import com.alibaba.excel.EasyExcel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class SearchTest {

    //数据提供者 - 会由代码读取Excel中的数据返回
    @DataProvider
    public Object[] getDatas() {
        List<CaseData> datas = EasyExcel.read("src/test/resources/casedata.xlsx").head(CaseData.class).
                sheet("搜索商品").doReadSync();
        return datas.toArray();
    }

    @Test(dataProvider = "getDatas")
    public void test_search(CaseData caseData){
        request(caseData);
    }

    //需求：封装设计统一的接口请求方法，用来兼容所有的接口请求，不管你是注册、登录、搜索商品、添加购物车...
    public Response request(CaseData caseData) {
        //接口请求四大要素
        String url = caseData.getUrl();
        String method = caseData.getMethod();
        String headers = caseData.getHeaders();
        String params = caseData.getParams();

        //RequestSpecification --> 请求设置类型对象，可以通过它来设置各种不同的请求参数/地址/方法/请求数据...
        RequestSpecification req = given();
        //1、设置请求头
        if (headers != null) {
            //请求头可能需要设置多组,把原始的请求头字符串(JSON)转换为Java中Map结构
            req.headers(json2Map(headers));
        }
        //2、设置请求方法，需要判断（get/post/put/delete）
        Response res = null;
        if (method.equalsIgnoreCase("get")) {
            //执行get请求
            res = req.get(url + params).then().log().all().extract().response();
        } else if (method.equalsIgnoreCase("post")) {
            //执行post请求
            //TODO 考虑到文件上传的请求
            res = req.body(params).post(url).then().log().all().extract().response();
        } else if (method.equalsIgnoreCase("put")) {
            //执行put请求
            res = req.body(params).put(url).then().log().all().extract().response();
        } else if (method.equalsIgnoreCase("delete")) {
            //执行delete请求
            res =req.delete(url + params).then().log().all().extract().response();
        }
        return res;
    }

    /**
     * 将json格式的字符串转换为Java的Map结构
     *
     * @param str
     */
    public HashMap<String, Object> json2Map(String str) {
        //通过Jackson进行转换
        ObjectMapper objectMapper = new ObjectMapper();
        HashMap<String, Object> map = null;
        try {
            map = objectMapper.readValue(str, HashMap.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return map;
    }
}
