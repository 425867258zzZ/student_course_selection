package com.ustc.studentcourseselection.dao;

import com.ustc.studentcourseselection.model.BaseObject;
import com.ustc.studentcourseselection.model.BaseUtils;
import com.ustc.studentcourseselection.model.Student;
import com.ustc.studentcourseselection.util.DBconnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 孟梓晗
 */
public class StudentDao {

    /**
     * 添加学生
     *
     * @param baseObject 学生
     * @return 布尔值
     */
    public boolean addStudent(BaseObject baseObject) {
        Student student = (Student) baseObject;
        Connection connection = DBconnection.getConnection();
        String sql1 = "INSERT INTO student(id,name,number,gender,grade,degree,major,className,password,createTime,updateTime) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = null;
        try {
            if (connection != null) {
                ps = connection.prepareStatement(sql1);
            }
            if (ps != null) {
                ps.setInt(1, student.getId());
                ps.setString(2, student.getName());
                ps.setString(3, student.getNumber());
                ps.setString(4, student.getGender());
                ps.setInt(5, student.getGrade());
                ps.setString(6, student.getDegree());
                ps.setString(7, student.getMajor());
                ps.setString(8, student.getClassName());
                ps.setString(9, student.getPassword());
                ps.setString(10, student.getCreateTime());
                ps.setString(11, student.getUpdateTime());
            }
            boolean result = false;
            if (ps != null) {
                result = ps.executeUpdate() > 0;
            }
            return result;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            DBconnection.closeConnection(null, ps, connection);
        }
        return false;
    }

    /**
     * 删除学生
     *
     * @param number 学号
     * @return 布尔值
     */

    public boolean delStudent(String number) {
        Connection connection = DBconnection.getConnection();
        String sql2 = "DELETE FROM student WHERE number = ?";
        PreparedStatement ps = null;
        try {
            if (connection != null) {
                ps = connection.prepareStatement(sql2);
            }
            if (ps != null) {
                ps.setString(1, number);
            }
            boolean result = false;
            if (ps != null) {
                result = ps.executeUpdate() > 0;
            }
            return result;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            DBconnection.closeConnection(null, ps, connection);
        }
        return false;
    }

    /**
     * 修改学生数据
     *
     * @param student 学生
     * @return 布尔值
     */
    public boolean update(Student student) {
        Connection connection = DBconnection.getConnection();
        String sql3 = "UPDATE student SET name=?, number=?, gender=?, grade=?, degree=?, major=?, className=?, updateTime=? WHERE id=?";
        PreparedStatement ps = null;
        try {
            if (connection != null) {
                ps = connection.prepareStatement(sql3);
            }
            if (ps != null) {
                ps.setString(1, student.getName());
                ps.setString(2, student.getNumber());
                ps.setString(3, student.getGender());
                ps.setInt(4, student.getGrade());
                ps.setString(5, student.getDegree());
                ps.setString(6, student.getMajor());
                ps.setString(7, student.getClassName());
                ps.setString(8, BaseUtils.getTime());
                ps.setInt(9, student.getId());
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
     * 查询单个学生
     *
     * @param number 学号
     * @return 学生
     */
    public Student query(String number) {
        Connection connection = DBconnection.getConnection();
        String sql4 = "SELECT * FROM student WHERE number = ?";
        PreparedStatement ps = null;
        try {
            if (connection != null) {
                ps = connection.prepareStatement(sql4);
            }
            if (ps != null) {
                ps.setString(1, number);
            }
            ResultSet rs = null;
            if (ps != null) {
                rs = ps.executeQuery();
            }
            if (rs != null && rs.next()) {
                Student student = new Student(rs.getInt("id"), rs.getString("name"), rs.getString("number"), rs.getString("gender"), rs.getInt("grade"), rs.getString("degree"), rs.getString("major"), rs.getString("className"), rs.getString("password"), rs.getString("createTime"), rs.getString("updateTime"));
                DBconnection.closeConnection(rs, ps, connection);
                return student;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            DBconnection.closeConnection(null, ps, connection);
        }
        return null;
    }

    /**
     * 返回所有学生
     *
     * @return Array<student>
     */
    public List<Student> queryAll() {
        Connection connection = DBconnection.getConnection();
        String sql5 = "SELECT * FROM student";
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            if (connection != null) {
                ps = connection.prepareStatement(sql5);
            }
            if (ps != null) {
                rs = ps.executeQuery();
            }
            List<Student> students = new ArrayList<>();
            if (rs != null) {
                while (rs.next()) {
                    Student student = new Student(rs.getInt("id"), rs.getString("name"), rs.getString("number"), rs.getString("gender"), rs.getInt("grade"), rs.getString("degree"), rs.getString("major"), rs.getString("className"), rs.getString("password"), rs.getString("createTime"), rs.getString("updateTime"));
                    students.add(student);
                }
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