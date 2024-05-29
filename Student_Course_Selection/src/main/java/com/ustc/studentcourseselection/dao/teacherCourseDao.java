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
     * ��ʦ����һ�ſ�
     * TODO
     *
     * @param teacherId ��ʦid
     * @param courseId  �γ�id
     * @return �Ƿ�ɹ�
     */
    public boolean teacherAddCourse(int teacherId, int courseId) {
        Connection connection = DBconnection.getConnection();
        String sql1 = "INSERT INTO teacher_course(course_id,teacher_id) VALUES(?,?)";
        PreparedStatement ps = null;
        try {
            if (connection != null) {
                ps = connection.prepareStatement(sql1);
            }
            if (ps != null) {
                ps.setInt(1, courseId);
                ps.setInt(2, teacherId);
            }
            if (ps != null) {
                return ps.executeUpdate() > 0;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            DBconnection.closeConnection(null, ps, connection);
        }
        return false;
    }

    /**
     * ��ʦɾ��һ�ſ�
     *
     * @param teacherId ��ʦid
     * @param courseId  �γ�id
     * @return �Ƿ�ɹ�
     */
    public boolean teacherDeleteCourse(int teacherId, int courseId) {
        Connection connection = DBconnection.getConnection();
        String sql2 = """
                START TRANSACTION;
                                
                DELETE FROM teacher_course WHERE teacher_id=? AND course_id = ?;
                                
                DELETE FROM course WHERE id = ?;
                                
                COMMIT;
                """;
        PreparedStatement ps = null;
        try {
            if (connection != null) {
                ps = connection.prepareStatement(sql2);
            }
            if (ps != null) {
                ps.setInt(1, teacherId);
                ps.setInt(2, courseId);
                ps.setInt(3, courseId);
            }
            if (ps != null) {
                return ps.executeUpdate() > 0;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            DBconnection.closeConnection(null, ps, connection);
        }
        return false;
    }

    /**
     * ��ȡ��ʦ�̵����пγ�
     *
     * @param teacherId ��ʦid
     * @return ����ʦ�̵����пγ�
     */
    public List<Course> getCoursesForTeacher(int teacherId) {
        Connection connection = DBconnection.getConnection();
        String sql3 = "SELECT course.* FROM course " +
                "INNER JOIN teacher_course ON course.id = teacher_course.course_id " +
                "WHERE teacher_course.teacher_id = ?";
        List<Course> courses = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            if (connection != null) {
                ps = connection.prepareStatement(sql3);
            }
            if (ps != null) {
                ps.setInt(1, teacherId);
            }
            if (ps != null) {
                rs = ps.executeQuery();
            }
            while (rs != null && rs.next()) {
                Course course = new Course(rs.getInt("id"), rs.getString("number"), rs.getString("course_name"),
                        rs.getString("course_time"), rs.getString("major"), rs.getString("location"), rs.getInt("score"), rs.getInt("capacity"), rs.getString("create_time"), rs.getString("update_time"));
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

