package com.ustc.studentcourseselection.view.mainmenu.manager.panel;

import com.ustc.studentcourseselection.controller.StudentManagementUtils;
import com.ustc.studentcourseselection.model.BaseUtils;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

/**
 * @author 何昊
 */
public class StudentEdictPanel extends JPanel {
    public StudentEdictPanel(JTabbedPane tabbedPane, Vector<String> student) {
        setLayout(null);

        JLabel setNumber = new JLabel("学号：");
        setNumber.setBounds(100, 40, 300, 30);
        setNumber.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        add(setNumber);

        JTextField setNumberField = new JTextField();
        setNumberField.setBounds(400, 40, 200, 30);
        setNumberField.setText(student.getFirst());
        setNumberField.setEditable(false);
        add(setNumberField);

        JLabel setName = new JLabel("姓名：");
        setName.setBounds(100, 80, 300, 30);
        setName.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        add(setName);

        JTextField setNameField = new JTextField();
        setNameField.setBounds(400, 80, 200, 40);
        setNameField.setText(student.get(1));
        setNameField.setEditable(false);
        add(setNameField);

        JLabel setGender = new JLabel("性别：");
        setGender.setBounds(100, 140, 300, 30);
        setGender.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        add(setGender);

        JTextField setGenderField = new JTextField();
        setGenderField.setBounds(400, 140, 200, 40);
        setGenderField.setText(student.get(2));
        setGenderField.setEditable(false);
        add(setGenderField);

        JLabel setGrade = new JLabel("年级：");
        setGrade.setBounds(100, 200, 300, 30);
        setGrade.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        add(setGrade);

        JTextField setGradeField = new JTextField();
        setGradeField.setBounds(400, 200, 200, 40);
        setGradeField.setText(student.get(4));
        setGenderField.setEditable(false);
        add(setGradeField);

        JLabel setMajor = new JLabel("专业：");
        setMajor.setBounds(100, 260, 300, 30);
        setMajor.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        add(setMajor);


        String[] majorList = new String[]{"计算机科学与技术", "数学", "物理", "化学", "生物"};
        JComboBox<String> selectMajor = new JComboBox<>(majorList);
        selectMajor.setBounds(400, 260, 200, 40);
        selectMajor.setSelectedItem(student.get(3));
        add(selectMajor);

        JLabel setClassName = new JLabel("班级名称：");
        setClassName.setBounds(100, 320, 300, 30);
        setClassName.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        add(setClassName);

        JTextField setClassNameField = new JTextField();
        setClassNameField.setBounds(400, 320, 200, 40);
        setClassNameField.setText(student.get(6));
        add(setClassNameField);

        JLabel setDegree = new JLabel("学历：");
        setDegree.setBounds(100, 380, 300, 30);
        setDegree.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        add(setDegree);

        JTextField setDegreeField = new JTextField();
        setDegreeField.setBounds(400, 380, 200, 40);
        setDegreeField.setText(student.get(5));
        add(setDegreeField);

        JButton addButton = new JButton("编辑");
        addButton.setBounds(270, 540, 80, 40);
        addButton.addActionListener(e -> {
            if (!setNumberField.getText().isEmpty() && !setGradeField.getText().isEmpty() && !setClassNameField.getText().isEmpty()) {
                int choice = JOptionPane.showConfirmDialog(StudentEdictPanel.this, "你确认要进行添加吗", "确认添加", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_NO_OPTION) {
                    if (StudentManagementUtils.studentEdict(setNumberField.getText(), setNameField.getText(), setGenderField.getText(), Integer.parseInt(setGradeField.getText()), setDegreeField.getText(), (String) selectMajor.getSelectedItem(), setClassNameField.getText())) {
                        JOptionPane.showMessageDialog(StudentEdictPanel.this, "添加成功", "提示", JOptionPane.INFORMATION_MESSAGE);
                        tabbedPane.remove(StudentEdictPanel.this);
                    } else {
                        JOptionPane.showMessageDialog(StudentEdictPanel.this, "添加失败", "提示", JOptionPane.INFORMATION_MESSAGE);

                    }
                }
            } else {
                JOptionPane.showMessageDialog(StudentEdictPanel.this, "还有课程休息尚未填写", "提示", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        add(addButton);

        JButton cancelButton = new JButton("取消");
        cancelButton.setBounds(370, 540, 80, 40);
        cancelButton.addActionListener(e -> {
            tabbedPane.remove(StudentEdictPanel.this);
        });
        add(cancelButton);

        JButton passwordRemakeButton = new JButton("密码重置");
        passwordRemakeButton.setBounds(470, 540, 120, 40);
        passwordRemakeButton.addActionListener(e -> {
            int choice = JOptionPane.showConfirmDialog(StudentEdictPanel.this, "你确认要进行重置吗", "确认重置", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_NO_OPTION) {
                if (StudentManagementUtils.passwordRemake(setNumberField.getText())) {
                    JOptionPane.showMessageDialog(StudentEdictPanel.this, "重置成功", "提示", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(StudentEdictPanel.this, "重置失败", "提示", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        add(passwordRemakeButton);
    }
}
