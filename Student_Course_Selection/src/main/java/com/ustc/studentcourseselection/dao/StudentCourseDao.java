package com.ustc.studentcourseselection.dao;

import com.ustc.studentcourseselection.model.Course;
import com.ustc.studentcourseselection.model.Student;
import com.ustc.studentcourseselection.util.DBconnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * ѧ��ѡ�ν����߼�
 *
 * @author ������
 */
public class StudentCourseDao {
    /**
     * ѧ��ѡ��
     *
     * @param studentId ѧ��id
     * @param courseId  �γ�id
     */
    public boolean addCourseForStudent(int studentId, int courseId) {

        Connection connection = DBconnection.getConnection();
        String sql1 = "INSERT INTO student_course(student_id,course_id) VALUES(?,?)";
        PreparedStatement ps = null;
        try {
            if (connection != null) {
                ps = connection.prepareStatement(sql1);
            }
            if (ps != null) {
                ps.setInt(1, studentId);
                ps.setInt(2, courseId);
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
     * ѧ���˿�
     *
     * @param studentId ѧ��id
     * @param courseId  �γ�id
     */
    public boolean removeCourseForStudent(int studentId, int courseId) {
        Connection connection = DBconnection.getConnection();
        String sql1 = "DELETE FROM student_course WHERE student_id=? AND course_id=?";
        PreparedStatement ps = null;
        try {
            if (connection != null) {
                ps = connection.prepareStatement(sql1);
            }
            if (ps != null) {
                ps.setInt(1, studentId);
                ps.setInt(2, courseId);
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
     * ����ѧ��ID�����ݿ��в�ѯ��ѧ����ѡ�Ŀγ��б�
     *
     * @param studentId ѧ��id
     * @return ��ѧ��ѡ�����пγ�
     */
    public List<Course> getCoursesForStudent(int studentId) {
        List<Course> courses = new ArrayList<>();
        Connection connection = DBconnection.getConnection();
        String sql3 = "SELECT course.* FROM course " +
                "INNER JOIN student_course ON course.id = student_course.course_id " +
                "WHERE student_course.student_id = ?";
        PreparedStatement ps = null;
        try {
            if (connection != null) {
                ps = connection.prepareStatement(sql3);
            }
            if (ps != null) {
                ps.setInt(1, studentId);
            }
            ResultSet rs = null;
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
            DBconnection.closeConnection(null, ps, connection);
        }
        return null;
    }

    /**
     * ���ݿγ�ID�����ݿ��в�ѯѡ���˸ÿγ̵�ѧ���б�
     *
     * @param courseId �γ�id
     * @return ����ѡ�˸ÿε�ѧ��
     */
    public static List<Student> getStudentsForCourse(int courseId) {
        List<Student> students = new ArrayList<>();
        Connection connection = DBconnection.getConnection();
        String sql4 =
                "SELECT student.* FROM student  " +
                        "INNER JOIN student_course ON student.id = student_course.student_id " +
                        "WHERE student_course.course_id = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            if (connection != null) {
                ps = connection.prepareStatement(sql4);
            }
            if (ps != null) {
                ps.setInt(1, courseId);
            }
            if (ps != null) {
                rs = ps.executeQuery();
            }
            while (rs != null && rs.next()) {
                Student student = new Student(rs.getInt("id"), rs.getString("name"), rs.getString("number"), rs.getString("gender"), rs.getInt("grade"), rs.getString("degree"), rs.getString("major"), rs.getString("className"), rs.getString("password"), rs.getString("createTime"), rs.getString("updateTime"));
                students.add(student);
            }
            return students;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            DBconnection.closeConnection(rs, ps, connection);
        }
        return null;
    }
}
