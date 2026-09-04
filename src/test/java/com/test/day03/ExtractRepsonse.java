package com.test.day03;

import io.restassured.response.Response;

import java.io.File;

import static io.restassured.RestAssured.given;

public class ExtractRepsonse {
    public static void main(String[] args) {
        //搜索商品接口
        /*Response response =
        given().
        when().
                get("http://mall.lemonban.com:8107/search/searchProdPage?" +
                        "prodName=自动化&categoryId=&sort=0&orderBy=0&current=1&isAllProdType=true&st=0&size=12").
        then().
                log().all().extract().response();*/
        //log().all() 把响应信息全部直接打印到控制台
        //提取响应体里面某个信息：1、断言 2、传递给后面的接口使用
        //将响应结果保存到Java的变量中
        //响应状态码
        /*System.out.println(response.statusCode());
        //响应时间 - 单位为ms
        System.out.println(response.time());
        //响应头字段
        //System.out.println(response.getHeaders());
        System.out.println(response.getHeader("Content-Type"));*/

        //响应体数据的提取 ！！！
        //Gpath-路径表达式语法
        //通过数组的下标来访问对应的元素：从0开始，获取倒数的第几个的话：通过负数，-1、-2、-3
        //路径表达式中的数组下标由目标字段的位置决定，外层有多少个[]，我们就需要去写几个下标来访问
        /*Object result = response.jsonPath().get("records[-2].prodId");
        System.out.println(result);*/

        //查看购物车信息接口
        /*Response response =
                given().
                        header("Authorization","bearer2edf5dd4-1e11-40c5-85b6-82f0d84e5ebc").
                        header("Content-Type","application/json; charset=UTF-8").
                        body("[]").
                when().
                        post("http://mall.lemonban.com:8107/p/shopCart/info").
                then().
                        log().all().extract().response();
        Object result = response.jsonPath().get("shopCartItemDiscounts[0].shopCartItems[0].prodName[1]");
        System.out.println(result);*/

        //搜索商品接口
        /*Response response =
        given().
        when().
                get("http://mall.lemonban.com:8107/search/searchProdPage?" +
                        "prodName=SK2&categoryId=&sort=0&orderBy=0&current=1&isAllProdType=true&st=0&size=12").
        then().
                log().all().extract().response();*/
        //如果是通过下标来访问，可能字段的位置会变化（数据增加/删除），此时通过下标来访问就不能满足需求
        //我们可以通过条件进行筛选（先去找到商品的ID->商品名字）
        //Object result = response.jsonPath().get("records.find{it.prodId == 17830}.prodName");
        //Object result = response.jsonPath().get("records.findAll{it.prodName == '樱花限定版本SK-II神仙水230ml精华液sk2护肤品套装化妆品礼盒skii'}.prodId");
        //System.out.println(result);
        //find:找符合条件的对应元素，并且返回符合条件的第一个元素
        //findAll：找符合条件的对应元素，并且返回符合条件的所有元素
        //条件筛选表达式语法：XX.find{条件表达式}.字段名


        //通过Gpath表达式提取HTML接口响应数据
        /*Response response = given().when().get("https://www.baidu.com").then().log().all().extract().response();
        //需求：获取百度的title字段的值
        //Object result = response.htmlPath().get("html.head.title");
        //需求：获取百度link标签的type属性值
        //通过@符号指定对应的属性名
        Object result = response.htmlPath().get("html.head.meta[1].@content");
        System.out.println(result);*/


        //综合实例，通过响应提取将接口调用串联起来
        //登录接口
        Response response1 =
        given().
                header("Content-Type","application/json; charset=UTF-8").
                body("{\"principal\":\"lemon_auto\",\"credentials\":\"lemon123456\",\"appType\":3,\"loginType\":0}").
        when().
                post("http://mall.lemonban.com:8107/login").
        then().
                log().all().extract().response();
        String token = "bearer"+response1.jsonPath().get("access_token");
        //实现个人头像上传并且保存头像设置
        Response response2  =
        given().
                header("Content-Type","multipart/form-data").
                header("Authorization",token).
                multiPart(new File("src/test/resources/touxiang.png")).
        when().
                post("http://mall.lemonban.com:8107/p/file/upload").
        then().
                log().all().extract().response();
        //resourceUrl字段的值
        String url = response2.jsonPath().get("resourcesUrl");
        String path = response2.jsonPath().get("filePath");

        //保存用户信息-更改用户头像成功
        given().
                header("Content-Type","application/json; charset=UTF-8").
                header("Authorization",token).
                body("{\"avatarUrl\":\""+url+path+"\",\"nickName\":\"lemon_auto\",\"userMobile\":\"13323234501\",\"auth\":{}}").
        when().
                put("http://mall.lemonban.com:8107/p/user/setUserInfo").
        then().
                log().all();
    }
}
