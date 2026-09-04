package com.test.day11.util;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class JDBCUtil {
    /**
     * 去连接数据库
     */
    public static Connection getConnection(){
        String url="jdbc:mysql://47.113.180.81/yami_shops?useUnicode=true&characterEncoding=utf-8&useSSL=true";
        String user="lemon";
        String password="lemon123";
        //conn数据库连接对象
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url,user,password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }

    /**
     * 用来查询单个字段值的SQL执行方法
     * @return
     */
    public static Object querySingleData(String sql){
        Connection conn = getConnection();
        QueryRunner queryRunner = new QueryRunner();
        Object result = null;
        try {
            result = queryRunner.query(conn, sql, new ScalarHandler<>());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * 用来查询多个字段值的SQL执行方法
     * @return
     */
    public static Map<String,Object> queryMulti(String sql){
        Connection conn = getConnection();
        QueryRunner queryRunner = new QueryRunner();
        Map<String,Object> result = null;
        try {
            result = queryRunner.query(conn, sql, new MapHandler());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * 用来查询多条记录的SQL执行方法
     * @return
     */
    public static List<Map<String,Object>> queryAll(String sql){
        Connection conn = getConnection();
        QueryRunner queryRunner = new QueryRunner();
        List<Map<String,Object>> result = null;
        try {
            result = queryRunner.query(conn, sql, new MapListHandler());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
