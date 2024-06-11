package com.ustc.studentcourseselection.controller;

import com.ustc.studentcourseselection.dao.DBconnection;
import com.ustc.studentcourseselection.dao.TeacherDao;
import com.ustc.studentcourseselection.model.BaseUtils;
import com.ustc.studentcourseselection.model.Teacher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author wangpanyang
 */
public class TeacherManagementUtils {
    public static boolean teacherAdd(String name, String number, String gender, String department, String password, String createTime, String updateTime) {
        int id = 0;
        TeacherDao cd = new TeacherDao();

        Connection connection = DBconnection.getConnection();
        System.out.println("successful");
        String sql = "SELECT id FROM teacher ORDER BY id DESC LIMIT 1;";
        PreparedStatement pstm = null;
        try {
            if (connection != null) {
                pstm = connection.prepareStatement(sql);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ResultSet rs = null;
        try {
            if (pstm != null) {
                rs = pstm.executeQuery(sql);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            if (rs != null && rs.next()) {
                // 获取结果
                id = rs.getInt("id") + 1;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBconnection.closeConnection(rs, pstm, connection);
        }

        return cd.add(new Teacher(id, name, number, gender, department, password, createTime, updateTime));
    }

    public static boolean teacherEdict(String name, String number, String gender, String department, String password) {
        TeacherDao cd = new TeacherDao();
        Teacher newTeacher = new Teacher(cd.query(number).getId(), name, number, gender, department, password, cd.query(number).getCreateTime(), BaseUtils.getTime());
        return cd.update(newTeacher);
    }
}

