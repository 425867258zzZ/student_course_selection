package com.ustc.studentcourseselection.view.mainmenu.teacher.panel;

import com.ustc.studentcourseselection.controller.TeacherCourseUtils;
import com.ustc.studentcourseselection.dao.CourseDao;
import com.ustc.studentcourseselection.dao.TeacherCourseDao;
import com.ustc.studentcourseselection.model.Teacher;
import com.ustc.studentcourseselection.view.UIUtil;
import com.ustc.studentcourseselection.view.mainmenu.teacher.AbstractTeacherPanel;
import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.util.Arrays;
import java.util.Vector;


/**
 * @author 潘义良
 */


public class ClassInfoPanelAbstract extends AbstractTeacherPanel {
    public static Vector<Vector<String>> courseData = new Vector<>();
    public static JScrollPane scrollPane = new JScrollPane();

    public static JTable table = UIUtil.creatTable();


    public ClassInfoPanelAbstract(Teacher teacher) {
        scrollPane.setBounds(20, 50, 944, 550);
        scrollPane.getViewport().setBackground(Color.WHITE);
        add(scrollPane);

        CourseDao courseDao = new CourseDao();
        courseData = TeacherCourseDao.getCoursesForTeacher(teacher.getName());

        header = new Vector<>(Arrays.asList(
                "课程号", "课程名", "开课时间", "开课院系",
                "上课地点", "老师", "学分", "已选人数",
                "课程容量", "操作"
        ));

        setTableData(table, scrollPane, header, courseData);
        table.getColumnModel().getColumn(9).setCellRenderer(new ClassInfoPanelAbstract.QuaryButton());
        table.getColumnModel().getColumn(9).setCellEditor(new ClassInfoPanelAbstract.QuaryButtonEditor(table, teacher));

        refreshBt.addActionListener(e -> {
            refreshButtonClick(teacher);
            setTableData(table, scrollPane, header, courseData);
            table.getColumnModel().getColumn(9).setCellRenderer(new ClassInfoPanelAbstract.QuaryButton());
            table.getColumnModel().getColumn(9).setCellEditor(new ClassInfoPanelAbstract.QuaryButtonEditor(table, teacher));
        });

        JLabel courseNum = new JLabel("课程号:");
        courseNum.setForeground(Color.black);
        courseNum.setFont(new Font("微软雅黑", Font.PLAIN, 13));
        courseNum.setBounds(110, 13, 95, 25);
        add(courseNum);

        JLabel courseNam = new JLabel("课程名:");
        courseNam.setForeground(Color.black);
        courseNam.setFont(new Font("微软雅黑", Font.PLAIN, 13));
        courseNam.setBounds(260, 13, 95, 25);
        add(courseNam);

        JTextField courseNumber = new JTextField();
        courseNumber.setFont(new Font("微软雅黑", Font.PLAIN, 13));
        courseNumber.setForeground(Color.black);
        courseNumber.setBounds(160, 13, 90, 25);
        courseNumber.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        courseNumber.setColumns(8);
        add(courseNumber);

        JTextField courseName = new JTextField();
        courseName.setFont(new Font("微软雅黑", Font.PLAIN, 13));
        courseName.setForeground(Color.black);
        courseName.setBounds(310, 13, 90, 25);
        courseName.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        courseName.setColumns(8);
        add(courseName);

        JButton searchBt = new JButton("搜索");
        searchBt.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        searchBt.setForeground(Color.WHITE);
        searchBt.setBackground(new Color(0, 64, 128));
        searchBt.setCursor(new Cursor(Cursor.HAND_CURSOR));
        searchBt.setFocusPainted(false);
        searchBt.setBounds(450, 10, 80, 34);
        add(searchBt);

        JTextField courseMajor = new JTextField();
        JTextField courseTeacherName = new JTextField();

        searchBt.addActionListener(e -> {
            courseData = courseDao.queryAll(courseNumber.getText(), courseName.getText(), courseMajor.getText(), courseTeacherName.getText());
            setTableData(table, scrollPane, header, courseData);
            table.getColumnModel().getColumn(9).setCellRenderer(new ClassInfoPanelAbstract.QuaryButton());
            table.getColumnModel().getColumn(9).setCellEditor(new ClassInfoPanelAbstract.QuaryButtonEditor(table, teacher));
        });

    }

    @Override
    protected void refreshButtonClick(Teacher teacher) {
        TeacherCourseUtils.refreshCourseForCourseInfo(courseData);
    }

    static class QuaryButton implements TableCellRenderer {
        private final JButton btn;

        public QuaryButton() {
            btn = new JButton("查看");
            btn.setPreferredSize(new Dimension(30, 20));
            btn.setBackground(new Color(119, 201, 126));
            btn.setBorder(null);
            btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            btn.setFocusPainted(false);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
                                                       int row, int column) {
            btn.setText("查看");
            return btn;
        }
    }

    static class QuaryButtonEditor extends DefaultCellEditor {
        private final JButton btn;

        public QuaryButtonEditor(JTable table,Teacher teacher) {
            super(new JTextField());
            this.setClickCountToStart(1);
            btn = new JButton("查看");
            btn.setPreferredSize(new Dimension(30, 20));
            btn.setBackground(new Color(119, 201, 126));
            btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            btn.setBorder(null);
            btn.setFocusPainted(false);
            //弹出新窗口,可查阅学生信息
            btn.addActionListener(e -> {
                int rowIndex = table.getSelectedRow();
                if (rowIndex != -1) {
                    Object value = table.getValueAt(rowIndex, 0);
                    String courseNumber = null;
                    if (value != null) {
                        courseNumber = value.toString();
                    }
                    TeacherCourseUtils.quaryStudentInfo(courseNumber);
                }
            });

        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            btn.setText("查看");
            return btn;
        }
    }
}
