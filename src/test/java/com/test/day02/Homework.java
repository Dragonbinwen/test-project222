package com.test.day02;

import static io.restassured.RestAssured.given;

public class Homework {
    public static void main(String[] args) {
        //通过REST-assured编写代码来练习接口测试（商品详情页接口+搜索商品接口），提交代码
        //接口请求四大要素：请求方法、请求地址、请求头、请求数据
        //搜索商品接口
        given().
        when().
                get("http://mall.lemonban.com:8107/search/searchProdPage?" +
                                "prodName=神仙水&categoryId=&sort=0&orderBy=0&current=1&isAllProdType=true&st=0&size=12").
        then().
                log().all();
        //商品详情页接口
        given().
        when().
                get("http://mall.lemonban.com:8107/prod/prodInfo?prodId=17830").
        then().
                log().all();
    }
}
