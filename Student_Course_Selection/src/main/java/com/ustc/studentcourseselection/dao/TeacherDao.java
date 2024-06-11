package com.ustc.studentcourseselection.dao;

import com.ustc.studentcourseselection.model.BaseObject;
import com.ustc.studentcourseselection.model.BaseUtils;
import com.ustc.studentcourseselection.model.Teacher;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

/**
 * @author ������
 */

public class TeacherDao {
    /**
     * �޸�����
     */
    public static boolean updatePassword(int teacherId, String newPassword) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DBconnection.getConnection();

            String sql = "UPDATE teacher SET password = ? WHERE id = ?";
            if (conn != null) {
                stmt = conn.prepareStatement(sql);
            }

            if (stmt != null) {
                stmt.setString(1, newPassword);
                stmt.setInt(2, teacherId);
            }

            if (stmt != null) {
                return stmt.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return false;
    }

    /**
     * �����ʦ
     *
     * @param baseObject ��ʦ
     * @return ����ֵ
     */

    public boolean add(BaseObject baseObject) {
        Teacher teacher = (Teacher) baseObject;
        Connection connection = DBconnection.getConnection();
        String sql1 = "INSERT INTO teacher(id,name,number,gender,department,password,create_Time,update_Time) VALUES(?,?,?,?,?,?,?,?)";
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
        String sql3 = "UPDATE teacher SET name=?, number=?, gender=?, department=?, update_Time=? WHERE id=?";
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
                        rs.getString("Password"), rs.getString("Create_Time"),
                        rs.getString("Update_Time"));
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

    public Vector<Vector<String>> queryAll(String name, String number, String department) {
        Connection connection = DBconnection.getConnection();
        String sql5 = "SELECT * FROM teacher WHERE 1=1";
        if (name != null && !name.isEmpty()) {
            sql5 += " AND name LIKE '%" + name + "%'";
        }
        if (number != null && !number.isEmpty()) {
            sql5 += " AND number LIKE '%" + number + "%'";
        }
        if (department != null && !department.isEmpty()) {
            sql5 += " AND major LIKE '%" + department + "%'";
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
            Vector<Vector<String>> teachers = new Vector<>();
            if (rs != null) {
                while (rs.next()) {
                    Vector<String> teacher = new Vector<>();
                    teacher.add(rs.getString("name"));
                    teacher.add(rs.getString("number"));
                    teacher.add(rs.getString("gender"));
                    teacher.add(rs.getString("department"));
                    teachers.add(teacher);
                }
            }
            return teachers;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            DBconnection.closeConnection(null, ps, connection);
        }
        return null;
    }

}
