package com.ustc.studentcourseselection.view.mainmenu.manager.panel;

import com.ustc.studentcourseselection.controller.StudentManagementUtils;
import com.ustc.studentcourseselection.model.BaseUtils;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

/**
 * @author ���
 */
public class StudentEdictPanel extends JPanel {
    public StudentEdictPanel(JTabbedPane tabbedPane, Vector<String> student) {
        setLayout(null);

        JLabel setNumber = new JLabel("ѧ�ţ�");
        setNumber.setBounds(100, 40, 300, 30);
        setNumber.setFont(new Font("΢���ź�", Font.PLAIN, 15));
        add(setNumber);

        JTextField setNumberField = new JTextField();
        setNumberField.setBounds(400, 40, 200, 30);
        setNumberField.setText(student.getFirst());
        setNumberField.setEditable(false);
        add(setNumberField);

        JLabel setName = new JLabel("������");
        setName.setBounds(100, 80, 300, 30);
        setName.setFont(new Font("΢���ź�", Font.PLAIN, 15));
        add(setName);

        JTextField setNameField = new JTextField();
        setNameField.setBounds(400, 80, 200, 40);
        setNameField.setText(student.get(1));
        setNameField.setEditable(false);
        add(setNameField);

        JLabel setGender = new JLabel("�Ա�");
        setGender.setBounds(100, 140, 300, 30);
        setGender.setFont(new Font("΢���ź�", Font.PLAIN, 15));
        add(setGender);

        JTextField setGenderField = new JTextField();
        setGenderField.setBounds(400, 140, 200, 40);
        setGenderField.setText(student.get(2));
        setGenderField.setEditable(false);
        add(setGenderField);

        JLabel setGrade = new JLabel("�꼶��");
        setGrade.setBounds(100, 200, 300, 30);
        setGrade.setFont(new Font("΢���ź�", Font.PLAIN, 15));
        add(setGrade);

        JTextField setGradeField = new JTextField();
        setGradeField.setBounds(400, 200, 200, 40);
        setGradeField.setText(student.get(4));
        setGenderField.setEditable(false);
        add(setGradeField);

        JLabel setMajor = new JLabel("רҵ��");
        setMajor.setBounds(100, 260, 300, 30);
        setMajor.setFont(new Font("΢���ź�", Font.PLAIN, 15));
        add(setMajor);


        String[] majorList = new String[]{"�������ѧ�뼼��", "��ѧ", "����", "��ѧ", "����"};
        JComboBox<String> selectMajor = new JComboBox<>(majorList);
        selectMajor.setBounds(400, 260, 200, 40);
        selectMajor.setSelectedItem(student.get(3));
        add(selectMajor);

        JLabel setClassName = new JLabel("�༶���ƣ�");
        setClassName.setBounds(100, 320, 300, 30);
        setClassName.setFont(new Font("΢���ź�", Font.PLAIN, 15));
        add(setClassName);

        JTextField setClassNameField = new JTextField();
        setClassNameField.setBounds(400, 320, 200, 40);
        setClassNameField.setText(student.get(6));
        add(setClassNameField);

        JLabel setDegree = new JLabel("ѧ����");
        setDegree.setBounds(100, 380, 300, 30);
        setDegree.setFont(new Font("΢���ź�", Font.PLAIN, 15));
        add(setDegree);

        JTextField setDegreeField = new JTextField();
        setDegreeField.setBounds(400, 380, 200, 40);
        setDegreeField.setText(student.get(5));
        add(setDegreeField);

        JButton addButton = new JButton("�༭");
        addButton.setBounds(270, 540, 80, 40);
        addButton.addActionListener(e -> {
            if (!setNumberField.getText().isEmpty() && !setGradeField.getText().isEmpty() && !setClassNameField.getText().isEmpty()) {
                int choice = JOptionPane.showConfirmDialog(StudentEdictPanel.this, "��ȷ��Ҫ���������", "ȷ�����", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_NO_OPTION) {
                    if (StudentManagementUtils.studentEdict(setNumberField.getText(), setNameField.getText(), setGenderField.getText(), Integer.parseInt(setGradeField.getText()), setDegreeField.getText(), (String) selectMajor.getSelectedItem(), setClassNameField.getText())) {
                        JOptionPane.showMessageDialog(StudentEdictPanel.this, "��ӳɹ�", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
                        tabbedPane.remove(StudentEdictPanel.this);
                    } else {
                        JOptionPane.showMessageDialog(StudentEdictPanel.this, "���ʧ��", "��ʾ", JOptionPane.INFORMATION_MESSAGE);

                    }
                }
            } else {
                JOptionPane.showMessageDialog(StudentEdictPanel.this, "���пγ���Ϣ��δ��д", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        add(addButton);

        JButton cancelButton = new JButton("ȡ��");
        cancelButton.setBounds(370, 540, 80, 40);
        cancelButton.addActionListener(e -> {
            tabbedPane.remove(StudentEdictPanel.this);
        });
        add(cancelButton);

        JButton passwordRemakeButton = new JButton("��������");
        passwordRemakeButton.setBounds(470, 540, 120, 40);
        passwordRemakeButton.addActionListener(e -> {
            int choice = JOptionPane.showConfirmDialog(StudentEdictPanel.this, "��ȷ��Ҫ����������", "ȷ������", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_NO_OPTION) {
                if (StudentManagementUtils.passwordRemake(setNumberField.getText())) {
                    JOptionPane.showMessageDialog(StudentEdictPanel.this, "���óɹ�", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(StudentEdictPanel.this, "����ʧ��", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        add(passwordRemakeButton);
    }
}
