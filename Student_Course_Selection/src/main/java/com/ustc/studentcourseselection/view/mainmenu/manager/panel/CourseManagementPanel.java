package com.ustc.studentcourseselection.view.mainmenu.manager.panel;

import com.ustc.studentcourseselection.dao.CourseDao;
import com.ustc.studentcourseselection.view.UIUtil;
import com.ustc.studentcourseselection.view.mainmenu.manager.AbstractManagerPanel;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.util.Arrays;
import java.util.Vector;

/**
 * @author 13901
 */
public class CourseManagementPanel extends AbstractManagerPanel {

    public static Vector<Vector<String>> courseData = new Vector<>();
    public static JScrollPane scrollPane = new JScrollPane();

    public static JTable table = UIUtil.creatTable();

    public static Vector<String> header = new Vector<>(Arrays.asList(
            "�γ̺�", "�γ���", "����ʱ��", "����Ժϵ",
            "�Ͽεص�", "��ʦ", "ѧ��", "��ѡ����",
            "�γ�����", "�޸�", "ɾ��"
    ));

    public CourseManagementPanel(JPanel mainpanel, JTabbedPane tabbedPane) {
        setLayout(null);

        scrollPane.setBounds(20, 50, 944, 550);
        scrollPane.getViewport().setBackground(Color.WHITE);
        add(scrollPane);

        CourseDao courseDao = new CourseDao();
        courseData = courseDao.queryAll(null, null, null, null);

        setTableData(table, scrollPane, header, courseData);
        table.getColumnModel().getColumn(9).setCellRenderer(new EdictButton());
        table.getColumnModel().getColumn(9).setCellEditor(new EdictButtonEditor(table, mainpanel, tabbedPane));
        table.getColumnModel().getColumn(10).setCellRenderer(new DeleteButton());
        table.getColumnModel().getColumn(10).setCellEditor(new DeleteButtonEditor(table,mainpanel,tabbedPane));

        refreshBt.addActionListener(e->{
            refreshButtonClick(mainpanel,tabbedPane);
        });

        JButton addButton = new JButton("���");
        addButton.setPreferredSize(new Dimension(30, 20));
        addButton.setFont(new Font("΢���ź�", Font.PLAIN, 14));
        addButton.setForeground(Color.WHITE);
        addButton.setBackground(new Color(0, 64, 128));
        addButton.setBorder(null);
        addButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        addButton.setFocusPainted(false);
        addButton.addActionListener(e -> {
            courseData=new CourseDao().queryAll(null,null,null,null);
            JPanel courseAdd = new CourseAddPanel(tabbedPane,courseData);
            mainpanel.add(courseAdd, "courseAddPanel");
            UIUtil.showOrSwitchToPanel(mainpanel, tabbedPane, courseAdd, "�γ�����");
        });
        addButton.setBounds(883, 10, 80, 34);
        add(addButton);

        JLabel courseNum = new JLabel("�γ̺�:");
        courseNum.setForeground(Color.black);
        courseNum.setFont(new Font("΢���ź�", Font.PLAIN, 13));
        courseNum.setBounds(110, 13, 95, 25);
        add(courseNum);

        JLabel courseNam = new JLabel("�γ���:");
        courseNam.setForeground(Color.black);
        courseNam.setFont(new Font("΢���ź�", Font.PLAIN, 13));
        courseNam.setBounds(260, 13, 95, 25);
        add(courseNam);

        JLabel courseMaj = new JLabel("����Ժϵ:");
        courseMaj.setForeground(Color.black);
        courseMaj.setFont(new Font("΢���ź�", Font.PLAIN, 13));
        courseMaj.setBounds(410, 13, 95, 25);
        add(courseMaj);

        JLabel courseTea = new JLabel("��ʦ����:");
        courseTea.setForeground(Color.black);
        courseTea.setFont(new Font("΢���ź�", Font.PLAIN, 13));
        courseTea.setBounds(600, 13, 95, 25);
        add(courseTea);


        JTextField courseNumber = new JTextField();
        courseNumber.setFont(new Font("΢���ź�", Font.PLAIN, 13));
        courseNumber.setForeground(Color.black);
        courseNumber.setBounds(160, 13, 90, 25);
        courseNumber.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        courseNumber.setColumns(8);
        add(courseNumber);

        JTextField courseName = new JTextField();
        courseName.setFont(new Font("΢���ź�", Font.PLAIN, 13));
        courseName.setForeground(Color.black);
        courseName.setBounds(310, 13, 90, 25);
        courseName.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        courseName.setColumns(8);
        add(courseName);

        JTextField courseMajor = new JTextField();
        courseMajor.setFont(new Font("΢���ź�", Font.PLAIN, 13));
        courseMajor.setForeground(Color.black);
        courseMajor.setBounds(474, 13, 120, 25);
        courseMajor.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        courseMajor.setColumns(8);
        add(courseMajor);

        JTextField courseTeacherName = new JTextField();
        courseTeacherName.setFont(new Font("΢���ź�", Font.PLAIN, 13));
        courseTeacherName.setForeground(Color.black);
        courseTeacherName.setBounds(664, 13, 90, 25);
        courseTeacherName.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        courseTeacherName.setColumns(8);
        add(courseTeacherName);


        JButton searchBt = new JButton("����");
        searchBt.setFont(new Font("΢���ź�", Font.PLAIN, 14));
        searchBt.setForeground(Color.WHITE);
        searchBt.setBackground(new Color(0, 64, 128));
        searchBt.setCursor(new Cursor(Cursor.HAND_CURSOR));
        searchBt.setFocusPainted(false);
        searchBt.setBounds(783, 10, 80, 34);
        add(searchBt);

        searchBt.addActionListener(e -> {
            courseData = courseDao.queryAll(courseNumber.getText(), courseName.getText(), courseMajor.getText(), courseTeacherName.getText());
            setTableData(table, scrollPane, header, courseData);
            table.getColumnModel().getColumn(9).setCellRenderer(new EdictButton());
            table.getColumnModel().getColumn(9).setCellEditor(new EdictButtonEditor(table, mainpanel, tabbedPane));
            table.getColumnModel().getColumn(10).setCellRenderer(new DeleteButton());
            table.getColumnModel().getColumn(10).setCellEditor(new DeleteButtonEditor(table,mainpanel,tabbedPane));
        });
    }

    @Override
    protected void refreshButtonClick(JPanel mainpanel,JTabbedPane tabbedPane) {
        courseData = new CourseDao().queryAll(null, null, null, null);
        setTableData(table, scrollPane, header, courseData);
        table.getColumnModel().getColumn(9).setCellRenderer(new EdictButton());
        table.getColumnModel().getColumn(9).setCellEditor(new EdictButtonEditor(table, mainpanel, tabbedPane));
        table.getColumnModel().getColumn(10).setCellRenderer(new DeleteButton());
        table.getColumnModel().getColumn(10).setCellEditor(new DeleteButtonEditor(table,mainpanel,tabbedPane));
    }


    static class EdictButton implements TableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus, int row, int column) {
            JButton btn = new JButton("�༭");
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
            btn = new JButton("�༭");
            btn.setPreferredSize(new Dimension(30, 20));
            btn.setBackground(new Color(119, 201, 126));
            btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            btn.setBorder(null);
            btn.setFocusPainted(false);
            btn.addActionListener(e -> {
                int row = table.getEditingRow();
                if (row != -1) {
                    courseData=new CourseDao().queryAll(null,null,null,null);
                    Vector<String> rowData = courseData.get(row);
                    System.out.println(rowData.get(1));
                    JPanel courseEdictPanel = new CourseEdictPanel(tabbedPane, rowData,courseData);
                    mainpanel.add(courseEdictPanel, "courseEdictPanel");
                    UIUtil.showOrSwitchToPanel(mainpanel, tabbedPane, courseEdictPanel, "�γ̱༭");
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
            JButton btn = new JButton("ɾ��");
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
            btn = new JButton("ɾ��");
            btn.setPreferredSize(new Dimension(30, 20));
            btn.setBackground(new Color(119, 201, 126));
            btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            btn.setBorder(null);
            btn.setFocusPainted(false);
            btn.addActionListener(e->{
                int choice = JOptionPane.showConfirmDialog(CourseManagementPanel.this,"��ȷ��Ҫ����ɾ����","ȷ��ɾ��",JOptionPane.YES_NO_OPTION);
                if(choice==JOptionPane.YES_NO_OPTION) {
                    int row = table.getEditingRow();
                    if (row != -1) {
                        Vector<String> rowData = courseData.get(row);
                        String number = rowData.getFirst();
                        boolean result=new CourseDao().delCourse(number);
                        if(result){
                            JOptionPane.showMessageDialog(CourseManagementPanel.this,"ɾ���ɹ�","��ʾ",JOptionPane.INFORMATION_MESSAGE);
                            courseData.remove(row);
                            setTableData(table, scrollPane, header, courseData);
                            table.getColumnModel().getColumn(9).setCellRenderer(new EdictButton());
                            table.getColumnModel().getColumn(9).setCellEditor(new EdictButtonEditor(table, mainpanel, tabbedPane));
                            table.getColumnModel().getColumn(10).setCellRenderer(new DeleteButton());
                            table.getColumnModel().getColumn(10).setCellEditor(new DeleteButtonEditor(table,mainpanel,tabbedPane));
                        }else{
                            JOptionPane.showMessageDialog(CourseManagementPanel.this,"ɾ��ʧ��","��ʾ",JOptionPane.INFORMATION_MESSAGE);
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