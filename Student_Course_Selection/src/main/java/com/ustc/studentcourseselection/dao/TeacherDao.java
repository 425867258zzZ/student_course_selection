package com.ustc.studentcourseselection.dao;

import com.ustc.studentcourseselection.model.BaseObject;
import com.ustc.studentcourseselection.model.BaseUtils;
import com.ustc.studentcourseselection.model.Teacher;
import com.ustc.studentcourseselection.util.DBconnection;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class TeacherDao {
    private static boolean sqlDeal(Teacher teacher, Connection connection, String sql) {
        try {
            PreparedStatement ps = null;
            if (connection != null) {
                ps = connection.prepareStatement(sql);
            }
            if (ps != null) {
                ps.setInt(1, teacher.getId());
                ps.setString(2, teacher.getName());
                ps.setString(3, teacher.getNumber());
                ps.setString(4, teacher.getGender());
                ps.setString(6, teacher.getDepartment());
                ps.setString(7, teacher.getCourse());
                ps.setString(8, teacher.getPassword());
                ps.setString(9, teacher.getCreateTime());
                ps.setString(10, teacher.getUpdateTime());
            }

            boolean result = ps.executeUpdate() > 0;
            com.ustc.studentcourseselection.util.DBconnection.closeConnection(null, null, connection);
            return result;
        } catch (NullPointerException e) {
            System.out.println("NullPointerException");
        } catch (SQLException e) {
            System.out.println("SQLException");
        }
        return false;
    }



    public static void main(String[] args) {
        try {
            BaseObject object = Factory.createModel(Model.Student, 4, "张国立", "PB23111645", "男", 2023, "本科", "数学", "1班", "123456", BaseUtils.getTime(), BaseUtils.getTime());
            ObjectDao dao = (ObjectDao) Class.forName("com.ustc.studentcourseselection.dao." + Model.Student.name() + "Dao").getConstructor().newInstance();
            dao.add(object);
        } catch (RuntimeException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            System.out.println("Creation failed");
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 添加学生
     *
     * @param baseObject 学生
     * @return 布尔值
     */


    @Override
    public boolean add(BaseObject baseObject) {
        Student student = (Student) baseObject;
        Connection connection = DBconnection.getConnection();
        String sql1 = "INSERT INTO student(id,name,number,gender,grade,degree,major,className,password,createTime,updateTime) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        return sqlDeal(student, connection, sql1);
    }

    /**
     * 删除学生
     *
     * @param number 学号
     * @return 布尔值
     */
    @Override
    public boolean del(String number) {
        Connection connection = DBconnection.getConnection();
        String sql2 = "DELETE FROM student WHERE number = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql2);
            ps.setString(1, number);
            boolean result = ps.executeUpdate() > 0;
            DBconnection.closeConnection(null, ps, connection);
            return result;
        } catch (NullPointerException e) {
            System.out.println("NullPointerException");
        } catch (SQLException e) {
            System.out.println("SQLException");
        }
        return false;
    }

    /**
     * 修改学生数据
     * TODO 完善sql语句和处理逻辑
     *
     * @param object 学生
     * @return 布尔值
     */
    @Override
    public boolean update(BaseObject object) {
        Student student = (Student) object;
        Connection connection = DBconnection.getConnection();
        String sql2 = "";
        return sqlDeal(student, connection, sql2);
    }

    /**
     * 查询单个学生
     *
     * @param number 学号
     * @return 学生
     */
    @Override
    public BaseObject query(String number) {
        Connection connection = DBconnection.getConnection();
        String sql2 = "SELECT * FROM student WHERE number = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql2);
            ps.setString(1, number);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Student student = new Student(rs.getInt("id"), rs.getString("name"), rs.getString("number"), rs.getString("gender"), rs.getInt("grade"), rs.getString("degree"), rs.getString("major"), rs.getString("className"), rs.getString("password"), rs.getString("createTime"), rs.getString("updateTime"));
                DBconnection.closeConnection(rs, ps, connection);
                return student;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 返回所有学生
     * TODO 完善
     *
     * @return Array<student>
     */
    @Override
    public Vector<Vector> queryAll() {
        Connection connection = DBconnection.getConnection();
        String sql2 = "SELECT * FROM student";
        try {
            PreparedStatement ps = connection.prepareStatement(sql2);
            ResultSet rs = ps.executeQuery();
            List<Student> students = new ArrayList<Student>();
            while (rs.next()) {
                Student student = new Student(rs.getInt("id"), rs.getString("name"), rs.getString("gender"), rs.getString("name"),
                        rs.getInt("grade"), rs.getString("degree"), rs.getString("major"), rs.getString("className"),
                        rs.getString("password"), rs.getString("createTime"), rs.getString("updateTime"));
                students.add(student);
            }
            DBconnection.closeConnection(rs, null, connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
