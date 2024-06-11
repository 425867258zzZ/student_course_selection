package com.ustc.studentcourseselection.dao;

import com.ustc.studentcourseselection.model.BaseObject;
import com.ustc.studentcourseselection.model.BaseUtils;
import com.ustc.studentcourseselection.model.Course;
import com.ustc.studentcourseselection.model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

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
        String sql1 = "INSERT INTO student(id,name,number,gender,grade,degree,major,class_Name,password,create_Time,update_Time) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
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
    public boolean studentIdGet(String number, String name, String gender, int grade, String degree, String major, String className, String createTime, String updateTime) {
        int id = 0;
        StudentDao sd=new StudentDao();

        Connection connection = DBconnection.getConnection();
        System.out.println("successful");
        String sql = "SELECT id FROM student ORDER BY id DESC LIMIT 1;";
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
        return sd.addStudent(new Student(id, name,number,gender,grade,degree,major,className,"123456",createTime, updateTime));
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
        String sql3 = "UPDATE student SET name=?, number=?, gender=?, grade=?, degree=?, major=?, class_Name=?, update_Time=? WHERE id=?";
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
     * 修改密码
     *
     * @return 是否成功
     */
    public boolean passwordUpdate(Student student){
        Connection connection = DBconnection.getConnection();
        String sql3 = "UPDATE student SET password=? WHERE id=?";
        PreparedStatement ps = null;
        try {
            if (connection != null) {
                ps = connection.prepareStatement(sql3);
            }
            if (ps != null) {
                ps.setString(0,student.getPassword());
                ps.setInt(1,student.getId());
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
                Student student = new Student(rs.getInt("id"), rs.getString("name"), rs.getString("number"), rs.getString("gender"), rs.getInt("grade"), rs.getString("degree"), rs.getString("major"), rs.getString("class_Name"), rs.getString("password"), rs.getString("create_Time"), rs.getString("update_Time"));
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
    public Vector<Vector<String>> queryAll(String name, String number, String major, String className) {
        Connection connection = DBconnection.getConnection();
        String sql5 = "SELECT * FROM student WHERE 1=1";
        if(name!=null && !name.isEmpty()){
            sql5+=" AND name LIKE '%"+name+"%'";
        }
        if(number!=null && !number.isEmpty()){
            sql5+=" AND number LIKE '%"+number+"%'";
        }
        if(major!=null && !major.isEmpty()){
            sql5+=" AND major LIKE '%"+major+"%'";
        }
        if(className!=null && !className.isEmpty()){
            sql5+=" AND class_Name LIKE '%"+className+"%'";
        }
        PreparedStatement ps = null;
        try {
            if (connection != null) {
                ps = connection.prepareStatement(sql5);
            }
            ResultSet rs = null;
            if (ps != null) {
                rs = ps.executeQuery();
            }
            Vector<Vector<String>> students = new Vector<>();
            if (rs != null) {
                while (rs.next()) {
                    Vector<String> student = new Vector<>();
                    student.add(rs.getString("number"));
                    student.add(rs.getString("name"));
                    student.add(rs.getString("gender"));
                    student.add(rs.getString("major") + "学院");
                    student.add(rs.getString("grade"));
                    student.add(rs.getString("degree"));
                    student.add(rs.getString("class_name"));
                    students.add(student);
                }
            }
            return students;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            DBconnection.closeConnection(null, ps, connection);
        }
        return null;
    }
}