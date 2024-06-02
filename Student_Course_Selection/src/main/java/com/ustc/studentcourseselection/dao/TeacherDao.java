package com.ustc.studentcourseselection.dao;

import com.ustc.studentcourseselection.model.BaseObject;
import com.ustc.studentcourseselection.model.BaseUtils;
import com.ustc.studentcourseselection.model.Teacher;
import com.ustc.studentcourseselection.util.DBconnection;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

/**
 * @author ������
 */

public class TeacherDao {
    /**
     * �����ʦ
     *
     * @param baseObject ��ʦ
     * @return ����ֵ
     */

    public boolean add(BaseObject baseObject) {
        Teacher teacher = (Teacher) baseObject;
        Connection connection = DBconnection.getConnection();
        String sql1 = "INSERT INTO teacher(id,name,number,gender,department,password,createTime,updateTime) VALUES(?,?,?,?,?,?,?,?)";
        PreparedStatement ps = null;
        try {
            if (connection != null) {
                ps = connection.prepareStatement(sql1);
            }
            if (ps != null) {
                ps.setInt(1, teacher.getId());
                ps.setString(2, teacher.getName());
                ps.setString(3, teacher.getNumber());
                ps.setString(4, teacher.getGender());
                ps.setString(5, teacher.getDepartment());
                ps.setString(6, teacher.getPassword());
                ps.setString(7, BaseUtils.getTime());
                ps.setString(8, BaseUtils.getTime());
            }
            boolean result = false;
            if (ps != null) {
                result = ps.executeUpdate() > 0;
            }
            DBconnection.closeConnection(null, null, connection);
            return result;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            DBconnection.closeConnection(null, ps, connection);
        }
        return false;
    }

    /**
     * ɾ����ʦ
     *
     * @param number ����
     * @return ����ֵ
     */

    public boolean del(String number) {
        Connection connection = DBconnection.getConnection();
        String sql2 = "DELETE FROM teacher WHERE number = ?";
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
     * ������ʦ��Ϣ
     *
     * @param teacher ��ʦ
     * @return �����Ƿ�ɹ��Ĳ���ֵ
     */

    public boolean update(Teacher teacher) {
        Connection connection = DBconnection.getConnection();
        String sql3 = "UPDATE teacher SET name=?, number=?, gender=?, department=?, updateTime=? WHERE id=?";
        PreparedStatement ps = null;
        try {
            if (connection != null) {
                ps = connection.prepareStatement(sql3);
            }
            if (ps != null) {
                ps.setString(1, teacher.getName());
                ps.setString(2, teacher.getNumber());
                ps.setString(3, teacher.getGender());
                ps.setString(4, teacher.getDepartment());
                ps.setString(5, BaseUtils.getTime());
                ps.setInt(6, teacher.getId());
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
     * ��ѯ������ʦ
     *
     * @param number ѧ��
     * @return ��ʦ
     */

    public Teacher query(String number) {
        Connection connection = DBconnection.getConnection();
        String sql4 = "SELECT * FROM teacher WHERE number = ?";
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
                return new Teacher(rs.getInt("Id"), rs.getString("Name"),
                        rs.getString("Number"), rs.getString("Gender"),
                        rs.getString("Department"),
                        rs.getString("Password"), rs.getString("CreateTime"),
                        rs.getString("UpdateTime"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            DBconnection.closeConnection(null, ps, connection);
        }
        return null;
    }

    /**
     * ����������ʦ
     *
     * @return Array<teacher>
     */

    public Vector<Vector> queryAll() {
        Connection connection = DBconnection.getConnection();
        String sql5 = "SELECT * FROM teacher";
        try {
            PreparedStatement ps = null;
            if (connection != null) {
                ps = connection.prepareStatement(sql5);
            }
            ResultSet rs = null;
            if (ps != null) {
                rs = ps.executeQuery();
            }
            Vector<Vector> teachersData = new Vector<>();
            if (rs != null) {
                while (rs.next()) {
                    Vector<String> teacherRow = new Vector<>();
                    teacherRow.add(String.valueOf(rs.getInt("Id")));
                    teacherRow.add(rs.getString("Name"));
                    teacherRow.add(rs.getString("Number"));
                    teacherRow.add(rs.getString("Gender"));
                    teacherRow.add(rs.getString("Department"));
                    teacherRow.add(rs.getString("Course"));
                    teacherRow.add(rs.getString("Password"));
                    teacherRow.add(rs.getString("CreateTime"));
                    teacherRow.add(rs.getString("UpdateTime"));
                    teachersData.add(teacherRow);
                }
            }
            DBconnection.closeConnection(rs, null, connection);
            return teachersData;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

}
