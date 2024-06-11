package com.ustc.studentcourseselection.view.mainmenu.manager.panel;

import com.ustc.studentcourseselection.dao.CourseDao;
import com.ustc.studentcourseselection.dao.StudentDao;
import com.ustc.studentcourseselection.view.UIUtil;
import com.ustc.studentcourseselection.view.mainmenu.manager.AbstractManagerPanel;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.util.Arrays;
import java.util.Vector;

/**
 * @author ºÎê»
 */
public class StudentManagementPanel extends AbstractManagerPanel {

        public static Vector<Vector<String>> studentData = new Vector<>();
        public static JScrollPane scrollPane = new JScrollPane();

        public static JTable table = UIUtil.creatTable();

        public static Vector<String> header = new Vector<>(Arrays.asList(
                "Ñ§ºÅ", "ÐÕÃû", "ÐÔ±ð", "×¨Òµ","Äê¼¶",
                "Ñ§Àú", "°à¼¶","±à¼­","É¾³ý"

        ));

        public StudentManagementPanel(JPanel mainpanel, JTabbedPane tabbedPane) {
            setLayout(null);

            scrollPane.setBounds(20, 50, 944, 550);
            scrollPane.getViewport().setBackground(Color.WHITE);
            add(scrollPane);

            StudentDao studentdao=new StudentDao();
            studentData = studentdao.queryAll(null, null, null, null);

            setTableData(table, scrollPane, header, studentData);
            table.getColumnModel().getColumn(7).setCellRenderer(new EdictButton());
            table.getColumnModel().getColumn(7).setCellEditor(new EdictButtonEditor(table, mainpanel, tabbedPane));
            table.getColumnModel().getColumn(8).setCellRenderer(new DeleteButton());
            table.getColumnModel().getColumn(8).setCellEditor(new DeleteButtonEditor(table,mainpanel,tabbedPane));

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
                JPanel studentAdd = new StudentAddPanel(tabbedPane);
                mainpanel.add(studentAdd, "studentAddPanel");
                UIUtil.showOrSwitchToPanel(mainpanel, tabbedPane, studentAdd, "Ñ§ÉúÔö¼Ó");
            });
            addButton.setBounds(883, 10, 80, 34);
            add(addButton);

            JLabel studentNam = new JLabel("ÐÕÃû:");
            studentNam.setForeground(Color.black);
            studentNam.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 13));
            studentNam.setBounds(110, 13, 95, 25);
            add(studentNam);

            JLabel courseNum = new JLabel("Ñ§ºÅ:");
            courseNum.setForeground(Color.black);
            courseNum.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 13));
            courseNum.setBounds(260, 13, 95, 25);
            add(courseNum);

            JLabel major = new JLabel("×¨Òµ:");
            major.setForeground(Color.black);
            major.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 13));
            major.setBounds(410, 13, 95, 25);
            add(major);

            JLabel className = new JLabel("°à¼¶:");
            className.setForeground(Color.black);
            className.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 13));
            className.setBounds(600, 13, 95, 25);
            add(className);


            JTextField studentName = new JTextField();
            studentName.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 13));
            studentName.setForeground(Color.black);
            studentName.setBounds(160, 13, 90, 25);
            studentName.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
            studentName.setColumns(8);
            add(studentName);

            JTextField studentNumber = new JTextField();
            studentNumber .setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 13));
            studentNumber .setForeground(Color.black);
            studentNumber .setBounds(310, 13, 90, 25);
            studentNumber .setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
            studentNumber .setColumns(8);
            add(studentNumber );

            JTextField studentMajor = new JTextField();
            studentMajor.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 13));
            studentMajor.setForeground(Color.black);
            studentMajor.setBounds(474, 13, 120, 25);
            studentMajor.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
            studentMajor.setColumns(8);
            add(studentMajor);

            JTextField studentClassNam = new JTextField();
            studentClassNam.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 13));
            studentClassNam.setForeground(Color.black);
            studentClassNam.setBounds(664, 13, 90, 25);
            studentClassNam.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
            studentClassNam.setColumns(8);
            add(studentClassNam);


            JButton searchBt = new JButton("ËÑË÷");
            searchBt.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 14));
            searchBt.setForeground(Color.WHITE);
            searchBt.setBackground(new Color(0, 64, 128));
            searchBt.setCursor(new Cursor(Cursor.HAND_CURSOR));
            searchBt.setFocusPainted(false);
            searchBt.setBounds(783, 10, 80, 34);
            add(searchBt);

            searchBt.addActionListener(e -> {
                studentData = studentdao.queryAll(studentName.getText(),studentNumber.getText(),studentMajor.getText(),studentClassNam.getText());
                setTableData(table, scrollPane, header, studentData);
                table.getColumnModel().getColumn(7).setCellRenderer(new EdictButton());
                table.getColumnModel().getColumn(7).setCellEditor(new EdictButtonEditor(table, mainpanel, tabbedPane));
                table.getColumnModel().getColumn(8).setCellRenderer(new DeleteButton());
                table.getColumnModel().getColumn(8).setCellEditor(new DeleteButtonEditor(table,mainpanel,tabbedPane));
            });
        }
        @Override
        protected void refreshButtonClick(JPanel mainpanel,JTabbedPane tabbedPane) {
            studentData = new StudentDao().queryAll(null,null,null,null);
            setTableData(table, scrollPane, header, studentData);
            table.getColumnModel().getColumn(7).setCellRenderer(new EdictButton());
            table.getColumnModel().getColumn(7).setCellEditor(new EdictButtonEditor(table, mainpanel, tabbedPane));
            table.getColumnModel().getColumn(8).setCellRenderer(new DeleteButton());
            table.getColumnModel().getColumn(8).setCellEditor(new DeleteButtonEditor(table,mainpanel,tabbedPane));
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
                        Vector<String> rowData = studentData.get(row);
                        System.out.println(rowData.get(1));
                        JPanel studentEdictPanel = new StudentEdictPanel(tabbedPane, rowData);
                        mainpanel.add(studentEdictPanel, "studentEdictPanel");
                        UIUtil.showOrSwitchToPanel(mainpanel, tabbedPane, studentEdictPanel, "Ñ§Éú±à¼­");
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
                    int choice = JOptionPane.showConfirmDialog(StudentManagementPanel.this,"ÄãÈ·ÈÏÒª½øÐÐÉ¾³ýÂð","È·ÈÏÉ¾³ý",JOptionPane.YES_NO_OPTION);
                    if(choice==JOptionPane.YES_NO_OPTION) {
                        int row = table.getEditingRow();
                        if (row != -1) {
                            Vector<String> rowData = studentData.get(row);
                            String number = rowData.getFirst();
                            boolean result=new CourseDao().delCourse(number);
                            if(result){
                                JOptionPane.showMessageDialog(StudentManagementPanel.this,"É¾³ý³É¹¦","ÌáÊ¾",JOptionPane.INFORMATION_MESSAGE);
                                studentData.remove(row);
                                setTableData(table, scrollPane, header, studentData);
                                table.getColumnModel().getColumn(7).setCellRenderer(new EdictButton());
                                table.getColumnModel().getColumn(7).setCellEditor(new EdictButtonEditor(table, mainpanel, tabbedPane));
                                table.getColumnModel().getColumn(8).setCellRenderer(new DeleteButton());
                                table.getColumnModel().getColumn(8).setCellEditor(new DeleteButtonEditor(table,mainpanel,tabbedPane));
                            }else{
                                JOptionPane.showMessageDialog(StudentManagementPanel.this,"É¾³ýÊ§°Ü","ÌáÊ¾",JOptionPane.INFORMATION_MESSAGE);
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

