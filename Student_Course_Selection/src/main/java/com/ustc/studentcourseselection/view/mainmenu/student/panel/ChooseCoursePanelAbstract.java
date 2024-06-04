package com.ustc.studentcourseselection.view.mainmenu.student.panel;

import com.ustc.studentcourseselection.controller.StudentCourseUtils;
import com.ustc.studentcourseselection.dao.CourseDao;
import com.ustc.studentcourseselection.model.Student;
import com.ustc.studentcourseselection.view.UIUtil;
import com.ustc.studentcourseselection.view.mainmenu.student.AbstractStudentPanel;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.util.Vector;

/**
 * ѡ�ν��������
 *
 * @author ������
 */
public class ChooseCoursePanelAbstract extends AbstractStudentPanel {
    public static Vector<Vector<String>> courseData = new Vector<>();
    public static JScrollPane scrollPane = new JScrollPane();

    public static JTable table = UIUtil.creatTable();

    public ChooseCoursePanelAbstract(Student student) {
        scrollPane.setBounds(20, 50, 944, 550);
        scrollPane.getViewport().setBackground(Color.WHITE);
        add(scrollPane);

        CourseDao courseDao = new CourseDao();
        courseData = courseDao.queryAll(null, null, null, null);

        setTableData(table, scrollPane, header, courseData);
        table.getColumnModel().getColumn(9).setCellRenderer(new ChooseButton());
        table.getColumnModel().getColumn(9).setCellEditor(new ChooseButtonEditor(table, student));

        refreshBt.addActionListener(e -> {
            refreshButtonClick(student);
            setTableData(table, scrollPane, header, courseData);
            table.getColumnModel().getColumn(9).setCellRenderer(new ChooseButton());
            table.getColumnModel().getColumn(9).setCellEditor(new ChooseButtonEditor(table, student));
        });

        JLabel courseNum = new JLabel("�γ̺�:");
        courseNum.setForeground(Color.black);
        courseNum.setFont(new Font("΢���ź�", Font.PLAIN, 13));
        courseNum.setBounds(110, 13, 95, 25);
        add(courseNum);

        JLabel courseNam = new JLabel("�γ���:");
        courseNam.setForeground(Color.black);
        courseNam.setFont(new Font("΢���ź�", Font.PLAIN, 13));
        courseNam.setBounds(260, 13, 95, 25);
        add(courseNam);

        JLabel courseMaj = new JLabel("����Ժϵ:");
        courseMaj.setForeground(Color.black);
        courseMaj.setFont(new Font("΢���ź�", Font.PLAIN, 13));
        courseMaj.setBounds(410, 13, 95, 25);
        add(courseMaj);

        JLabel courseTea = new JLabel("��ʦ����:");
        courseTea.setForeground(Color.black);
        courseTea.setFont(new Font("΢���ź�", Font.PLAIN, 13));
        courseTea.setBounds(600, 13, 95, 25);
        add(courseTea);


        JTextField courseNumber = new JTextField();
        courseNumber.setFont(new Font("΢���ź�", Font.PLAIN, 13));
        courseNumber.setForeground(Color.black);
        courseNumber.setBounds(160, 13, 90, 25);
        courseNumber.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        courseNumber.setColumns(8);
        add(courseNumber);

        JTextField courseName = new JTextField();
        courseName.setFont(new Font("΢���ź�", Font.PLAIN, 13));
        courseName.setForeground(Color.black);
        courseName.setBounds(310, 13, 90, 25);
        courseName.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        courseName.setColumns(8);
        add(courseName);

        JTextField courseMajor = new JTextField();
        courseMajor.setFont(new Font("΢���ź�", Font.PLAIN, 13));
        courseMajor.setForeground(Color.black);
        courseMajor.setBounds(474, 13, 120, 25);
        courseMajor.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        courseMajor.setColumns(8);
        add(courseMajor);

        JTextField courseTeacherName = new JTextField();
        courseTeacherName.setFont(new Font("΢���ź�", Font.PLAIN, 13));
        courseTeacherName.setForeground(Color.black);
        courseTeacherName.setBounds(664, 13, 90, 25);
        courseTeacherName.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        courseTeacherName.setColumns(8);
        add(courseTeacherName);


        JButton searchBt = new JButton("����");
        searchBt.setFont(new Font("΢���ź�", Font.PLAIN, 14));
        searchBt.setForeground(Color.WHITE);
        searchBt.setBackground(new Color(0, 64, 128));
        searchBt.setCursor(new Cursor(Cursor.HAND_CURSOR));
        searchBt.setFocusPainted(false);
        searchBt.setBounds(883, 10, 80, 34);
        add(searchBt);

        searchBt.addActionListener(e -> {
            courseData = courseDao.queryAll(courseNumber.getText(), courseName.getText(), courseMajor.getText(), courseTeacherName.getText());
            setTableData(table, scrollPane, header, courseData);
            table.getColumnModel().getColumn(9).setCellRenderer(new ChooseButton());
            table.getColumnModel().getColumn(9).setCellEditor(new ChooseButtonEditor(table, student));
        });
    }

    @Override
    protected void refreshButtonClick(Student student) {
        StudentCourseUtils.refreshCourseForChooseCourse(courseData);
    }


    static class ChooseButton implements TableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
                                                       int row, int column) {
            JButton btn = new JButton("ѡ��");
            btn.setPreferredSize(new Dimension(30, 20));
            btn.setBackground(new Color(119, 201, 126));
            btn.setBorder(null);
            btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            btn.setFocusPainted(false);
            return btn;
        }

    }

    static class ChooseButtonEditor extends DefaultCellEditor {
        private final JButton btn;

        public ChooseButtonEditor(JTable table, Student student) {
            super(new JTextField());
            this.setClickCountToStart(1);
            btn = new JButton("ѡ��");
            btn.setPreferredSize(new Dimension(30, 20));
            btn.setBackground(new Color(119, 201, 126));
            btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            btn.setBorder(null);
            btn.setFocusPainted(false);
            btn.addActionListener(e -> {
                int row = table.convertRowIndexToModel(table.getEditingRow());
                String courseNumber = (String) table.getModel().getValueAt(row, 0);
                int res = StudentCourseUtils.chooseCourse(student.getId(), courseNumber);
                switch (res) {
                    case (1) ->
                            JOptionPane.showConfirmDialog(scrollPane, "ѡ��ʧ�ܣ�����������", "��ʾ", JOptionPane.DEFAULT_OPTION);
                    case (2) ->
                            JOptionPane.showConfirmDialog(scrollPane, "ѡ��ʧ�ܣ�ʱ���ͻ��", "��ʾ", JOptionPane.DEFAULT_OPTION);
                    case (3) ->
                            JOptionPane.showConfirmDialog(scrollPane, "ѡ��ʧ�ܣ��������磡", "��ʾ", JOptionPane.DEFAULT_OPTION);
                    default ->
                            JOptionPane.showConfirmDialog(scrollPane, "ѡ�γɹ���", "��ʾ", JOptionPane.DEFAULT_OPTION);
                }
                // ˢ������
                StudentCourseUtils.refreshCourseForChooseCourse(courseData);
                setTableData(table, scrollPane, header, courseData);
                table.getColumnModel().getColumn(9).setCellRenderer(new ChooseButton());
                table.getColumnModel().getColumn(9).setCellEditor(new ChooseButtonEditor(table, student));
            });
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            return btn;
        }
    }
}