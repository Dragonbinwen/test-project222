package com.test.day10.util;

import com.github.javafaker.Faker;

import java.util.Locale;

/**
 * @Project: interface_class49
 * @Site: http://www.lemonban.com
 * @Forum: http://testingpai.com
 * @Copyright 湖南省零檬信息技术有限公司. All rights reserved.
 * @Author: 长沙吴彦祖
 * @Create: 2023-07-03 20:15
 * @Desc： 随机数据生产练习
 */
public class RandomDataUtil {

    /**
     * 生成一个未注册过的手机号码
     * @return
     */
    public static String getUnregisterPhone(){
        //1、生成随机的手机号码
        Faker faker = new Faker(Locale.CHINA);
        String phone = faker.phoneNumber().cellPhone();
        //2、保证它是没有使用过-未注册过的-查询数据库
        Object result = JDBCUtil.querySingleData("SELECT COUNT(*) FROM tz_user WHERE user_mobile='"+phone+"';");
        while(true) {
            if ((Long) result == 0) {
                break;
            } else {
                //不满足要求
                phone = faker.phoneNumber().cellPhone();
                result = JDBCUtil.querySingleData("SELECT COUNT(*) FROM tz_user WHERE user_mobile='"+phone+"';");
            }
        }
        return phone;
    }

    /**
     * 生成未注册过的用户名
     * @return
     */
    public static String getUnregisterUsername(){
        //1、随机生成合法的用户名-大于4位小于16位，数字+字母的组合
        Faker faker = new Faker();
        String username = faker.name().firstName();
        while(true){
            Object result = JDBCUtil.querySingleData("SELECT COUNT(*) FROM tz_user WHERE user_name = '"+username+"';");
            if(username.length() > 4 && username.length() < 16 && (Long)result == 0){
                break;
            }else {
                //需要再一次重新生成新的用户名
                username = faker.name().firstName();
            }
        }
        return username;
    }
}
