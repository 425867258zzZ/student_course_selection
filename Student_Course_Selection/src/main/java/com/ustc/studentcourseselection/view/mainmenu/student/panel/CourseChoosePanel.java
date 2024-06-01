package com.ustc.studentcourseselection.view.mainmenu.student.panel;

import com.ustc.studentcourseselection.controller.StudentCourseUtils;
import com.ustc.studentcourseselection.dao.CourseDao;
import com.ustc.studentcourseselection.model.Student;
import com.ustc.studentcourseselection.view.Panel;
import com.ustc.studentcourseselection.view.UIUtil;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.Vector;

/**
 * @author ������
 */
public class CourseChoosePanel extends Panel {

    public CourseChoosePanel(Student student) {
        super();

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(20, 50, 944, 550);
        scrollPane.getViewport().setBackground(Color.WHITE);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        add(scrollPane);

        // ��ͷ����
        Vector<String> header = new Vector<>();
        header.add("�γ̺�");
        header.add("�γ���");
        header.add("����ʱ��");
        header.add("����Ժϵ");
        header.add("�Ͽεص�");
        header.add("ѧ��");
        header.add("��ѡ����");
        header.add("�γ�����");
        header.add("����");

        // ��ȡ���пγ���Ϣ
        CourseDao courseDao = new CourseDao();
        Vector<Vector<String>> allCourses = courseDao.queryAll();

        JTable table = UIUtil.creatTable(header, allCourses);
        scrollPane.setViewportView(table);


        // �����γ̺�һ�п�
        TableColumn courseNumberCol = table.getColumnModel().getColumn(0);
        courseNumberCol.setPreferredWidth(25);

        // ���������п�
        int startCol = 4;
        int endCol = 8;
        for (int i = startCol; i <= endCol; i++) {
            TableColumn col = table.getColumnModel().getColumn(i);
            col.setPreferredWidth(i);
        }


        table.getColumnModel().getColumn(8).setCellRenderer(new ChooseButton());
        table.getColumnModel().getColumn(8).setCellEditor(new ChooseButtonEditor(table, student));
    }


    static class ChooseButton implements TableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
                                                       int row, int column) {
            JButton btn = new JButton("ѡ��");
            btn.setPreferredSize(new Dimension(30, 20));
            btn.setBorder(null);
            btn.setFocusPainted(false);
            return btn;
        }

    }

    static class QuitButton implements TableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
                                                       int row, int column) {
            JButton btn = new JButton("�˿�");
            btn.setPreferredSize(new Dimension(30, 20));
            btn.setBorder(null);
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
            btn.setBorder(null);
            btn.setFocusPainted(false);
            btn.addActionListener(e -> {
                int row = table.convertRowIndexToModel(table.getEditingRow());
                String courseNumber = (String) table.getModel().getValueAt(row, 0);
                int res = StudentCourseUtils.chooseCourse(student.getId(), courseNumber);
                switch (res) {
                    case (1) ->
                            JOptionPane.showConfirmDialog(null, "ѡ��ʧ�ܣ�������������", "��ʾ", JOptionPane.DEFAULT_OPTION);
                    case (2) ->
                            JOptionPane.showConfirmDialog(null, "ѡ��ʧ�ܣ�ʱ���ͻ��", "��ʾ", JOptionPane.DEFAULT_OPTION);
                    case (3) ->
                            JOptionPane.showConfirmDialog(null, "ѡ��ʧ�ܣ��������磡", "��ʾ", JOptionPane.DEFAULT_OPTION);
                    default -> JOptionPane.showConfirmDialog(null, "ѡ�γɹ���", "��ʾ", JOptionPane.DEFAULT_OPTION);
                }
            });
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            return btn;
        }
    }

    static class QuitButtonEditor extends DefaultCellEditor {
        private final JButton btn;

        public QuitButtonEditor(JTable table) {
            super(new JTextField());
            this.setClickCountToStart(1);
            btn = new JButton("ѡ��");
            btn.setPreferredSize(new Dimension(30, 20));
            btn.setBorder(null);
            btn.setFocusPainted(false);
            btn.addActionListener(e -> {
                int row = table.convertRowIndexToModel(table.getEditingRow()); // Convert view row index to model row index
                // Now you have the model index of the row, you can get data from the table model
                Object courseNumber = table.getModel().getValueAt(row, 0); // Assuming the course number is in the first column
                Object courseName = table.getModel().getValueAt(row, 1); // Assuming the course name is in the second column
                // Now you have the data, you can do whatever you want with it
                // For example, you can pass it to another method to process the selection
                // processSelection(courseNumber, courseName);
            });
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            return btn;
        }
    }
}