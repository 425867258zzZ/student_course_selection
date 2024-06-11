package com.ustc.studentcourseselection.controller;

import com.ustc.studentcourseselection.dao.CourseDao;
import com.ustc.studentcourseselection.dao.StudentCourseDao;
import com.ustc.studentcourseselection.dao.TeacherCourseDao;
import com.ustc.studentcourseselection.dao.TeacherDao;
import com.ustc.studentcourseselection.model.BaseUtils;
import com.ustc.studentcourseselection.model.Course;
import com.ustc.studentcourseselection.model.Student;
import com.ustc.studentcourseselection.model.Teacher;
import com.ustc.studentcourseselection.util.DBconnection;
import com.ustc.studentcourseselection.view.UIUtil;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.List;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;


/**
 * The type Teacher course utils.
 *
 * @author 潘义良
 */
public class TeacherCourseUtils {

    /**
     * The constant scrollPane.
     */
    public static JScrollPane scrollPane = new JScrollPane();


    /**
     * Refresh course for course info.
     *
     * @param courseData the course data
     */
    public static void refreshCourseForCourseInfo(Vector<Vector<String>> courseData) {
        CourseDao courseDao = new CourseDao();
        for (Vector<String> course : courseData) {
            Course thisCourse = courseDao.query(course.getFirst());
            String studentNow = Integer.toString(CourseDao.selectCount(thisCourse.getId()));
            course.set(7, studentNow);
        }
    }

    /**
     * Save data to database.
     *
     * @param table the table
     */

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
            System.out.println(e.getMessage());
            Icon customIcon = new ImageIcon("src/main/resources/images/error.png");
            UIUtil.showScaledIconMessage(scrollPane, "保存失败！", "提示", customIcon);
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
    }

    /**
     * Sets course gui.
     */

    public static void setupCourseGui() {

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

        JButton searchButton = new JButton("添加课程");
        searchButton.setForeground(Color.BLACK);
        searchButton.setBackground(new Color(225, 255, 255));
        searchButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        searchButton.setFocusPainted(false);

        // 添加一行空数据并设置为可编辑
        Object[] emptyRow = new Object[tableModel.getColumnCount()];
        tableModel.addRow(emptyRow);


        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.add(scrollPane, BorderLayout.CENTER);
        contentPane.add(searchButton, BorderLayout.SOUTH);

        searchButton.addActionListener(e -> addCourseToDatabase(tableModel,frame));

        frame.setContentPane(contentPane);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * Add course to database.
     *
     * @param table the table
     * @param frame the frame
     */
    @SuppressWarnings("rawtypes")
    public static void addCourseToDatabase(DefaultTableModel table, JFrame frame) {
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
                boolean isnull = false;
                int col = 8;
                for (int i = 0; i < col; i++) {
                    if(row.get(i) == null){
                        isnull = true;
                        break;
                    }
                }
                if(isnull){
                    Icon customIcon = new ImageIcon("src/main/resources/images/error.png");
                    UIUtil.showScaledIconMessage(scrollPane, "申请失败！不能有空值！","提示", customIcon);
                }
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
            // 添加课程成功后关闭窗口
            frame.dispose();
            Icon customIcon = new ImageIcon("src/main/resources/images/success.png");
            UIUtil.showScaledIconMessage(scrollPane, "申请成功！", "提示", customIcon);
        } catch (SQLException ex) {
            Icon customIcon = new ImageIcon("src/main/resources/images/error.png");
            UIUtil.showScaledIconMessage(scrollPane, "申请失败！"+ ex.getMessage(),"提示", customIcon);
            System.out.println(ex.getMessage());
            frame.dispose();
        } finally {
            DBconnection.closeConnection(null, ps, connection);
        }
    }

    /**
     * Query student info.
     *
     * @param number the number
     */
    public static void queryStudentInfo(String number){
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

        List<Student> students = StudentCourseDao.getStudentsForCourse(courseId);

        JFrame frame = new JFrame("课程班级信息");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 600);

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("名字");model.addColumn("学号");model.addColumn("性别");model.addColumn("年级");model.addColumn("学历");model.addColumn("专业");model.addColumn("班级");

        if (students != null) {
            for (Student student : students) {
                model.addRow(new Object[]{
                        student.getName(), student.getNumber(), student.getGender(), student.getGrade(), student.getDegree(), student.getMajor(), student.getClassName()
                });
            }
        }

        JLabel stuNum = new JLabel("学号:");
        stuNum.setForeground(Color.black);
        stuNum.setFont(new Font("微软雅黑", Font.PLAIN, 13));
        stuNum.setBounds(110, 13, 95, 25);

        JLabel courseNam = new JLabel("姓名:");
        courseNam.setForeground(Color.black);
        courseNam.setFont(new Font("微软雅黑", Font.PLAIN, 13));
        courseNam.setBounds(260, 13, 95, 25);

        JTextField stuNumber = new JTextField();
        stuNumber.setFont(new Font("微软雅黑", Font.PLAIN, 13));
        stuNumber.setForeground(Color.black);
        stuNumber.setBounds(160, 13, 90, 25);
        stuNumber.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        stuNumber.setColumns(8);

        JTextField stuName = new JTextField();
        stuName.setFont(new Font("微软雅黑", Font.PLAIN, 13));
        stuName.setForeground(Color.black);
        stuName.setBounds(310, 13, 90, 25);
        stuName.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        stuName.setColumns(8);

        JButton searchButton = getjButton(stuNumber, stuName, model);
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        inputPanel.add(stuNum);
        inputPanel.add(stuNumber);
        inputPanel.add(courseNam);
        inputPanel.add(stuName);
        inputPanel.add(searchButton);
        frame.add(inputPanel, BorderLayout.NORTH);

        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    @NotNull
    private static JButton getjButton(JTextField stuNumber, JTextField stuName, DefaultTableModel model) {
        JButton searchButton = new JButton("搜索");
        searchButton.setForeground(Color.BLACK);
        searchButton.setBackground(new Color(225, 255, 255));
        searchButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        searchButton.setFocusPainted(false);

        searchButton.addActionListener(e -> {
            Vector<Vector<String>> stuData;
            stuData = TeacherCourseDao.searchStuByNumber(stuNumber.getText(), stuName.getText());
            model.setRowCount(0);
            if (stuData != null) {
                for (Vector<String> student : stuData) {
                    model.addRow(new Vector<>(student));
                }
            }
        });
        return searchButton;
    }

    /**
     * Gets message.
     *
     * @param teacherName the teacher name
     * @return the message
     */

    public static JLabel getMessage(String teacherName) {

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
            // 用于标记是否为第一个课程
            while (rs != null && rs.next()) {
                if (!first) {
                    result.append(", ");
                    // 在非第一个课程后面添加逗号和空格
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

        String text = result.toString();
        JLabel info = new JLabel(text);
        info.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        info.setBounds(20, 200, 450, 305);
        return info;
    }

    /**
     * Change password.
     *
     * @param teacher the teacher
     */
    public static void changePassword (Teacher teacher){

        JFrame frame = new JFrame("修改密码");
        JLabel oldPasswordLabel = new JLabel("输入原密码:");
        oldPasswordLabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        JPasswordField oldPasswordField = new JPasswordField(20);

        JLabel newPasswordLabel = new JLabel("输入新密码:");
        newPasswordLabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        JPasswordField newPasswordField = new JPasswordField(20);

        //占位标签
        JLabel occupancy = new JLabel();

        JButton changeButton = new JButton("修改");
        changeButton.setForeground(Color.BLACK);
        changeButton.setBackground(new Color(225, 255, 255));
        changeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        changeButton.setFocusPainted(false);
        JFrame tempFrame = new JFrame();
        tempFrame.pack();

        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(oldPasswordLabel);
        panel.add(oldPasswordField);
        panel.add(newPasswordLabel);
        panel.add(newPasswordField);
        panel.add(occupancy);
        panel.add(changeButton);

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        changeButton.addActionListener(e -> {
            String oldPassword = new String(oldPasswordField.getPassword());
            String newPassword = new String(newPasswordField.getPassword());

            if (teacher.getPassword().equals(oldPassword)) {
                TeacherDao.updatePassword(teacher.getId(), newPassword);
                teacher.setPassword(newPassword);
                frame.dispose();
            } else {
                Icon customIcon = new ImageIcon("src/main/resources/images/error.png");
                UIUtil.showScaledIconMessage(scrollPane, "原密码错误！", "提示", customIcon);
            }
        });

    }
}






