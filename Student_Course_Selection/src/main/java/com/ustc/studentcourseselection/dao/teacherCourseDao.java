package com.ustc.studentcourseselection.dao;

import com.ustc.studentcourseselection.model.Course;
import com.ustc.studentcourseselection.util.DBconnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * ��ʦ��γ̺ͽ����߼�
 *
 * @author ������
 */
public class teacherCourseDao {
    /**
     * ��ȡ��ʦ�̵����пγ�
     *
     * @param teacherName ��ʦ����
     * @return ����ʦ�̵����пγ�
     */
    public List<Course> getCoursesForTeacher(String teacherName) {
        Connection connection = DBconnection.getConnection();
        String sql3 = "SELECT course.* FROM course WHERE teacher_name = ?";
        List<Course> courses = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            if (connection != null) {
                ps = connection.prepareStatement(sql3);
            }
            if (ps != null) {
                ps.setString(1, teacherName);
            }
            if (ps != null) {
                rs = ps.executeQuery();
            }
            while (rs != null && rs.next()) {
                Course course = new Course(rs.getInt("id"), rs.getString("number"), rs.getString("course_name"),
                        rs.getString("course_time"), rs.getString("major"), rs.getString("location"), rs.getString("teacher_name"), rs.getInt("score"), rs.getInt("capacity"), rs.getString("create_time"), rs.getString("update_time"));
                courses.add(course);
            }
            return courses;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            DBconnection.closeConnection(rs, ps, connection);
        }
        return null;
    }
}

