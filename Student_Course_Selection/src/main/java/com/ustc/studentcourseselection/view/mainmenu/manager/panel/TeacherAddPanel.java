package com.ustc.studentcourseselection.view.mainmenu.manager.panel;

import com.ustc.studentcourseselection.controller.TeacherManagementUtils;
import com.ustc.studentcourseselection.model.BaseUtils;
import com.ustc.studentcourseselection.view.mainmenu.manager.ManagerMenu;

import javax.swing.*;
import java.awt.*;

/**
 * The type Course add panel.
 *
 * @author wangpanyang5
 */
public class TeacherAddPanel extends JPanel {
    public TeacherAddPanel(JTabbedPane tabbedPane) {
        setLayout(null);

        JLabel setTeacherName = new JLabel("请输入添加老师的姓名");
        setTeacherName.setBounds(100, 40, 300, 30);
        setTeacherName.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        add(setTeacherName);

        JTextField setTeacherNameField = new JTextField();
        setTeacherNameField.setBounds(400, 40, 200, 30);
        add(setTeacherNameField);

        JLabel setTeacherGender = new JLabel("请输入添加老师的性别：");
        setTeacherGender.setBounds(100, 140, 300, 30);
        setTeacherGender.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        add(setTeacherGender);

        JTextField setTeacherGenderField = new JTextField();
        setTeacherGenderField.setBounds(400, 140, 200, 40);
        add(setTeacherGenderField);

        JLabel setTeacherNumber = new JLabel("请输入添加老师的工号：");
        setTeacherNumber.setBounds(100, 80, 300, 30);
        setTeacherNumber.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        add(setTeacherNumber);

        JTextField setTeacherNumberField = new JTextField();
        setTeacherNumberField.setBounds(400, 80, 200, 40);
        add(setTeacherNumberField);

        JLabel setTeacherDepartment = new JLabel("请输入添加老师的部门：");
        setTeacherDepartment.setBounds(100, 200, 300, 30);
        setTeacherDepartment.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        add(setTeacherDepartment);

        JTextField setTeacherDepartmentField = new JTextField();
        setTeacherDepartmentField.setBounds(400, 200, 200, 40);
        add(setTeacherDepartmentField);


        JButton addButton = new JButton("添加");
        addButton.setBounds(270, 540, 80, 40);
        addButton.addActionListener(e -> {
            if (!setTeacherNameField.getText().isEmpty() && !setTeacherGenderField.getText().isEmpty() && !setTeacherNumberField.getText().isEmpty() && !setTeacherDepartmentField.getText().isEmpty()) {
                int choice = JOptionPane.showConfirmDialog(TeacherAddPanel.this, "你确认要进行添加吗", "确认添加", JOptionPane.YES_NO_OPTION);
                boolean res = TeacherManagementUtils.teacherAdd(setTeacherNameField.getText(), setTeacherNumberField.getText(), setTeacherGenderField.getText(), setTeacherDepartmentField.getText(), "123456", BaseUtils.getTime(), BaseUtils.getTime());
                if (res) {
                    JOptionPane.showMessageDialog(TeacherAddPanel.this, "添加成功", "提示", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(TeacherAddPanel.this, "添加失败", "提示", JOptionPane.INFORMATION_MESSAGE);
                }

                tabbedPane.remove(TeacherAddPanel.this);
            } else {
                JOptionPane.showMessageDialog(TeacherAddPanel.this, "还有信息尚未填写", "提示", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        add(addButton);

        JButton cancelButton = new JButton("取消");
        cancelButton.setBounds(370, 540, 80, 40);
        cancelButton.addActionListener(e -> {
            tabbedPane.remove(TeacherAddPanel.this);
        });
        add(cancelButton);
    }
}
