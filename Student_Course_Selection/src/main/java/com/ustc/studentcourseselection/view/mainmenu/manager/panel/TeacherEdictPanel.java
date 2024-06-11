package com.ustc.studentcourseselection.view.mainmenu.manager.panel;

import com.ustc.studentcourseselection.controller.TeacherManagementUtils;
import com.ustc.studentcourseselection.model.BaseUtils;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

/**
 * @author wangpanyang5
 */
public class TeacherEdictPanel extends JPanel {
    public TeacherEdictPanel(JTabbedPane tabbedPane, Vector<String> teacher){
        setLayout(null);

        JLabel setTeacherName=new JLabel("姓名:");
        setTeacherName.setBounds(100,40,300,30);
        setTeacherName.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        add(setTeacherName);

        JTextField setTeacherNameField=new JTextField();
        setTeacherNameField.setBounds(400,40,200,30);
        setTeacherNameField.setText(teacher.getFirst());
        setTeacherNameField.setEditable(false);
        add(setTeacherNameField);

        JLabel setTeacherGender=new JLabel("性别：");
        setTeacherGender.setBounds(100,140,300,30);
        setTeacherGender.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        add(setTeacherGender);

        JTextField setTeacherGenderField=new JTextField();
        setTeacherGenderField.setBounds(400,140,200,40);
        setTeacherGenderField.setText(teacher.get(2));
        setTeacherGenderField.setEditable(false);
        add(setTeacherGenderField);

        JLabel setTeacherNumber=new JLabel("工号：");
        setTeacherNumber.setBounds(100,80,300,30);
        setTeacherNumber.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        add(setTeacherNumber);

        JTextField setTeacherNumberField=new JTextField();
        setTeacherNumberField.setBounds(400,80,200,40);
        setTeacherNumberField.setText(teacher.get(1));
        setTeacherNumberField.setEditable(false);
        add(setTeacherNumberField);

        JLabel setTeacherDepartment=new JLabel("部门：");
        setTeacherDepartment.setBounds(100,200,300,30);
        setTeacherDepartment.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        add(setTeacherDepartment);

        JTextField setTeacherDepartmentField=new JTextField();
        setTeacherDepartmentField.setBounds(400,200,200,40);
        setTeacherDepartmentField.setText(teacher.get(3));
        add(setTeacherDepartmentField);

        JLabel setPassword=new JLabel("重置密码：");
        setPassword.setBounds(100,260,300,30);
        setPassword.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        add(setPassword);

        JCheckBox resetPasswordCheckbox = new JCheckBox("是");
        resetPasswordCheckbox.setBounds(400, 260, 300, 30);
        add(resetPasswordCheckbox);

        JButton edictButton=new JButton("编辑");
        edictButton.setBounds(270,540,80,40);
        edictButton.addActionListener(e->{
            boolean shouldResetPassword = resetPasswordCheckbox.isSelected();
            if (!setTeacherNameField.getText().isEmpty() && !setTeacherGenderField.getText().isEmpty() && !setTeacherNumberField.getText().isEmpty() && !setTeacherDepartmentField.getText().isEmpty() ) {
                int choice = JOptionPane.showConfirmDialog(TeacherEdictPanel.this,"你确认要进行编辑吗","确认编辑",JOptionPane.YES_NO_OPTION);
                if(shouldResetPassword) {
                    TeacherManagementUtils.teacherEdict(setTeacherNameField.getText(), setTeacherNumberField.getText(), setTeacherGenderField.getText(), setTeacherDepartmentField.getText(), "123456");
                }
                else {
                    TeacherManagementUtils.teacherEdict(setTeacherNameField.getText(), setTeacherNumberField.getText(), setTeacherGenderField.getText(), setTeacherDepartmentField.getText(),teacher.get(4));
                }
                JOptionPane.showMessageDialog(TeacherEdictPanel.this, "编辑成功", "提示", JOptionPane.INFORMATION_MESSAGE);
                tabbedPane.remove(TeacherEdictPanel.this);
            }else{
                JOptionPane.showMessageDialog(TeacherEdictPanel.this,"还有信息尚未填写","提示",JOptionPane.INFORMATION_MESSAGE);
            }
        });
        add(edictButton);

        JButton cancelButton=new JButton("取消");
        cancelButton.setBounds(370,540,80,40);
        cancelButton.addActionListener(e->{
            tabbedPane.remove(TeacherEdictPanel.this);});
        add(cancelButton);
    }
}
