package com.test.day02;

import io.restassured.response.Response;

import java.io.File;

import static io.restassured.RestAssured.given;

public class FirstAuto {
    public static void main(String[] args) {
        //发起接口请求四大要素：请求方法、接口地址、请求头（可选）、请求数据（可选）
        //given() 是用来设置请求预设：请求头、请求数据
        //when() 代表要去执行的操作：get/post/put/delete,填写接口请求地址
        //then() 代表的是在请求结束之后要做的操作，eg：提取响应数据,打印响应结果
        //链式调用
        /*given().
                header("Content-Type","application/json; charset=UTF-8").
                body("{\"principal\":\"lemon_auto\",\"credentials\":\"lemon123456\",\"appType\":3,\"loginType\":0}").
        when().
                post("http://mall.lemonban.com:8107/login").
        then().
                log().all();*/

        //通过REST-assured发送get请求
        //参数拼接到URL地址的后面
        /*given().
        when().
                get("http://mall.lemonban.com:8107/search/searchProdPage?prodName=duck").
        then().
                log().body();*/
        //通过given设置请求参数 - 查询参数
        /*given().
                queryParam("prodName","duck").
        when().
                get("http://mall.lemonban.com:8107/search/searchProdPage").
        then().
                log().body();*/

        //通过REST-assured发送post请求
        //传参类型：JSON传参-application/json、FORM表单-application/x-www-form-urlencoded、XML传参、Text文本传参、
        // 大文件（大量数据）传参-文件上传-multipart/formdata
        //1、JSON传参
        /*given().
                header("Content-Type","application/json; charset=UTF-8").
                body("{\"principal\":\"lemon_auto\",\"credentials\":\"lemon123456\",\"appType\":3,\"loginType\":0}").
        when().
                post("http://mall.lemonban.com:8107/login").
        then().
                log().all();*/

        //2、FORM表单传参
        /*given().
                header("Content-Type","application/x-www-form-urlencoded; charset=UTF-8").
                body("loginame=admin&password=e10adc3949ba59abbe56e057f20f883e").
        when().
                post("http://erp.lemfix.com/user/login").
        then().
                log().all();*/

        //3、大容量数据参数传递（文件上传接口）
        //通过multiPart方法设定要上传文件路径
        /*given().
                header("Content-Type","multipart/form-data").
                header("Authorization","bearera20f6211-9feb-446a-bbdb-01c87b1ddb9a").
                multiPart(new File("src/test/resources/test.png")).
        when().
                post("http://mall.lemonban.com:8107/p/file/upload").
        then().
                log().all();*/
        //问题点：如果只是调用了上传图片的接口，那么该图片保存到了服务器中，并没有去修改用户的头像

        //put请求 - 替换，与post请求类似，请求参数放到请求体中
        /*given().
                header("Content-Type","application/json; charset=UTF-8").
                header("Authorization","bearera20f6211-9feb-446a-bbdb-01c87b1ddb9a").
                body("{\"avatarUrl\":\"http://mall.lemonban.com:8108/2023/06/7f646a1049cd4164bdce97768e7959e2.png\",\"nickName\":\"lemon_auto\",\"userMobile\":\"13323234501\",\"auth\":{}}").
        when().
                put("http://mall.lemonban.com:8107/p/user/setUserInfo").
        then().
                log().all();*/

        Response res = given().
        when().
                get("http://mall.lemonban.com:8107/search/searchProdPage").
        then().
                log().body().extract().response();
        Object result = res.jsonPath().get("records.size()");
        System.out.println(result);
    }
}
