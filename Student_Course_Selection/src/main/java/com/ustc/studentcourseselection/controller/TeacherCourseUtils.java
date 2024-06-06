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
 * @author 潘义良
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

    //教师修改数据
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
            UIUtil.showScaledIconMessage(scrollPane, "保存成功！", "提示", customIcon);
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
            Icon customIcon = new ImageIcon("src/main/resources/images/error.png");
            UIUtil.showScaledIconMessage(scrollPane, "保存失败！", "提示", customIcon);
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

    //开课申请
    public static void setupCourseGUI() {
        JFrame frame = new JFrame("申请开课");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setPreferredSize(new Dimension(600, 400));

        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("课程编号");
        tableModel.addColumn("课程名称");
        tableModel.addColumn("课程时间");
        tableModel.addColumn("专业");
        tableModel.addColumn("地点");
        tableModel.addColumn("教师姓名");
        tableModel.addColumn("学分");
        tableModel.addColumn("容量");

        JTable table = new JTable(tableModel);
        table.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(table);

        JButton addButton = new JButton("添加课程");
        addButton.setForeground(Color.BLACK);
        addButton.setBackground(new Color(225, 255, 255));
        addButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        addButton.setFocusPainted(false);

        // 添加一行空数据并设置为可编辑
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
                //f非空提醒
                for (int i = 0; i < 8; i++) {
                    if(row.get(i) == null){
                        isnull = true;
                        break;
                    }
                }
                if(isnull){
                    Icon customIcon = new ImageIcon("src/main/resources/images/error.png");
                    UIUtil.showScaledIconMessage(scrollPane, "申请失败！不能有空值！","提示", customIcon);
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
            // 添加课程成功后关闭窗口
            frame.dispose();
            Icon customIcon = new ImageIcon("src/main/resources/images/success.png");
            UIUtil.showScaledIconMessage(scrollPane, "申请成功！", "提示", customIcon);
        } catch (SQLException ex) {
            Icon customIcon = new ImageIcon("src/main/resources/images/error.png");
            UIUtil.showScaledIconMessage(scrollPane, "申请失败！"+ ex.getMessage(),"提示", customIcon);
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
        //找到课程id
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int courseId = -1;

        try {
            connection = DBconnection.getConnection();
            String sql = "SELECT id FROM course WHERE number = ?";

            ps = connection.prepareStatement(sql);
            ps.setString(1, number);

            // 执行查询
            rs = ps.executeQuery();

            if (rs.next()) {
                courseId = rs.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBconnection.closeConnection(rs, ps, connection);
        }

        //展示学生信息
        List<Student> students = StudentCourseDao.getStudentsForCourse(courseId);
        JFrame frame;
        JTable table;

        frame = new JFrame("课程班级信息");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 600);

            // 创建表格模型
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("名字");
        model.addColumn("学号");
        model.addColumn("性别");
        model.addColumn("年级");
        model.addColumn("学历");
        model.addColumn("专业");
        model.addColumn("班级");

            // 添加学生信息到表格模型
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




