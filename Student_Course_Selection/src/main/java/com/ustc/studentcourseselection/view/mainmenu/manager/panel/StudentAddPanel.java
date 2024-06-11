package com.ustc.studentcourseselection.view.mainmenu.manager.panel;

import com.ustc.studentcourseselection.controller.StudentManagementUtils;
import com.ustc.studentcourseselection.model.BaseUtils;


import javax.swing.*;
import java.awt.*;

/**
 * @author ���
 */
public class StudentAddPanel extends JPanel {
    public StudentAddPanel(JTabbedPane tabbedPane) {
        setLayout(null);

        JLabel setNumber = new JLabel("����������ѧ����ѧ��");
        setNumber.setBounds(100, 40, 300, 30);
        setNumber.setFont(new Font("΢���ź�", Font.PLAIN, 23));
        add(setNumber);

        JTextField setNumberField = new JTextField();
        setNumberField.setBounds(400, 40, 200, 30);
        add(setNumberField);

        JLabel setName = new JLabel("��������Ҫ����ѧ����������");
        setName.setBounds(100, 80, 300, 30);
        setName.setFont(new Font("΢���ź�", Font.PLAIN, 15));
        add(setName);

        JTextField setNameField = new JTextField();
        setNameField.setBounds(400, 80, 200, 40);
        add(setNameField);

        JLabel setGender = new JLabel("��ѡ����Ҫ����ѧ�����Ա�");
        setGender.setBounds(100, 140, 300, 30);
        setGender.setFont(new Font("΢���ź�", Font.PLAIN, 15));
        add(setGender);

        String[] genderList = new String[]{"��", "Ů"};
        JComboBox<String> selectGender = new JComboBox<>(genderList);
        selectGender.setBounds(400, 140, 200, 40);
        add(selectGender);

        JLabel setGrade = new JLabel("��������Ҫ����ѧ�����꼶��");
        setGrade.setBounds(100, 200, 300, 30);
        setGrade.setFont(new Font("΢���ź�", Font.PLAIN, 15));
        add(setGrade);

        JTextField setGradeField = new JTextField();
        setGradeField.setBounds(400, 200, 200, 40);
        add(setGradeField);


        JLabel setMajor = new JLabel("��ѡ����Ҫ����ѧ����רҵ��");
        setMajor.setBounds(100, 260, 300, 30);
        setMajor.setFont(new Font("΢���ź�", Font.PLAIN, 15));
        add(setMajor);

        String[] majorList = new String[]{"�������ѧ�뼼��", "��ѧ", "����", "��ѧ", "����"};
        JComboBox<String> selectMajor = new JComboBox<>(majorList);
        selectMajor.setBounds(400, 260, 200, 40);
        add(selectMajor);

        JLabel setClassName = new JLabel("��������Ҫ����ѧ���İ༶���ƣ�");
        setClassName.setBounds(100, 320, 300, 30);
        setClassName.setFont(new Font("΢���ź�", Font.PLAIN, 15));
        add(setClassName);

        JTextField setClassNameField = new JTextField();
        setClassNameField.setBounds(400, 320, 300, 30);
        add(setClassNameField);

        JLabel setDegree = new JLabel("��������Ҫ����ѧ����ѧ����");
        setDegree.setBounds(100, 380, 300, 30);
        setDegree.setFont(new Font("΢���ź�", Font.PLAIN, 15));
        add(setDegree);

        JTextField setDegreeField = new JTextField();
        setDegreeField.setBounds(400, 380, 200, 40);
        add(setDegreeField);

        JButton addButton = new JButton("���");
        addButton.setBounds(270, 540, 80, 40);
        addButton.addActionListener(e -> {
            if (!setNumberField.getText().isEmpty() && !setGradeField.getText().isEmpty() && !setClassNameField.getText().isEmpty()) {
                int choice = JOptionPane.showConfirmDialog(StudentAddPanel.this, "��ȷ��Ҫ���������", "ȷ�����", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_NO_OPTION) {
                    if (StudentManagementUtils.studentAdd(setNumberField.getText(), setNameField.getText(), (String) selectGender.getSelectedItem(), Integer.parseInt(setGradeField.getText()), setDegreeField.getText(), (String) selectMajor.getSelectedItem(), setClassNameField.getText(), BaseUtils.getTime(), BaseUtils.getTime())) {
                        JOptionPane.showMessageDialog(StudentAddPanel.this, "��ӳɹ�", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
                        tabbedPane.remove(StudentAddPanel.this);
                    } else {
                        JOptionPane.showMessageDialog(StudentAddPanel.this, "���ʧ��", "��ʾ", JOptionPane.INFORMATION_MESSAGE);

                    }
                }
            } else {
                JOptionPane.showMessageDialog(StudentAddPanel.this, "���пγ���Ϣ��δ��д", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        add(addButton);

        JButton cancelButton = new JButton("ȡ��");
        cancelButton.setBounds(370, 540, 80, 40);
        cancelButton.addActionListener(e -> {
            tabbedPane.remove(StudentAddPanel.this);
        });
        add(cancelButton);
    }
}
