package com.test.day09;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class DBTest {
    public static void main(String[] args) throws SQLException {
        //通过Java代码去链接Mysql数据库
        //Oracle：jdbc:oracle:thin:@localhost:1521:DBName
        //SqlServer：jdbc:microsoft:sqlserver://localhost:1433; DatabaseName=DBName
        //MySql：jdbc:mysql://localhost:3306/DBName
        /*String url="jdbc:mysql://47.113.180.81/yami_shops?useUnicode=true&characterEncoding=utf-8&useSSL=true";
        String user="lemon";
        String password="lemon123";
        //conn数据库连接对象
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url,user,password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }*/
        //查询 - 结果是单个字段的时候-ScalarHandler接收
        /*QueryRunner queryRunner = new QueryRunner();
        String sql = "SELECT mobile_code FROM tz_sms_log WHERE user_phone = '13323234501'";
        Object result = queryRunner.query(conn, sql, new ScalarHandler<>());
        System.out.println(result);*/

        //查询 - 结果是多个字段的时候-MapHandler接收
        /*QueryRunner queryRunner = new QueryRunner();
        String sql = "SELECT * FROM tz_sms_log WHERE user_phone = '13323234501'";
        Object result = queryRunner.query(conn, sql, new MapHandler());
        System.out.println(result);*/

        //查询 - 结果是多条记录的时候-MapListHandler接收
        /*QueryRunner queryRunner = new QueryRunner();
        String sql = "SELECT * FROM tz_sms_log WHERE id > 78860;";
        Object result = queryRunner.query(conn, sql, new MapListHandler());
        System.out.println(result);*/
        System.out.println(querySingleData("SELECT mobile_code FROM tz_sms_log WHERE user_phone = '13323234523'"));;
    }

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
