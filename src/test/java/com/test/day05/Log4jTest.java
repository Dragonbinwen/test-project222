package com.test.day05;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

public class Log4jTest {
    //得到Log4j日志对象
    Logger logger = Logger.getLogger(Log4jTest.class);

    @Test
    public void test_login(){
        logger.debug("这是debug模式的日志");
        logger.info("这是info模式的日志");
        logger.warn("这是warn模式的日志");
        logger.error("这是error模式的日志");
    }

    public static void main(String[] args) {
        System.out.println("这是通过sysout输出的日志");
    }
}
