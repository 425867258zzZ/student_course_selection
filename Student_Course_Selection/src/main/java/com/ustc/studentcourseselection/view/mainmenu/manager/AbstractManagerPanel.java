package com.ustc.studentcourseselection.view.mainmenu.manager;

import com.ustc.studentcourseselection.model.Course;
import com.ustc.studentcourseselection.model.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.Vector;

/**
 * @author 13901
 */
public abstract class AbstractManagerPanel extends JPanel {
    protected JButton refreshBt = new JButton("刷新");

    public AbstractManagerPanel() {
        refreshBt.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        refreshBt.setForeground(Color.WHITE);
        refreshBt.setBackground(new Color(0, 64, 128));
        refreshBt.setCursor(new Cursor(Cursor.HAND_CURSOR));
        refreshBt.setFocusPainted(false);
        refreshBt.setBounds(20, 10, 80, 34);
        add(refreshBt);
    }

    public static void setTableData(JTable table, JScrollPane scrollPane, Vector<String> header, Vector<Vector<String>> data) {

        table.setModel(new DefaultTableModel(data, header));
        TableColumn courseNumberCol = table.getColumnModel().getColumn(0);
        courseNumberCol.setPreferredWidth(25);

        // 批量调整列宽
        int startCol = 4;
        int endCol = 5;
        for (int i = startCol; i <= endCol; i++) {
            TableColumn col = table.getColumnModel().getColumn(i);
            col.setPreferredWidth(i);
        }

        // 设置所有列居中显示
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        scrollPane.setViewportView(table);

    }


    protected abstract void refreshButtonClick(JPanel mainpanel, JTabbedPane tabbedPane);
}
