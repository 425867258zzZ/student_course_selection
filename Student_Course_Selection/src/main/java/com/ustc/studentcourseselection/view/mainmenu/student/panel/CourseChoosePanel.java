package com.ustc.studentcourseselection.view.mainmenu.student.panel;

import com.ustc.studentcourseselection.dao.CourseDao;
import com.ustc.studentcourseselection.view.Panel;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.Vector;

/**
 * @author ASUS
 */
public class CourseChoosePanel extends Panel {


    /**
     * Create the panel.
     */
    public CourseChoosePanel() {
        super();

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(20, 50, 944, 550);
        add(scrollPane);

        // ��ͷ����
        Vector<String> header = new Vector<>();
        header.add("�γ̺�");
        header.add("�γ���");
        header.add("����ʱ��");
        header.add("����Ժϵ");
        header.add("�Ͽεص�");
        header.add("ѧ��");
        header.add("��ѡ/�γ�����");
        header.add("����");

        CourseDao courseDao = new CourseDao();
        Vector<Vector<String>> allCourses = courseDao.queryAll();
        JTable table = new JTable();
        scrollPane.getViewport().setBackground(Color.WHITE);
        table.setBackground(Color.WHITE);
        table.setModel(new DefaultTableModel(allCourses, header));
        table.setRowHeight(25);
        scrollPane.setViewportView(table);

        TableColumn courseNumberCol = table.getColumnModel().getColumn(0);
        courseNumberCol.setPreferredWidth(30);

        TableColumn courseScoreCol = table.getColumnModel().getColumn(5);
        courseScoreCol.setPreferredWidth(5);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

    }

}
