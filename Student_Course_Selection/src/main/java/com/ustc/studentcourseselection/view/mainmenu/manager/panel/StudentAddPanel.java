package com.ustc.studentcourseselection.view.mainmenu.manager.panel;

import com.ustc.studentcourseselection.controller.StudentManagementUtils;
import com.ustc.studentcourseselection.model.BaseUtils;


import javax.swing.*;
import java.awt.*;

/**
 * @author 何昊
 */
public class StudentAddPanel extends JPanel {
    public StudentAddPanel(JTabbedPane tabbedPane) {
        setLayout(null);

        JLabel setNumber = new JLabel("请输入增加学生的学号");
        setNumber.setBounds(100, 40, 300, 30);
        setNumber.setFont(new Font("微软雅黑", Font.PLAIN, 23));
        add(setNumber);

        JTextField setNumberField = new JTextField();
        setNumberField.setBounds(400, 40, 200, 30);
        add(setNumberField);

        JLabel setName = new JLabel("请输入你要增加学生的姓名：");
        setName.setBounds(100, 80, 300, 30);
        setName.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        add(setName);

        JTextField setNameField = new JTextField();
        setNameField.setBounds(400, 80, 200, 40);
        add(setNameField);

        JLabel setGender = new JLabel("请选择你要增加学生的性别：");
        setGender.setBounds(100, 140, 300, 30);
        setGender.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        add(setGender);

        String[] genderList = new String[]{"男", "女"};
        JComboBox<String> selectGender = new JComboBox<>(genderList);
        selectGender.setBounds(400, 140, 200, 40);
        add(selectGender);

        JLabel setGrade = new JLabel("请输入你要增加学生的年级：");
        setGrade.setBounds(100, 200, 300, 30);
        setGrade.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        add(setGrade);

        JTextField setGradeField = new JTextField();
        setGradeField.setBounds(400, 200, 200, 40);
        add(setGradeField);


        JLabel setMajor = new JLabel("请选择你要增加学生的专业：");
        setMajor.setBounds(100, 260, 300, 30);
        setMajor.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        add(setMajor);

        String[] majorList = new String[]{"计算机科学与技术", "数学", "物理", "化学", "生物"};
        JComboBox<String> selectMajor = new JComboBox<>(majorList);
        selectMajor.setBounds(400, 260, 200, 40);
        add(selectMajor);

        JLabel setClassName = new JLabel("请输入你要增加学生的班级名称：");
        setClassName.setBounds(100, 320, 300, 30);
        setClassName.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        add(setClassName);

        JTextField setClassNameField = new JTextField();
        setClassNameField.setBounds(400, 320, 300, 30);
        add(setClassNameField);

        JLabel setDegree = new JLabel("请输入你要增加学生的学历：");
        setDegree.setBounds(100, 380, 300, 30);
        setDegree.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        add(setDegree);

        JTextField setDegreeField = new JTextField();
        setDegreeField.setBounds(400, 380, 200, 40);
        add(setDegreeField);

        JButton addButton = new JButton("添加");
        addButton.setBounds(270, 540, 80, 40);
        addButton.addActionListener(e -> {
            if (!setNumberField.getText().isEmpty() && !setGradeField.getText().isEmpty() && !setClassNameField.getText().isEmpty()) {
                int choice = JOptionPane.showConfirmDialog(StudentAddPanel.this, "你确认要进行添加吗", "确认添加", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_NO_OPTION) {
                    if (StudentManagementUtils.studentAdd(setNumberField.getText(), setNameField.getText(), (String) selectGender.getSelectedItem(), Integer.parseInt(setGradeField.getText()), setDegreeField.getText(), (String) selectMajor.getSelectedItem(), setClassNameField.getText(), BaseUtils.getTime(), BaseUtils.getTime())) {
                        JOptionPane.showMessageDialog(StudentAddPanel.this, "添加成功", "提示", JOptionPane.INFORMATION_MESSAGE);
                        tabbedPane.remove(StudentAddPanel.this);
                    } else {
                        JOptionPane.showMessageDialog(StudentAddPanel.this, "添加失败", "提示", JOptionPane.INFORMATION_MESSAGE);

                    }
                }
            } else {
                JOptionPane.showMessageDialog(StudentAddPanel.this, "还有课程休息尚未填写", "提示", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        add(addButton);

        JButton cancelButton = new JButton("取消");
        cancelButton.setBounds(370, 540, 80, 40);
        cancelButton.addActionListener(e -> {
            tabbedPane.remove(StudentAddPanel.this);
        });
        add(cancelButton);
    }
}
