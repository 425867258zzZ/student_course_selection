package com.ustc.studentcourseselection.dao;

import com.ustc.studentcourseselection.model.BaseObject;
import com.ustc.studentcourseselection.model.BaseUtils;
import com.ustc.studentcourseselection.model.Student;
import com.ustc.studentcourseselection.util.DBconnection;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * @author ������
 */
public class StudentDao implements ObjectDao {

    private static boolean sqlDeal(Student student, Connection connection, String sql) {
        try {
            PreparedStatement ps = null;
            if (connection != null) {
                ps = connection.prepareStatement(sql);
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
            BaseObject object = Factory.createModel(Model.Student, 4, "�Ź���", "PB23111645", "��", 2023, "����", "��ѧ", "1��", "123456", BaseUtils.getTime(), BaseUtils.getTime());
            ObjectDao dao = (ObjectDao) Class.forName("com.ustc.studentcourseselection.dao." + Model.Student.name() + "Dao").getConstructor().newInstance();
            dao.add(object);
        } catch (RuntimeException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            System.out.println("Creation failed");
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * ���ѧ��
     *
     * @param baseObject ѧ��
     * @return ����ֵ
     */


    @Override
    public boolean add(BaseObject baseObject) {
        Student student = (Student) baseObject;
        Connection connection = DBconnection.getConnection();
        String sql1 = "INSERT INTO student(id,name,number,gender,grade,degree,major,className,password,createTime,updateTime) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        return sqlDeal(student, connection, sql1);
    }

    /**
     * ɾ��ѧ��
     *
     * @param number ѧ��
     * @return ����ֵ
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
     * �޸�ѧ������
     * TODO ����sql���ʹ����߼�
     *
     * @param object ѧ��
     * @return ����ֵ
     */
    @Override
    public boolean update(BaseObject object) {
        Student student = (Student) object;
        Connection connection = DBconnection.getConnection();
        String sql2 = "";
        return sqlDeal(student, connection, sql2);
    }

    /**
     * ��ѯ����ѧ��
     *
     * @param number ѧ��
     * @return ѧ��
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
     * ��������ѧ��
     * TODO ����
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