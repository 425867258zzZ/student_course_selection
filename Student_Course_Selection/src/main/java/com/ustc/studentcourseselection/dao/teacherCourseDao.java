package com.ustc.studentcourseselection.dao;

import com.ustc.studentcourseselection.model.BaseUtils;
import com.ustc.studentcourseselection.model.Course;
import com.ustc.studentcourseselection.util.DBconnection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;


/**
 * ��ʦ��γ̺ͽ����߼�
 *
 * @author ������
 */
@SuppressWarnings("rawtypes")
public class TeacherCourseDao {


    /**
     * ������ʦ���������ݿ��в�ѯ����ʦ��ѡ�Ŀγ��б�
     *
     * @param teacherName ��ʦ����
     * @return ����ʦѡ�����пγ�
     */
    public static List<Course> getCoursesForTeacher(String teacherName) {
        List<Course> courses = new ArrayList<>();
        Connection connection = DBconnection.getConnection();
        String sql3 = "SELECT * FROM course WHERE teacher_name = ?";
        PreparedStatement ps = null;
        try {
            if (connection != null) {
                ps = connection.prepareStatement(sql3);
            }
            if (ps != null) {
                ps.setString(1, teacherName);
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
     * ͨ��ѧ�ţ����ֲ�ѯѧ��
     *
     * @param stuNumber ѧ��
     * @param stuName ѧ������
     * @return ����������ѧ��
     */
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


    /**
     * ����γ̱༭������
     *
     * @param table �γ���Ϣ��
     */
    public static boolean saveDataToDatabase(JTable table) {
        int rowCount = table.getRowCount();
        Connection connection = DBconnection.getConnection();
        try {
            // Loop through each row
            for (int i = 0; i < rowCount; i++) {
                String column1Data  = table.getValueAt(i, 0).toString();
                String column3Data = table.getValueAt(i, 2).toString();
                String column5Data = table.getValueAt(i, 4).toString();
                String column9Data = table.getValueAt(i, 8).toString();

                // Your SQL update statement to update the database
                String updateQuery = "UPDATE course SET course_time = ?, location = ?, capacity = ?, update_time = ? WHERE number = ?";
                PreparedStatement preparedStatement = null;
                if (connection != null) {
                    preparedStatement = connection.prepareStatement(updateQuery);
                }

                // Set values for the prepared statement
                if (preparedStatement != null) {
                    preparedStatement.setString(1, column3Data);
                    preparedStatement.setString(2, column5Data);
                    preparedStatement.setString(3, column9Data);
                    preparedStatement.setString(4, BaseUtils.getTime());
                    preparedStatement.setString(5, column1Data);
                }

                // Execute the update query
                if (preparedStatement != null) {
                    preparedStatement.executeUpdate();
                }
            }

            // Commit the transaction if needed
            if (connection != null) {
                connection.setAutoCommit(false);
                connection.commit();
                return true;
            }

        } catch (SQLException e) {
            return false;
        } finally {
            // Close the connection
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return false;
    }


    /**
     * ��ʦ��ӿγ�
     *
     * @param table ���뿪�α�
     */
    public static boolean addCourseToDatabase(DefaultTableModel table){
        Vector<Vector> data = table.getDataVector();
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = DBconnection.getConnection();
            String sql = "INSERT INTO course(number, course_name, course_time, major, location, teacher_name, score, capacity, create_time, update_time) VALUES(?,?,?,?,?,?,?,?,?,?)";
            if (connection != null) {
                ps = connection.prepareStatement(sql);
            }

            for (Vector row : data) {

                if (ps != null) {
                    ps.setString(1, (String) row.get(0));
                    ps.setString(2, (String) row.get(1));
                    ps.setString(3, (String) row.get(2));
                    ps.setString(4, (String) row.get(3));
                    ps.setString(5, (String) row.get(4));
                    ps.setString(6, (String) row.get(5));
                    ps.setFloat(7, Float.parseFloat((String) row.get(6)));
                    ps.setInt(8, Integer.parseInt((String)row.get(7)));
                    ps.setString(9, BaseUtils.getTime());
                    ps.setString(10, BaseUtils.getTime());
                    ps.addBatch();
                }
            }

            if (ps != null) {
                ps.executeBatch();
            }
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        } finally {
            DBconnection.closeConnection(null, ps, connection);
        }
    }

    /**
     * ��ȡ�γ�id
     *
     * @param number �γ̺�
     */
    public static int getCourseId(String number){
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int courseId = -1;

        try {
            connection = DBconnection.getConnection();
            String sql = "SELECT id FROM course WHERE number = ?";
            if (connection != null) {
                ps = connection.prepareStatement(sql);
                ps.setString(1, number);
                rs = ps.executeQuery();
            }

            if (rs != null && rs.next()) {
                courseId = rs.getInt("id");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            DBconnection.closeConnection(rs, ps, connection);
        }
        return courseId;
    }

    /**
     * ��ȡ�γ̺��Լ��γ���
     *
     * @param teacherName ��ʦ��
     * @return ���ؿγ̺ż��γ���
     */
    public static String getCourseNumAndName(String teacherName) {

        Connection connection = DBconnection.getConnection();
        String sql = "SELECT number, course_name FROM course WHERE teacher_name = ?";
        StringBuilder result = new StringBuilder();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            if (connection != null) {
                ps = connection.prepareStatement(sql);
            }
            if (ps != null) {
                ps.setString(1, teacherName);
                rs = ps.executeQuery();
            }

            boolean first = true;
            // ���ڱ���Ƿ�Ϊ��һ���γ�
            while (rs != null && rs.next()) {
                if (!first) {
                    result.append(", ");
                    // �ڷǵ�һ���γ̺�����Ӷ��źͿո�
                } else {
                    first = false;
                }
                result.append(rs.getString("number")).append(":").append(rs.getString("course_name"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            DBconnection.closeConnection(rs, ps, connection);
        }

        return result.toString();

    }

}

