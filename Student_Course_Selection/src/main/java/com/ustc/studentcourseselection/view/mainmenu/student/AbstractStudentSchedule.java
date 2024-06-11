package com.ustc.studentcourseselection.view.mainmenu.student;

import com.ustc.studentcourseselection.model.Student;
import com.ustc.studentcourseselection.view.Panel;
import com.ustc.studentcourseselection.view.mainmenu.student.panel.StudentSchedule;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.Color;
import java.util.Arrays;
import java.util.Vector;

/**
 * 课程表的父类
 *
 * @author 沈文杨
 */
public abstract class AbstractStudentSchedule extends Panel {
    public static Vector<String> header = new Vector<>(Arrays.asList(
            "节次", "周一", "周二", "周三", "周四", "周五"
    ));
    protected JButton refreshBt = new JButton("刷新");

    public AbstractStudentSchedule() {
        refreshBt.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        refreshBt.setForeground(Color.WHITE);
        refreshBt.setBackground(new Color(0, 64, 128));
        refreshBt.setCursor(new Cursor(Cursor.HAND_CURSOR));
        refreshBt.setFocusPainted(false);
        refreshBt.setBounds(20, 10, 80, 34);
        add(refreshBt);
    }

    public static void setTableData(JTable table, JScrollPane scrollPane, Vector<String> header, Vector<StudentSchedule.CourseTime> courseTimeList) {
        // 创建表格数据的二维数组，初始化为""
        String[][] tableData = new String[13][6];
        int dayMax = 5;
        int section = 13;
        // 13行代表1-13节，5列代表周一到周五

        for (int i = 0; i < tableData.length; i++) {
            tableData[i][0] = Integer.toString(i + 1);

        }

        // 遍历课程时间列表，填充表格数据
        for (StudentSchedule.CourseTime courseTime : courseTimeList) {
            String courseName = courseTime.courseName();
            String courseLocation = courseTime.courseLocation();
            int[][] timeSlots = courseTime.courseTime();
            for (int day = 0; day < dayMax; day++) {
                for (int slot = 0; slot < section; slot++) {

                    if (timeSlots[day][slot] > 0 && tableData[slot][day + 1] == null) {
                        String cellText = courseName + "\n" + "(" + courseLocation + ")";

                        tableData[slot][day + 1] = cellText;
                    }
                }
            }
        }

        Vector<Vector<String>> dataForTableModel = new Vector<>();
        for (String[] row : tableData) {
            dataForTableModel.add(new Vector<>(Arrays.asList(row)));
        }

        // 设置表格模型
        table.setModel(new DefaultTableModel(dataForTableModel, header));

        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                this.setHorizontalAlignment(SwingConstants.CENTER);
                this.setVerticalAlignment(SwingConstants.CENTER);
                // 根据行号设置不同的背景颜色
                switch (row / 5) { // 每5行一个周期
                    case 0: // 第1到第5行
                        c.setBackground(new Color(255, 255, 224));
                        break;
                    case 1: // 第6到第10行
                        c.setBackground(new Color(255, 182, 193));
                        break;
                    case 2: // 第11到第13行
                        c.setBackground(new Color(204, 255, 144));
                        break;
                    default:
                        c.setBackground(table.getBackground());
                }

                return c;
            }
        });

        // 设置列宽和行高
        for (int i = 0; i < table.getColumnCount(); i++) {
            TableColumn col = table.getColumnModel().getColumn(i);
            col.setPreferredWidth(100);
        }
        int rowHeight = 40;
        table.setRowHeight(rowHeight);

        // 将表格添加到滚动面板
        scrollPane.setViewportView(table);
    }

    /**
     * 刷新课程表信息
     *
     * @param student 学生
     */
    protected abstract void refreshButtonClick(Student student);
}
