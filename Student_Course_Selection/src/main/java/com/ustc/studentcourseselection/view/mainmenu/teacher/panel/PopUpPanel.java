package com.ustc.studentcourseselection.view.mainmenu.teacher.panel;

import com.ustc.studentcourseselection.dao.StudentCourseDao;
import com.ustc.studentcourseselection.dao.TeacherCourseDao;
import com.ustc.studentcourseselection.model.Student;
import com.ustc.studentcourseselection.view.UIUtil;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.Vector;

import static com.ustc.studentcourseselection.controller.TeacherCourseUtils.addCourse;


/**
 * 教师系统功能性弹窗
 *
 * @author 潘义良
 */

public class PopUpPanel {

    /**
     * Sets course gui.
     */

    public static void setupCoursePanel(String teacherName) {

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

        searchButton.addActionListener(e ->{

            int mark = addCourse(tableModel,teacherName);

            switch(mark){
                case (1) -> {
                    Icon customIcon = new ImageIcon("src/main/resources/images/error.png");
                    UIUtil.showScaledIconMessage(scrollPane, "申请失败！不能有空值！", "提示", customIcon);
                }
                case (2) ->{
                    Icon customIcon = new ImageIcon("src/main/resources/images/error.png");
                    UIUtil.showScaledIconMessage(scrollPane, "申请失败！网络错误！","提示", customIcon);
                }
                case (3) ->{
                    Icon customIcon = new ImageIcon("src/main/resources/images/success.png");
                    UIUtil.showScaledIconMessage(scrollPane, "申请成功！", "提示", customIcon);
                    frame.dispose();
                }
                case (4) ->{
                    Icon customIcon = new ImageIcon("src/main/resources/images/error.png");
                    UIUtil.showScaledIconMessage(scrollPane, "申请失败！时间冲突！","提示", customIcon);
                }
                case (5) ->{
                    Icon customIcon = new ImageIcon("src/main/resources/images/error.png");
                    UIUtil.showScaledIconMessage(scrollPane, "申请失败！您不能为其他老师开课！","提示", customIcon);
                }
                default ->{
                    Icon customIcon = new ImageIcon("src/main/resources/images/error.png");
                    UIUtil.showScaledIconMessage(scrollPane, "申请失败！","提示", customIcon);
                }

            }
        });

        frame.setContentPane(contentPane);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * Query student info.
     *
     * @param number the number
     */
    public static void queryStudentPanel(String number){

        int courseId = TeacherCourseDao.getCourseId(number);

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
    public static JButton getjButton(JTextField stuNumber, JTextField stuName, DefaultTableModel model) {
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

}
