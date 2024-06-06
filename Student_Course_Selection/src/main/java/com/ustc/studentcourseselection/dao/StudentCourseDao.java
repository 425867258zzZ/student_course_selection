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
 * 学生选课交互逻辑
 *
 * @author 孟梓晗
 */
public class StudentCourseDao {
    /**
     * 学生选课
     *
     * @param studentId 学生id
     * @param courseId  课程id
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
     * 学生退课
     *
     * @param studentId 学生id
     * @param courseId  课程id
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
     * 根据学生ID从数据库中查询该学生所选的课程列表
     *
     * @param studentId 学生id
     * @return 该学生选的所有课程
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
     * 根据课程ID从数据库中查询选择了该课程的学生列表
     *
     * @param courseId 课程id
     * @return 所有选了该课的学生
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
