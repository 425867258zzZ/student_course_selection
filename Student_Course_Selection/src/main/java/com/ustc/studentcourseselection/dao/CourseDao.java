package com.ustc.studentcourseselection.dao;

import com.ustc.studentcourseselection.model.BaseUtils;
import com.ustc.studentcourseselection.model.Course;
import com.ustc.studentcourseselection.util.DBconnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


/**
 * �γ������ݿ⽻���߼�
 *
 * @author ���
 */
public class CourseDao {
    /**
     * ��������
     *
     * @param course �γ�
     * @return �Ƿ�ɹ�
     */
    public boolean addCourse(Course course) {
        Connection connection = DBconnection.getConnection();
        String sql1 = "INSERT INTO course(id,number,courseName,courseTime,major,location,score,capacity,create_time,update_time) VALUES(?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = null;
        try {
            if (connection != null) {
                ps = connection.prepareStatement(sql1);
            }
            if (ps != null) {
                ps.setInt(1, course.getId());
                ps.setString(2, course.getNumber());
                ps.setString(3, course.getCourseName());
                ps.setString(4, course.getCourseTime());
                ps.setString(5, course.getMajor());
                ps.setString(6, course.getLocation());
                ps.setInt(7, course.getScore());
                ps.setInt(8, course.getCapacity());
                ps.setString(9, course.getCreateTime());
                ps.setString(10, course.getUpdateTime());
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
     * ɾ���γ�
     *
     * @param number �γ̺�
     * @return �Ƿ�ɹ�
     */
    public boolean delCourse(String number) {
        Connection connection = DBconnection.getConnection();
        String sql2 = "DELETE FROM course WHERE number = ?";
        PreparedStatement ps = null;
        try {
            if (connection != null) {
                ps = connection.prepareStatement(sql2);
            }
            if (ps != null) {
                ps.setString(1, number);
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
     * ���¿γ�
     *
     * @param course �γ�
     * @return �Ƿ�ɹ�
     */
    public boolean update(Course course) {
        Connection connection = DBconnection.getConnection();
        String sql2 = "UPDATE course SET number=?,courseName=?,courseTime=?,major=?,location=?,score=?,capacity=?, update_time=? WHERE id=?";
        PreparedStatement ps = null;
        try {
            if (connection != null) {
                ps = connection.prepareStatement(sql2);
            }
            if (ps != null) {
                ps.setString(1, course.getNumber());
                ps.setString(2, course.getCourseName());
                ps.setString(3, course.getCourseTime());
                ps.setString(4, course.getMajor());
                ps.setString(5, course.getLocation());
                ps.setInt(6, course.getScore());
                ps.setInt(7, course.getCapacity());
                ps.setString(8, BaseUtils.getTime());
                ps.setInt(9, course.getId());
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
     * ��ѯ�����γ�
     *
     * @param number �γ̺�
     * @return �γ�
     */
    public Course query(String number) {
        Connection connection = DBconnection.getConnection();
        String sql2 = "SELECT * FROM course WHERE number = ?";
        PreparedStatement ps = null;
        try {

            if (connection != null) {
                ps = connection.prepareStatement(sql2);
            }
            if (ps != null) {
                ps.setString(1, number);
            }
            ResultSet rs = null;
            if (ps != null) {
                rs = ps.executeQuery();
            }
            if (rs != null && rs.next()) {
                return new Course(rs.getInt("id"), rs.getString("createTime"), rs.getString("updateTime"), rs.getString("number"), rs.getString("courseName"),
                        rs.getString("courseTime"), rs.getString("major"), rs.getString("location"), rs.getInt("score"),
                        rs.getInt("capacity"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            DBconnection.closeConnection(null, ps, connection);
        }
        return null;
    }

    /**
     * ��ѯ���пγ�
     *
     * @return ���пγ�
     */
    public List<Course> queryAll() {
        Connection connection = DBconnection.getConnection();
        String sql2 = "SELECT * FROM course";
        PreparedStatement ps = null;
        try {
            if (connection != null) {
                ps = connection.prepareStatement(sql2);
            }
            ResultSet rs = null;
            if (ps != null) {
                rs = ps.executeQuery();
            }
            List<Course> courses = new ArrayList<>();
            if (rs != null) {
                while (rs.next()) {
                    Course course = new Course(rs.getInt("id"), rs.getString("createTime"), rs.getString("updateTime"), rs.getString("number"), rs.getString("courseName"),
                            rs.getString("courseTime"), rs.getString("major"), rs.getString("location"), rs.getInt("score"),
                            rs.getInt("capacity"));
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

    /**
     * ���ص�ǰ�γ���ѡ������
     *
     * @param id �γ�id
     * @return ѡ������
     */
    public int selectCount(int id) {
        Connection connection = DBconnection.getConnection();
        String sql = """
                SELECT course_id, COUNT(student_id) AS student_count
                FROM student_course
                WHERE course_id = ?
                GROUP BY course_id;""";
        if (connection != null) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, id);
                try (ResultSet resultSet = ps.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getInt("student_count");
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return -1;
    }
}
