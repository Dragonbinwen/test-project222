package com.test.day11;

import com.lemon.encryption.MD5Util;
import com.lemon.encryption.RSAManager;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

/**
 * 加密练习
 */
public class EncryptTest {
    public static void main(String[] args) throws Exception {
        //1、登录接口请求
        /*Response res_login = given().
                header("Content-Type","application/json").
                header("X-Lemonban-Media-Type","lemonban.v3").
                body("{\"mobile_phone\": \"13323231111\",\"pwd\": \"12345678\"}").
        when().
                post("http://api.lemonban.com:8788/futureloan/member/login").
        then().
                log().body().extract().response();
        int memberId = res_login.jsonPath().get("data.id");
        String token = res_login.jsonPath().get("data.token_info.token");
        String token_value = "Bearer "+res_login.jsonPath().get("data.token_info.token");

        //获取到timestamp sign两个参数的值
        //获取时间戳timestamp
        //currentTimeMillis() 获取的是毫秒级别的时间戳
        //毫秒时间 --> 秒时间？？
        //System.out.println(System.currentTimeMillis()/1000);
        //取 token字段 前 50 位再拼接上 timestamp 值，然后通过 RSA 公钥加密得到
        long timestamp = System.currentTimeMillis()/1000;
        String sign = token.substring(0,50) + timestamp;
        //怎么加密？找开发打了一个jar包
        sign = RSAManager.encryptWithBase64(sign);
        //2、充值接口请求
        Response res_recharge = given().
                header("Content-Type","application/json").
                header("X-Lemonban-Media-Type","lemonban.v3").
                header("Authorization",token_value).
                body("{\"member_id\": "+memberId+",\"amount\": 10000.0,\"timestamp\": "
                        +timestamp+",\"sign\": \""+sign+"\"}").
        when().
                post("http://api.lemonban.com:8788/futureloan/member/recharge").
        then().
                log().body().extract().response();*/

        //加密的Jar里面还有MD5加密的算法，可以这么使用
        System.out.println(MD5Util.stringMD5("123456"));

    }
}
