package com.ustc.studentcourseselection.dao;

import java.sql.*;

/**
 * ��ȡ���ݿ����Ӳ��ر�
 *
 * @author ������
 */
public class DBconnection {
    public final static String URL = "jdbc:mysql://localhost:3306/student_course_selection";
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
     * ��ȡ����
     *
     * @return ����
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
     * @param rs   �����
     * @param ps   Ԥ����
     * @param conn ���ݿ�����
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