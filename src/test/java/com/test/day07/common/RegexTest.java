package com.test.day07.common;

/**
 * Java中正则表达式的练习
 */
public class RegexTest {
    public static void main(String[] args) {
        // {"basketId":0,"count":1,"prodId":"{{prodId}}","shopId":1,"skuId":{{skuId}}}
        String str = "{\"basketId\":0,\"count\":1,\"prodId\":\"{{prodId}}\",\"shopId\":1,\"skuId\":{{skuId}}}";
        //通过正则表达式识别到里面的{{XXX}} -> {{prodId}} {{skuId}}
        //Matcher类: 正则匹配器
        //Pattern类：正则表达式匹配模式对象
        //为什么\\需要两次：Java中需要转义一次，正则表达式也需要转义一次
        /*Pattern pattern = Pattern.compile("\\{\\{(.*?)\\}\\}");
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()){
            //打印找到的结果
            System.out.println(matcher.group());
            //通过正则表达式的分组功能，通过()括起来
            System.out.println(matcher.group(1));

        }*/
        System.out.println(str.replace("{{prodId}}","100"));;
    }
}
