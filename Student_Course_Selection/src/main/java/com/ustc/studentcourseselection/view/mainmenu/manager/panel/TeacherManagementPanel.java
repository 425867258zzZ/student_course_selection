package com.ustc.studentcourseselection.view.mainmenu.manager.panel;

import com.ustc.studentcourseselection.dao.TeacherDao;
import com.ustc.studentcourseselection.view.UIUtil;
import com.ustc.studentcourseselection.view.mainmenu.manager.AbstractManagerPanel;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.util.Arrays;
import java.util.Vector;

/**
 * @author wangpanyang5
 */
public class TeacherManagementPanel extends AbstractManagerPanel {

    public static Vector<Vector<String>> teacherData = new Vector<>();
    public static JScrollPane scrollPane = new JScrollPane();

    public static JTable table = UIUtil.creatTable();

    public static Vector<String> header = new Vector<>(Arrays.asList(
            "ÐÕÃû", "¹¤ºÅ", "ÐÔ±ð", "²¿ÃÅ","ÐÞ¸Ä","É¾³ý"
    ));

    public TeacherManagementPanel(JPanel mainpanel, JTabbedPane tabbedPane) {
        setLayout(null);

        scrollPane.setBounds(20, 50, 944, 550);
        scrollPane.getViewport().setBackground(Color.WHITE);
        add(scrollPane);

        TeacherDao teacherDao = new TeacherDao();
        teacherData = teacherDao.queryAll(null,null,null);

        setTableData(table, scrollPane, header, teacherData);
        table.getColumnModel().getColumn(4).setCellRenderer(new EdictButton());
        table.getColumnModel().getColumn(4).setCellEditor(new EdictButtonEditor(table, mainpanel, tabbedPane));
        table.getColumnModel().getColumn(5).setCellRenderer(new DeleteButton());
        table.getColumnModel().getColumn(5).setCellEditor(new DeleteButtonEditor(table,mainpanel,tabbedPane));

        refreshBt.addActionListener(e->{
            refreshButtonClick(mainpanel,tabbedPane);
        });

        JButton addButton = new JButton("Ìí¼Ó");
        addButton.setPreferredSize(new Dimension(30, 20));
        addButton.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 14));
        addButton.setForeground(Color.WHITE);
        addButton.setBackground(new Color(0, 64, 128));
        addButton.setBorder(null);
        addButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        addButton.setFocusPainted(false);
        addButton.addActionListener(e -> {
            JPanel teacherAdd = new TeacherAddPanel(tabbedPane);
            mainpanel.add(teacherAdd, "teacherAddPanel");
            UIUtil.showOrSwitchToPanel(mainpanel, tabbedPane, teacherAdd, "ÀÏÊ¦Ìí¼Ó");
        });
        addButton.setBounds(883, 10, 80, 34);
        add(addButton);

        JLabel teacherNam = new JLabel("ÐÕÃû:");
        teacherNam.setForeground(Color.black);
        teacherNam.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 13));
        teacherNam.setBounds(110, 13, 95, 25);
        add(teacherNam);


        JLabel teacherNum = new JLabel("¹¤ºÅ:");
        teacherNum.setForeground(Color.black);
        teacherNum.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 13));
        teacherNum.setBounds(260, 13, 95, 25);
        add(teacherNum);

        JLabel teacherDep = new JLabel("²¿ÃÅ:");
        teacherDep.setForeground(Color.black);
        teacherDep.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 13));
        teacherDep.setBounds(410, 13, 95, 25);
        add(teacherDep);


        JTextField teacherName = new JTextField();
        teacherName.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 13));
        teacherName.setForeground(Color.black);
        teacherName.setBounds(160, 13, 90, 25);
        teacherName.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        teacherName.setColumns(8);
        add(teacherName);

        JTextField teacherNumber = new JTextField();
        teacherNumber.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 13));
        teacherNumber.setForeground(Color.black);
        teacherNumber.setBounds(310, 13, 90, 25);
        teacherNumber.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        teacherNumber.setColumns(8);
        add(teacherNumber);

        JTextField teacherDepartment = new JTextField();
        teacherDepartment.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 13));
        teacherDepartment.setForeground(Color.black);
        teacherDepartment.setBounds(474, 13, 120, 25);
        teacherDepartment.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        teacherDepartment.setColumns(8);
        add(teacherDepartment);


        JButton searchBt = new JButton("ËÑË÷");
        searchBt.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 14));
        searchBt.setForeground(Color.WHITE);
        searchBt.setBackground(new Color(0, 64, 128));
        searchBt.setCursor(new Cursor(Cursor.HAND_CURSOR));
        searchBt.setFocusPainted(false);
        searchBt.setBounds(783, 10, 80, 34);
        add(searchBt);

        searchBt.addActionListener(e -> {
            teacherData = teacherDao.queryAll(teacherName.getText(),teacherNumber.getText(),teacherDepartment.getText());
            setTableData(table, scrollPane, header, teacherData);
            table.getColumnModel().getColumn(4).setCellRenderer(new EdictButton());
            table.getColumnModel().getColumn(4).setCellEditor(new EdictButtonEditor(table, mainpanel, tabbedPane));
            table.getColumnModel().getColumn(5).setCellRenderer(new DeleteButton());
            table.getColumnModel().getColumn(5).setCellEditor(new DeleteButtonEditor(table,mainpanel,tabbedPane));
        });
    }

    @Override
    protected void refreshButtonClick(JPanel mainpanel,JTabbedPane tabbedPane) {
        teacherData = new TeacherDao().queryAll(null,null,null);
        setTableData(table, scrollPane, header, teacherData);
        table.getColumnModel().getColumn(4).setCellRenderer(new EdictButton());
        table.getColumnModel().getColumn(4).setCellEditor(new EdictButtonEditor(table, mainpanel, tabbedPane));
        table.getColumnModel().getColumn(5).setCellRenderer(new DeleteButton());
        table.getColumnModel().getColumn(5).setCellEditor(new DeleteButtonEditor(table,mainpanel,tabbedPane));
    }


    static class EdictButton implements TableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus, int row, int column) {
            JButton btn = new JButton("±à¼­");
            btn.setPreferredSize(new Dimension(30, 20));
            btn.setBackground(new Color(119, 201, 126));
            btn.setBorder(null);
            btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            btn.setFocusPainted(false);
            return btn;
        }
    }

    static class EdictButtonEditor extends DefaultCellEditor {
        private final JButton btn;

        public EdictButtonEditor(JTable table, JPanel mainpanel, JTabbedPane tabbedPane) {
            super(new JTextField());
            this.setClickCountToStart(1);
            btn = new JButton("±à¼­");
            btn.setPreferredSize(new Dimension(30, 20));
            btn.setBackground(new Color(119, 201, 126));
            btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            btn.setBorder(null);
            btn.setFocusPainted(false);
            btn.addActionListener(e -> {
                int row = table.getEditingRow();
                if (row != -1) {
                    Vector<String> rowData = teacherData.get(row);
                    System.out.println(rowData.get(0));
                    JPanel teacherEdictPanel = new TeacherEdictPanel(tabbedPane, rowData);
                    mainpanel.add(teacherEdictPanel, "teacherEdictPanel");
                    UIUtil.showOrSwitchToPanel(mainpanel, tabbedPane, teacherEdictPanel, "ÀÏÊ¦±à¼­");
                }
            });
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            return btn;
        }
    }

    static class DeleteButton implements TableCellRenderer{
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus, int row, int column) {
            JButton btn = new JButton("É¾³ý");
            btn.setPreferredSize(new Dimension(30, 20));
            btn.setBackground(new Color(119, 201, 126));
            btn.setBorder(null);
            btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            btn.setFocusPainted(false);
            return btn;
        }
    }

    class DeleteButtonEditor extends DefaultCellEditor {
        private final JButton btn;
        public DeleteButtonEditor(JTable table,JPanel mainpanel,JTabbedPane tabbedPane){
            super(new JTextField());
            this.setClickCountToStart(1);
            btn = new JButton("É¾³ý");
            btn.setPreferredSize(new Dimension(30, 20));
            btn.setBackground(new Color(119, 201, 126));
            btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            btn.setBorder(null);
            btn.setFocusPainted(false);
            btn.addActionListener(e->{
                int choice = JOptionPane.showConfirmDialog(TeacherManagementPanel.this,"ÄãÈ·ÈÏÒª½øÐÐÉ¾³ýÂð","È·ÈÏÉ¾³ý",JOptionPane.YES_NO_OPTION);
                if(choice==JOptionPane.YES_NO_OPTION) {
                    int row = table.getEditingRow();
                    if (row != -1) {
                        Vector<String> rowData = teacherData.get(row);
                        String number = rowData.get(1);
                        boolean result=new TeacherDao().del(number);
                        if(result){
                            JOptionPane.showMessageDialog(TeacherManagementPanel.this,"É¾³ý³É¹¦","ÌáÊ¾",JOptionPane.INFORMATION_MESSAGE);
                            teacherData.remove(row);
                            setTableData(table, scrollPane, header, teacherData);
                            table.getColumnModel().getColumn(4).setCellRenderer(new EdictButton());
                            table.getColumnModel().getColumn(4).setCellEditor(new EdictButtonEditor(table, mainpanel, tabbedPane));
                            table.getColumnModel().getColumn(5).setCellRenderer(new DeleteButton());
                            table.getColumnModel().getColumn(5).setCellEditor(new DeleteButtonEditor(table,mainpanel,tabbedPane));
                        }else{
                            JOptionPane.showMessageDialog(TeacherManagementPanel.this,"É¾³ýÊ§°Ü","ÌáÊ¾",JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                }
            });
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            return btn;
        }
    }
}