package com.ustc.studentcourseselection.view.mainmenu.student.panel;


import com.ustc.studentcourseselection.controller.StudentCourseUtils;
import com.ustc.studentcourseselection.model.Student;
import com.ustc.studentcourseselection.view.UIUtil;
import com.ustc.studentcourseselection.view.mainmenu.student.AbstractStudentPanel;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.util.Vector;

/**
 * ÒÑÑ¡¿Î³Ì
 *
 * @author ÃÏè÷êÏ
 */
public class CourseChosenPanelAbstract extends AbstractStudentPanel {
    public static Vector<Vector<String>> courseData = new Vector<>();
    public static JScrollPane scrollPane = new JScrollPane();

    public static JTable table = UIUtil.creatTable();

    public CourseChosenPanelAbstract(Student student) {
        scrollPane.setBounds(20, 50, 944, 550);
        scrollPane.getViewport().setBackground(Color.WHITE);
        add(scrollPane);

        StudentCourseUtils.refreshCourseForCourseChosen(courseData, student);

        setTableData(table, scrollPane, header, courseData);
        table.getColumnModel().getColumn(9).setCellRenderer(new QuitButton());
        table.getColumnModel().getColumn(9).setCellEditor(new QuitButtonEditor(table, student));

        refreshBt.addActionListener(e -> {
            refreshButtonClick(student);
            setTableData(table, scrollPane, header, courseData);
            table.getColumnModel().getColumn(9).setCellRenderer(new QuitButton());
            table.getColumnModel().getColumn(9).setCellEditor(new QuitButtonEditor(table, student));
        });

    }

    @Override
    protected void refreshButtonClick(Student student) {
        StudentCourseUtils.refreshCourseForCourseChosen(courseData, student);
    }


    static class QuitButton implements TableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
                                                       int row, int column) {
            JButton btn = new JButton("ÍË¿Î");
            btn.setPreferredSize(new Dimension(30, 20));
            btn.setBackground(new Color(227, 78, 99));
            btn.setBorder(null);
            btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            btn.setFocusPainted(false);
            return btn;
        }
    }


    static class QuitButtonEditor extends DefaultCellEditor {
        private final JButton btn;

        public QuitButtonEditor(JTable table, Student student) {
            super(new JTextField());
            this.setClickCountToStart(1);
            btn = new JButton("ÍË¿Î");
            btn.setPreferredSize(new Dimension(30, 20));
            btn.setBorder(null);
            btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            btn.setBackground(new Color(227, 78, 99));
            btn.setFocusPainted(false);
            btn.addActionListener(e -> {
                int row = table.convertRowIndexToModel(table.getEditingRow());
                String courseNumber = (String) table.getModel().getValueAt(row, 0);
                if (StudentCourseUtils.quitCourse(student.getId(), courseNumber)) {
                    JOptionPane.showConfirmDialog(scrollPane, "ÍË¿Î³É¹¦£¡", "ÌáÊ¾", JOptionPane.DEFAULT_OPTION);
                } else {
                    JOptionPane.showConfirmDialog(scrollPane, "ÍË¿ÎÊ§°Ü£¬Çë¼ì²éÍøÂç£¡", "ÌáÊ¾", JOptionPane.DEFAULT_OPTION);
                }
                courseData.remove(row);
                setTableData(table, scrollPane, header, courseData);
                table.getColumnModel().getColumn(9).setCellRenderer(new QuitButton());
                table.getColumnModel().getColumn(9).setCellEditor(new QuitButtonEditor(table, student));
            });
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            return btn;
        }
    }

}
