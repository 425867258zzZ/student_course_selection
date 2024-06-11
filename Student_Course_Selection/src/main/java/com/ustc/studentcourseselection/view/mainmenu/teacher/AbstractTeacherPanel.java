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
 * ��ʦ���ĸ���
 * ���ڲ�ͬ���ˢ�µ��߼���ͬ���ó�����ʵ�ֶ�̬
 *
 * @author ������
 */
public abstract class AbstractTeacherPanel extends Panel {

    public static Vector<String> header = new Vector<>(Arrays.asList(
            "�γ̺�", "�γ���", "*����ʱ��", "����Ժϵ",
            "*�Ͽεص�", "��ʦ", "ѧ��", "��ѡ����",
            "*�γ�����", "����"
    ));
    protected JButton refreshBt = new JButton("ˢ��");

    public AbstractTeacherPanel() {
        refreshBt.setFont(new Font("΢���ź�", Font.PLAIN, 14));
        refreshBt.setForeground(Color.WHITE);
        refreshBt.setBackground(new Color(0, 64, 128));
        refreshBt.setCursor(new Cursor(Cursor.HAND_CURSOR));
        refreshBt.setFocusPainted(false);
        refreshBt.setBounds(20, 10, 80, 34);
        add(refreshBt);
    }

    public static void setTableData(JTable table, JScrollPane scrollPane, Vector<String> header, Vector<Vector<String>> data) {

        table.setModel(new DefaultTableModel(data, header));
        // �����γ̺�һ�п�
        TableColumn courseNumberCol = table.getColumnModel().getColumn(0);
        courseNumberCol.setPreferredWidth(25);

        // ���������п�
        int startCol = 4;
        int endCol = 9;
        for (int i = startCol; i <= endCol; i++) {
            TableColumn col = table.getColumnModel().getColumn(i);
            col.setPreferredWidth(i);
        }

        // ���������о�����ʾ
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        scrollPane.setViewportView(table);
    }

    /**
     * ˢ�°�ť����¼�,ʵ�������̬���ú���
     *
     * @param teacher ��ʦ
     */
    protected abstract void refreshButtonClick(Teacher teacher);
}
