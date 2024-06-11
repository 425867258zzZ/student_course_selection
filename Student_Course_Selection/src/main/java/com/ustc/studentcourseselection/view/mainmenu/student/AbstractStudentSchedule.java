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
 * �γ̱�ĸ���
 *
 * @author ������
 */
public abstract class AbstractStudentSchedule extends Panel {
    public static Vector<String> header = new Vector<>(Arrays.asList(
            "�ڴ�", "��һ", "�ܶ�", "����", "����", "����"
    ));
    protected JButton refreshBt = new JButton("ˢ��");

    public AbstractStudentSchedule() {
        refreshBt.setFont(new Font("΢���ź�", Font.PLAIN, 14));
        refreshBt.setForeground(Color.WHITE);
        refreshBt.setBackground(new Color(0, 64, 128));
        refreshBt.setCursor(new Cursor(Cursor.HAND_CURSOR));
        refreshBt.setFocusPainted(false);
        refreshBt.setBounds(20, 10, 80, 34);
        add(refreshBt);
    }

    public static void setTableData(JTable table, JScrollPane scrollPane, Vector<String> header, Vector<StudentSchedule.CourseTime> courseTimeList) {
        // ����������ݵĶ�ά���飬��ʼ��Ϊ""
        String[][] tableData = new String[13][6];
        int dayMax = 5;
        int section = 13;
        // 13�д���1-13�ڣ�5�д�����һ������

        for (int i = 0; i < tableData.length; i++) {
            tableData[i][0] = Integer.toString(i + 1);

        }

        // �����γ�ʱ���б����������
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

        // ���ñ��ģ��
        table.setModel(new DefaultTableModel(dataForTableModel, header));

        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                this.setHorizontalAlignment(SwingConstants.CENTER);
                this.setVerticalAlignment(SwingConstants.CENTER);
                // �����к����ò�ͬ�ı�����ɫ
                switch (row / 5) { // ÿ5��һ������
                    case 0: // ��1����5��
                        c.setBackground(new Color(255, 255, 224));
                        break;
                    case 1: // ��6����10��
                        c.setBackground(new Color(255, 182, 193));
                        break;
                    case 2: // ��11����13��
                        c.setBackground(new Color(204, 255, 144));
                        break;
                    default:
                        c.setBackground(table.getBackground());
                }

                return c;
            }
        });

        // �����п���и�
        for (int i = 0; i < table.getColumnCount(); i++) {
            TableColumn col = table.getColumnModel().getColumn(i);
            col.setPreferredWidth(100);
        }
        int rowHeight = 40;
        table.setRowHeight(rowHeight);

        // �������ӵ��������
        scrollPane.setViewportView(table);
    }

    /**
     * ˢ�¿γ̱���Ϣ
     *
     * @param student ѧ��
     */
    protected abstract void refreshButtonClick(Student student);
}
