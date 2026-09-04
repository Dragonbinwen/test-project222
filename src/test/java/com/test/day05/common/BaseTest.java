package com.test.day05.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.day05.pojo.CaseData;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.Logger;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

//基础的测试类，所有的测试类都会继承于该类
public class BaseTest {
    //得到日志对象
    Logger logger = Logger.getLogger(BaseTest.class);

    //需求：封装设计统一的接口请求方法，用来兼容所有的接口请求，不管你是注册、登录、搜索商品、添加购物车...
    public Response request(CaseData caseData) {
        //接口请求四大要素
        String url = caseData.getUrl();
        String method = caseData.getMethod();
        String headers = caseData.getHeaders();
        String params = caseData.getParams();
        logger.info("=================请求信息=================");
        logger.info("请求方法:"+method);
        logger.info("请求地址:"+url);
        logger.info("请求头:"+headers);
        logger.info("请求参数:"+params);

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
            res = req.get(url + params).then().extract().response();
        } else if (method.equalsIgnoreCase("post")) {
            //执行post请求
            //TODO 考虑到文件上传的请求
            res = req.body(params).post(url).then().extract().response();
        } else if (method.equalsIgnoreCase("put")) {
            //执行put请求
            res = req.body(params).put(url).then().extract().response();
        } else if (method.equalsIgnoreCase("delete")) {
            //执行delete请求
            res =req.delete(url + params).then().extract().response();
        }
        logger.info("=================响应信息=================");
        logger.info("响应状态码:"+res.getStatusCode());
        logger.info("响应时间:"+res.getTime()+"ms");
        logger.info("响应头:"+res.getHeaders().asList());
        logger.info("响应体:"+res.getBody().asString());
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
