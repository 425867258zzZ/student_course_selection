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
import java.util.Vector;
import static com.ustc.studentcourseselection.view.mainmenu.teacher.panel.PopUpPanel.setupCoursePanel;


/**
 * �γ���Ϣ������
 *
 * @author ������
 */
public class CourseInfoPanelAbstract extends AbstractTeacherPanel {
    public static Vector<Vector<String>> courseData = new Vector<>();
    public static JScrollPane scrollPane = new JScrollPane();

    public static JTable table = UIUtil.creatTable();

    public CourseInfoPanelAbstract(Teacher teacher) {
        scrollPane.setBounds(20, 50, 944, 550);
        scrollPane.getViewport().setBackground(Color.WHITE);
        add(scrollPane);

        CourseDao courseDao = new CourseDao();
        courseData = courseDao.queryAll(null,null,null,teacher.getName());

        setTableData(table, scrollPane, header, courseData);
        table.getColumnModel().getColumn(9).setCellRenderer(new ModifyButton());
        table.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(new JTextField()));
        table.getColumnModel().getColumn(4).setCellEditor(new DefaultCellEditor(new JTextField()));
        table.getColumnModel().getColumn(8).setCellEditor(new DefaultCellEditor(new JTextField()));
        table.getColumnModel().getColumn(9).setCellEditor(new ModifyButtonEditor(table));

        refreshBt.addActionListener(e -> {
            refreshButtonClick(teacher);
            setTableData(table, scrollPane, header, courseData);
            table.getColumnModel().getColumn(9).setCellRenderer(new ModifyButton());
            table.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(new JTextField()));
            table.getColumnModel().getColumn(4).setCellEditor(new DefaultCellEditor(new JTextField()));
            table.getColumnModel().getColumn(8).setCellEditor(new DefaultCellEditor(new JTextField()));
            table.getColumnModel().getColumn(9).setCellEditor(new ModifyButtonEditor(table));
        });

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

        JButton searchBt = new JButton("����");
        searchBt.setFont(new Font("΢���ź�", Font.PLAIN, 14));
        searchBt.setForeground(Color.WHITE);
        searchBt.setBackground(new Color(0, 64, 128));
        searchBt.setCursor(new Cursor(Cursor.HAND_CURSOR));
        searchBt.setFocusPainted(false);
        searchBt.setBounds(450, 10, 80, 34);
        add(searchBt);

        ImageIcon originalIcon = new ImageIcon("src/main/resources/images/side/course_white.png");
        Image scaledIcon = originalIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(scaledIcon);
        JButton setupCourse = new JButton("���뿪��", resizedIcon);
        setupCourse.setFont(new Font("΢���ź�", Font.PLAIN, 14));
        setupCourse.setForeground(Color.WHITE);
        setupCourse.setBackground(new Color(65, 105, 225));
        setupCourse.setCursor(new Cursor(Cursor.HAND_CURSOR));
        setupCourse.setFocusPainted(false);
        setupCourse.setBounds(600, 10, 130, 34);
        add(setupCourse);

        JLabel  courseMajor = new  JLabel("*Ϊ�ɱ༭ѡ��");
        courseMajor.setForeground(Color.black);
        courseMajor.setFont(new Font("΢���ź�", Font.PLAIN, 13));
        courseMajor.setBounds(833, 13, 95, 25);
        add(courseMajor);

        searchBt.addActionListener(e -> {
            courseData = courseDao.queryAll(courseNumber.getText(), courseName.getText(), null, teacher.getName());
            setTableData(table, scrollPane, header, courseData);
            table.getColumnModel().getColumn(9).setCellRenderer(new ModifyButton());
            table.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(new JTextField()));
            table.getColumnModel().getColumn(4).setCellEditor(new DefaultCellEditor(new JTextField()));
            table.getColumnModel().getColumn(8).setCellEditor(new DefaultCellEditor(new JTextField()));
            table.getColumnModel().getColumn(9).setCellEditor(new ModifyButtonEditor(table));
        });

        setupCourse.addActionListener(e -> setupCoursePanel(teacher.getName()));

    }

    @Override
    protected void refreshButtonClick(Teacher teacher) {
        TeacherCourseUtils.refreshCourseForCourseInfo(courseData);
    }

    static class ModifyButton implements TableCellRenderer {
        private final JButton btn;

        public ModifyButton() {
            btn = new JButton("����");
            btn.setPreferredSize(new Dimension(30, 20));
            btn.setBackground(new Color(119, 201, 126));
            btn.setBorder(null);
            btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            btn.setFocusPainted(false);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
                                                       int row, int column) {
            btn.setText("����");
            return btn;
        }
    }

    static class ModifyButtonEditor extends DefaultCellEditor {
        private final JButton btn;

        public ModifyButtonEditor(JTable table) {
            super(new JTextField());
            this.setClickCountToStart(1);
            btn = new JButton("����");
            btn.setPreferredSize(new Dimension(30, 20));
            btn.setBackground(new Color(119, 201, 126));
            btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            btn.setBorder(null);
            btn.setFocusPainted(false);
            btn.addActionListener(e -> {
                if (TeacherCourseDao.saveDataToDatabase(table)) {
                    Icon customIcon = new ImageIcon("src/main/resources/images/success.png");
                    UIUtil.showScaledIconMessage(scrollPane, "����ɹ���", "��ʾ", customIcon);
                }
                else{
                    Icon customIcon = new ImageIcon("src/main/resources/images/error.png");
                    UIUtil.showScaledIconMessage(scrollPane, "����ʧ�ܣ�", "��ʾ", customIcon);
                }
            });

        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
                btn.setText("����");
            return btn;
        }
    }
}