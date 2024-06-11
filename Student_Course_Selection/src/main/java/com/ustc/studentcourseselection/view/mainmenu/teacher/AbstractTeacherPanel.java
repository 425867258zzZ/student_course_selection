package com.ustc.studentcourseselection.view.mainmenu.teacher;

import com.ustc.studentcourseselection.model.Teacher;
import com.ustc.studentcourseselection.view.Panel;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.Arrays;
import java.util.Vector;

/**
 * 老师面板的父类
 * 由于不同面板刷新的逻辑不同，用抽象函数实现多态
 *
 * @author 孟梓晗
 */
public abstract class AbstractTeacherPanel extends Panel {

    public static Vector<String> header = new Vector<>(Arrays.asList(
            "课程号", "课程名", "*开课时间", "开课院系",
            "*上课地点", "老师", "学分", "已选人数",
            "*课程容量", "操作"
    ));
    protected JButton refreshBt = new JButton("刷新");

    public AbstractTeacherPanel() {
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
        // 调整课程号一列宽
        TableColumn courseNumberCol = table.getColumnModel().getColumn(0);
        courseNumberCol.setPreferredWidth(25);

        // 批量调整列宽
        int startCol = 4;
        int endCol = 9;
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

    /**
     * 刷新按钮点击事件,实现子类多态调用函数
     *
     * @param teacher 老师
     */
    protected abstract void refreshButtonClick(Teacher teacher);
}
