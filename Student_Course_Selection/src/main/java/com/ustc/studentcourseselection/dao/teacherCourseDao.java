package com.ustc.studentcourseselection.dao;

import com.ustc.studentcourseselection.util.DBconnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import static com.ustc.studentcourseselection.dao.CourseDao.selectCount;

/**
 * 教师与课程和交互逻辑
 *
 * @author 孟梓晗
 */
public class TeacherCourseDao {
    /**
     * 获取老师教的所有课程
     *
     * @param teacherName 老师名字
     * @return 该老师教的所有课程
     */
    public static Vector<Vector<String>> getCoursesForTeacher(String teacherName) {
        Connection connection = DBconnection.getConnection();
        String sql2 = "SELECT * FROM course WHERE 1=1";

        if (teacherName != null && !teacherName.isEmpty()) {
            sql2 += " AND teacher_name LIKE '%" + teacherName + "%'";
        }

        PreparedStatement ps = null;
        try {
            if (connection != null) {
                ps = connection.prepareStatement(sql2);
            }

            ResultSet rs = null;
            if (ps != null) {
                rs = ps.executeQuery();
            }

            Vector<Vector<String>> courses = new Vector<>();
            if (rs != null) {
                while (rs.next()) {
                    Vector<String> course = new Vector<>();
                    // 添加查询结果到course向量
                    String selectCount = Integer.toString(selectCount(rs.getInt("id")));
                    course.add(rs.getString("number"));
                    course.add(rs.getString("course_name"));
                    course.add(rs.getString("course_time"));
                    course.add(rs.getString("major") + "学院");
                    course.add(rs.getString("location"));
                    course.add(rs.getString("teacher_name"));
                    course.add(rs.getString("score"));
                    course.add(selectCount);
                    course.add(rs.getString("capacity"));
                    courses.add(course);
                }
            }

            return courses;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            DBconnection.closeConnection(null, ps, connection);
        }

        return null;
    }


    public static Vector<Vector<String>> searchStuByNumber(String stuNumber, String stuName) {
        Connection connection = DBconnection.getConnection();
        String sql = "SELECT * FROM student WHERE 1=1";
        if (stuNumber != null && !stuNumber.isEmpty()) {
            sql += " AND number LIKE '%" + stuNumber + "%'";
        }
        if (stuName != null && !stuName.isEmpty()) {
            sql += " AND name LIKE '%" + stuName + "%'";
        }
        PreparedStatement ps = null;
        try {
            if (connection != null) {
                ps = connection.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();

                Vector<Vector<String>> students = new Vector<>();
                while (rs.next()) {
                    Vector<String> student = new Vector<>();
                    student.add(rs.getString("name"));
                    student.add(rs.getString("number"));
                    student.add(rs.getString("gender"));
                    student.add(rs.getString("grade"));
                    student.add(rs.getString("degree"));
                    student.add(rs.getString("major"));
                    student.add(rs.getString("className"));
                    students.add(student);
                }
                return students;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            DBconnection.closeConnection(null, ps, connection);
        }
        return null;
    }

}

