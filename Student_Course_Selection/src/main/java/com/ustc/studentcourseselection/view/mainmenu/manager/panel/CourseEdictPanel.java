package com.ustc.studentcourseselection.view.mainmenu.manager.panel;

import com.ustc.studentcourseselection.controller.CourseManagementUtils;
import com.ustc.studentcourseselection.model.BaseUtils;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

/**
 * @author 13901
 */
public class CourseEdictPanel extends JPanel {
    public CourseEdictPanel(JTabbedPane tabbedPane, Vector<String> course, Vector<Vector<String>> courseData) {
        setLayout(null);

        JLabel setNumber = new JLabel("课程编号");
        setNumber.setBounds(100, 40, 300, 30);
        setNumber.setFont(new Font("微软雅黑", Font.PLAIN, 23));
        add(setNumber);

        JTextField setNumberField = new JTextField();
        setNumberField.setBounds(400, 40, 200, 30);
        setNumberField.setText(course.getFirst());
        setNumberField.setEditable(false);
        add(setNumberField);

        JLabel setTeacherName = new JLabel("老师姓名：");
        setTeacherName.setBounds(100, 80, 300, 30);
        setTeacherName.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        add(setTeacherName);

        JTextField setTeacherNameField = new JTextField();
        setTeacherNameField.setBounds(400, 80, 200, 40);
        setTeacherNameField.setText(course.get(5));
        setTeacherNameField.setEditable(false);
        add(setTeacherNameField);

        JLabel setCourseTime = new JLabel("开课时间：");
        setCourseTime.setBounds(100, 140, 300, 30);
        setCourseTime.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        add(setCourseTime);

        JTextField setCourseTimeField = new JTextField();
        setCourseTimeField.setBounds(400, 140, 200, 40);
        setCourseTimeField.setText(course.get(2));
        add(setCourseTimeField);

        JLabel setCourseName = new JLabel("课程名称：");
        setCourseName.setBounds(100, 200, 300, 30);
        setCourseName.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        add(setCourseName);

        JTextField setCourseNameField = new JTextField();
        setCourseNameField.setBounds(400, 200, 200, 40);
        setCourseNameField.setText(course.get(1));
        add(setCourseNameField);

        JLabel setMajor = new JLabel("所属专业：");
        setMajor.setBounds(100, 260, 300, 30);
        setMajor.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        add(setMajor);

        JTextField majorField = new JTextField();
        majorField.setBounds(400, 260, 200, 40);
        majorField.setText(course.get(3).substring(0, course.get(3).length() - 2));
        majorField.setEditable(false);
        add(majorField);

        JLabel setLocation = new JLabel("上课地点：");
        setLocation.setBounds(100, 320, 300, 30);
        setLocation.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        add(setLocation);

        JTextField setLocationField = new JTextField();
        setLocationField.setBounds(400, 320, 200, 40);
        setLocationField.setText(course.get(4));
        add(setLocationField);

        JLabel setScore = new JLabel("课程学分：");
        setScore.setBounds(100, 380, 300, 30);
        setScore.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        add(setScore);

        SpinnerNumberModel model = new SpinnerNumberModel(Integer.parseInt(course.get(6)), 0, 20, 1);
        JSpinner setScoreField = new JSpinner(model);
        setScoreField.setBounds(400, 380, 50, 40);
        add(setScoreField);

        JLabel setCapacity = new JLabel("课堂容量：");
        setCapacity.setBounds(100, 440, 300, 30);
        setCapacity.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        add(setCapacity);

        JTextField setCapacityField = new JTextField();
        setCapacityField.setBounds(400, 440, 80, 40);
        setCapacityField.setText(course.get(8));
        setCapacityField.setEditable(false);
        add(setCapacityField);

        JButton edictButton = new JButton("编辑");
        edictButton.setBounds(270, 540, 80, 40);
        edictButton.addActionListener(e -> {
            if (!setNumberField.getText().isEmpty() && !setCourseNameField.getText().isEmpty() && !setCourseTimeField.getText().isEmpty() && !setLocationField.getText().isEmpty() && !setCapacityField.getText().isEmpty() && !setTeacherNameField.getText().isEmpty()) {
                int choice = JOptionPane.showConfirmDialog(CourseEdictPanel.this, "你确认要进行编辑吗", "确认编辑", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_NO_OPTION) {
                    if (CourseManagementUtils.check(courseData, setTeacherNameField.getText(), setCourseTimeField.getText(), setLocationField.getText(), setNumberField.getText()) == 1) {
                        JOptionPane.showMessageDialog(CourseEdictPanel.this, "该课程在该时间段的上课地点与其他课程冲突", "提示", JOptionPane.INFORMATION_MESSAGE);
                    } else if (CourseManagementUtils.check(courseData, setTeacherNameField.getText(), setCourseTimeField.getText(), setLocationField.getText(), setNumberField.getText()) == 2) {
                        JOptionPane.showMessageDialog(CourseEdictPanel.this, "该时间段的老师有其他课程", "提示", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        if (CourseManagementUtils.courseEdict(setNumberField.getText(), setCourseNameField.getText(), setCourseTimeField.getText(), majorField.getText(), setLocationField.getText(), setTeacherNameField.getText(), (int) setScoreField.getValue(), Integer.parseInt(setCapacityField.getText()))) {
                            JOptionPane.showMessageDialog(CourseEdictPanel.this, "编辑成功", "提示", JOptionPane.INFORMATION_MESSAGE);
                            tabbedPane.remove(CourseEdictPanel.this);
                        } else {
                            JOptionPane.showMessageDialog(CourseEdictPanel.this, "编辑失败", "提示", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                }
            } else {
                JOptionPane.showMessageDialog(CourseEdictPanel.this, "还有课程信息尚未填写", "提示", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        add(edictButton);

        JButton cancelButton = new JButton("取消");
        cancelButton.setBounds(370, 540, 80, 40);
        cancelButton.addActionListener(e -> {
            tabbedPane.remove(CourseEdictPanel.this);
        });
        add(cancelButton);
    }
}
