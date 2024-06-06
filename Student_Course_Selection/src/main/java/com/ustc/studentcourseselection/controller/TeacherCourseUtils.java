package com.ustc.studentcourseselection.controller;

import com.ustc.studentcourseselection.dao.CourseDao;
import com.ustc.studentcourseselection.dao.StudentCourseDao;
import com.ustc.studentcourseselection.model.BaseUtils;
import com.ustc.studentcourseselection.model.Course;
import com.ustc.studentcourseselection.model.Student;
import com.ustc.studentcourseselection.model.Teacher;
import com.ustc.studentcourseselection.util.DBconnection;
import com.ustc.studentcourseselection.view.UIUtil;
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.List;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;


/**
 * @author ������
 */
public class TeacherCourseUtils {

    public static JScrollPane scrollPane = new JScrollPane();


    public static void refreshCourseForCourseInfo(Vector<Vector<String>> courseData) {
        CourseDao courseDao = new CourseDao();
        for (Vector<String> course : courseData) {
            Course thisCourse = courseDao.query(course.getFirst());
            String studentNow = Integer.toString(CourseDao.selectCount(thisCourse.getId()));
            course.set(7, studentNow);
        }
    }

    //��ʦ�޸�����
    public static void saveDataToDatabase(JTable table) {
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
            }
            Icon customIcon = new ImageIcon("src/main/resources/images/success.png");
            UIUtil.showScaledIconMessage(scrollPane, "����ɹ���", "��ʾ", customIcon);
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
            Icon customIcon = new ImageIcon("src/main/resources/images/error.png");
            UIUtil.showScaledIconMessage(scrollPane, "����ʧ�ܣ�", "��ʾ", customIcon);
        } finally {
            // Close the connection
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //��������
    public static void setupCourseGUI() {
        JFrame frame = new JFrame("���뿪��");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setPreferredSize(new Dimension(600, 400));

        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("�γ̱��");
        tableModel.addColumn("�γ�����");
        tableModel.addColumn("�γ�ʱ��");
        tableModel.addColumn("רҵ");
        tableModel.addColumn("�ص�");
        tableModel.addColumn("��ʦ����");
        tableModel.addColumn("ѧ��");
        tableModel.addColumn("����");

        JTable table = new JTable(tableModel);
        table.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(table);

        JButton addButton = new JButton("��ӿγ�");
        addButton.setForeground(Color.BLACK);
        addButton.setBackground(new Color(225, 255, 255));
        addButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        addButton.setFocusPainted(false);

        // ���һ�п����ݲ�����Ϊ�ɱ༭
        Object[] emptyRow = new Object[tableModel.getColumnCount()];
        tableModel.addRow(emptyRow);


        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.add(scrollPane, BorderLayout.CENTER);
        contentPane.add(addButton, BorderLayout.SOUTH);

        addButton.addActionListener(e -> addCourseToDatabase(tableModel,frame));

        frame.setContentPane(contentPane);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void addCourseToDatabase(DefaultTableModel table, JFrame frame) {
        Vector<Vector> data = table.getDataVector();

        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = DBconnection.getConnection();
            String sql = "INSERT INTO course(number, course_name, course_time, major, location, teacher_name, score, capacity, create_time, update_time) VALUES(?,?,?,?,?,?,?,?,?,?)";
            ps = connection.prepareStatement(sql);

            for (Vector<Object> row : data) {
                boolean isnull = false;
                //f�ǿ�����
                for (int i = 0; i < 8; i++) {
                    if(row.get(i) == null){
                        isnull = true;
                        break;
                    }
                }
                if(isnull){
                    Icon customIcon = new ImageIcon("src/main/resources/images/error.png");
                    UIUtil.showScaledIconMessage(scrollPane, "����ʧ�ܣ������п�ֵ��","��ʾ", customIcon);
                    frame.dispose();
                }
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

            ps.executeBatch();
            // ��ӿγ̳ɹ���رմ���
            frame.dispose();
            Icon customIcon = new ImageIcon("src/main/resources/images/success.png");
            UIUtil.showScaledIconMessage(scrollPane, "����ɹ���", "��ʾ", customIcon);
        } catch (SQLException ex) {
            Icon customIcon = new ImageIcon("src/main/resources/images/error.png");
            UIUtil.showScaledIconMessage(scrollPane, "����ʧ�ܣ�"+ ex.getMessage(),"��ʾ", customIcon);
            ex.getMessage();
            frame.dispose();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
    public static void quaryStudentInfo(String number){
        //�ҵ��γ�id
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int courseId = -1;

        try {
            connection = DBconnection.getConnection();
            String sql = "SELECT id FROM course WHERE number = ?";

            ps = connection.prepareStatement(sql);
            ps.setString(1, number);

            // ִ�в�ѯ
            rs = ps.executeQuery();

            if (rs.next()) {
                courseId = rs.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBconnection.closeConnection(rs, ps, connection);
        }

        //չʾѧ����Ϣ
        List<Student> students = StudentCourseDao.getStudentsForCourse(courseId);
        JFrame frame;
        JTable table;

        frame = new JFrame("�γ̰༶��Ϣ");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 600);

            // �������ģ��
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("����");
        model.addColumn("ѧ��");
        model.addColumn("�Ա�");
        model.addColumn("�꼶");
        model.addColumn("ѧ��");
        model.addColumn("רҵ");
        model.addColumn("�༶");

            // ���ѧ����Ϣ�����ģ��
        if (students != null) {
            for (Student student : students) {
                model.addRow(new Object[]{
                        student.getName(),
                        student.getNumber(),
                        student.getGender(),
                        student.getGrade(),
                        student.getDegree(),
                        student.getMajor(),
                        student.getClassName()
                });
            }
        }

        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

}




