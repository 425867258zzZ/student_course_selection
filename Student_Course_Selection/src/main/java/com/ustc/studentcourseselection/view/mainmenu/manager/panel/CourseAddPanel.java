package com.ustc.studentcourseselection.view.mainmenu.manager.panel;

import com.ustc.studentcourseselection.controller.CourseManagementUtils;
import com.ustc.studentcourseselection.model.BaseUtils;
import com.ustc.studentcourseselection.view.mainmenu.manager.ManagerMenu;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

/**
 * The type Course add panel.
 *
 * @author 13901
 */
public class CourseAddPanel extends JPanel {
    /**
     * Instantiates a new Course add panel.
     *
     * @param tabbedPane the tabbed pane
     */
    public CourseAddPanel(JTabbedPane tabbedPane, Vector<Vector<String>> courseData) {
        setLayout(null);

        JLabel setNumber = new JLabel("���������ӿγ̵ı��");
        setNumber.setBounds(100, 40, 300, 30);
        setNumber.setFont(new Font("΢���ź�", Font.PLAIN, 23));
        add(setNumber);

        JTextField setNumberField = new JTextField();
        setNumberField.setBounds(400, 40, 200, 30);
        add(setNumberField);

        JLabel setTeacherName = new JLabel("��������Ҫ���ӿγ̵���ʦ���֣�");
        setTeacherName.setBounds(100, 80, 300, 30);
        setTeacherName.setFont(new Font("΢���ź�", Font.PLAIN, 15));
        add(setTeacherName);

        JTextField setTeacherNameField = new JTextField();
        setTeacherNameField.setBounds(400, 80, 200, 40);
        add(setTeacherNameField);

        JLabel setCourseTime = new JLabel("��������Ҫ���ӿγ̵��Ͽ�ʱ�䣺");
        setCourseTime.setBounds(100, 140, 300, 30);
        setCourseTime.setFont(new Font("΢���ź�", Font.PLAIN, 15));
        add(setCourseTime);

        JTextField setCourseTimeField = new JTextField();
        setCourseTimeField.setBounds(400, 140, 200, 40);
        add(setCourseTimeField);

        JLabel setCourseName = new JLabel("��������Ҫ���ӿγ̵����ƣ�");
        setCourseName.setBounds(100, 200, 300, 30);
        setCourseName.setFont(new Font("΢���ź�", Font.PLAIN, 15));
        add(setCourseName);

        JTextField setCourseNameField = new JTextField();
        setCourseNameField.setBounds(400, 200, 200, 40);
        add(setCourseNameField);

        JLabel setMajor = new JLabel("��ѡ����Ҫ���ӿγ̵�����רҵ��");
        setMajor.setBounds(100, 260, 300, 30);
        setMajor.setFont(new Font("΢���ź�", Font.PLAIN, 15));
        add(setMajor);

        String[] majorList = new String[]{"�������ѧ�뼼��", "��ѧ", "����", "��ѧ", "����"};
        JComboBox<String> selectMajor = new JComboBox<>(majorList);
        selectMajor.setBounds(400, 260, 200, 40);
        add(selectMajor);

        JLabel setLocation = new JLabel("��������Ҫ���ӿγ̵��Ͽεص㣺");
        setLocation.setBounds(100, 320, 300, 30);
        setLocation.setFont(new Font("΢���ź�", Font.PLAIN, 15));
        add(setLocation);

        JTextField setLocationField = new JTextField();
        setLocationField.setBounds(400, 320, 200, 40);
        add(setLocationField);

        JLabel setScore = new JLabel("��������Ҫ���ӿγ̵Ŀγ�ѧ�֣�");
        setScore.setBounds(100, 380, 300, 30);
        setScore.setFont(new Font("΢���ź�", Font.PLAIN, 15));
        add(setScore);

        SpinnerNumberModel model = new SpinnerNumberModel(0, 0, 20, 1);
        JSpinner setScoreField = new JSpinner(model);
        setScoreField.setBounds(400, 380, 50, 40);
        add(setScoreField);

        JLabel setCapacity = new JLabel("��������Ҫ���ӿγ̵Ŀ���������");
        setCapacity.setBounds(100, 440, 300, 30);
        setCapacity.setFont(new Font("΢���ź�", Font.PLAIN, 15));
        add(setCapacity);

        JTextField setCapacityField = new JTextField();
        setCapacityField.setBounds(400, 440, 80, 40);
        add(setCapacityField);

        JButton addButton = new JButton("���");
        addButton.setBounds(270, 540, 80, 40);
        addButton.addActionListener(e -> {
            if (!setNumberField.getText().isEmpty() && !setCourseNameField.getText().isEmpty() && !setCourseTimeField.getText().isEmpty() && !setLocationField.getText().isEmpty() && !setCapacityField.getText().isEmpty() && !setTeacherNameField.getText().isEmpty()) {
                int choice = JOptionPane.showConfirmDialog(CourseAddPanel.this, "��ȷ��Ҫ���������", "ȷ�����", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_NO_OPTION) {
                    if (CourseManagementUtils.check(courseData, setTeacherNameField.getText(), setCourseTimeField.getText(), setLocationField.getText(), setNumberField.getText()) == 1) {
                        JOptionPane.showMessageDialog(CourseAddPanel.this, "�ÿγ��ڸ�ʱ��ε��Ͽεص��������γ̳�ͻ", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
                    } else if (CourseManagementUtils.check(courseData, setTeacherNameField.getText(), setCourseTimeField.getText(), setLocationField.getText(), setNumberField.getText()) == 2) {
                        JOptionPane.showMessageDialog(CourseAddPanel.this, "��ʱ��ε���ʦ�������γ�", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        if (CourseManagementUtils.courseAdd(setNumberField.getText(), setCourseNameField.getText(), setCourseTimeField.getText(), (String) selectMajor.getSelectedItem(), setLocationField.getText(), setTeacherNameField.getText(), (int) setScoreField.getValue(), Integer.parseInt(setCapacityField.getText()), BaseUtils.getTime(), BaseUtils.getTime())) {
                            JOptionPane.showMessageDialog(CourseAddPanel.this, "��ӳɹ�", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
                            tabbedPane.remove(CourseAddPanel.this);
                        } else {
                            JOptionPane.showMessageDialog(CourseAddPanel.this, "���ʧ��", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                }
            } else {
                JOptionPane.showMessageDialog(CourseAddPanel.this, "���пγ���Ϣ��δ��д", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        add(addButton);

        JButton cancelButton = new JButton("ȡ��");
        cancelButton.setBounds(370, 540, 80, 40);
        cancelButton.addActionListener(e -> {
            tabbedPane.remove(CourseAddPanel.this);
        });
        add(cancelButton);
    }
}
