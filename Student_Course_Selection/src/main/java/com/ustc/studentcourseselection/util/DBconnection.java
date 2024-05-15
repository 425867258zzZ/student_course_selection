package com.ustc.studentcourseselection.util;

import java.sql.*;

/**
 * 获取数据库连接并关闭
 *
 * @author 孟梓晗
 */
public class DBconnection {
    public final static String URL = "jdbc:mysql://localhost:3306/new_schema";
    public final static String USER_NAME = "root";
    public final static String PASSWORD = "123456";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取连接
     *
     * @return 连接
     */
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Failed to connect the database!");
        }
        return null;
    }

    /**
     * Close connection.
     *
     * @param rs   结果集
     * @param ps   预声明
     * @param conn 数据库连接
     */
    public static void closeConnection(ResultSet rs, PreparedStatement ps, Connection conn) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("SQLException");
        }
    }
}
