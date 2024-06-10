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
import static com.ustc.studentcourseselection.controller.TeacherCourseUtils.setupCourseGUI;

/**
 * øŒ≥Ã–≈œ¢≥ÈœÛ¿‡
 *
 * @author ≈À“Â¡º
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
        courseData = TeacherCourseDao.getCoursesForTeacher(teacher.getName());;

        setTableData(table, scrollPane, header, courseData);
        table.getColumnModel().getColumn(9).setCellRenderer(new ModifyButton());
        table.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(new JTextField()));
        table.getColumnModel().getColumn(4).setCellEditor(new DefaultCellEditor(new JTextField()));
        table.getColumnModel().getColumn(8).setCellEditor(new DefaultCellEditor(new JTextField()));
        table.getColumnModel().getColumn(9).setCellEditor(new ModifyButtonEditor(table, teacher));

        refreshBt.addActionListener(e -> {
            refreshButtonClick(teacher);
            setTableData(table, scrollPane, header, courseData);
            table.getColumnModel().getColumn(9).setCellRenderer(new ModifyButton());
            table.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(new JTextField()));
            table.getColumnModel().getColumn(4).setCellEditor(new DefaultCellEditor(new JTextField()));
            table.getColumnModel().getColumn(8).setCellEditor(new DefaultCellEditor(new JTextField()));
            table.getColumnModel().getColumn(9).setCellEditor(new ModifyButtonEditor(table, teacher));
        });

        JLabel courseNum = new JLabel("øŒ≥Ã∫≈:");
        courseNum.setForeground(Color.black);
        courseNum.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.PLAIN, 13));
        courseNum.setBounds(110, 13, 95, 25);
        add(courseNum);

        JLabel courseNam = new JLabel("øŒ≥Ã√˚:");
        courseNam.setForeground(Color.black);
        courseNam.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.PLAIN, 13));
        courseNam.setBounds(260, 13, 95, 25);
        add(courseNam);

        JTextField courseNumber = new JTextField();
        courseNumber.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.PLAIN, 13));
        courseNumber.setForeground(Color.black);
        courseNumber.setBounds(160, 13, 90, 25);
        courseNumber.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        courseNumber.setColumns(8);
        add(courseNumber);

        JTextField courseName = new JTextField();
        courseName.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.PLAIN, 13));
        courseName.setForeground(Color.black);
        courseName.setBounds(310, 13, 90, 25);
        courseName.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        courseName.setColumns(8);
        add(courseName);

        JButton searchBt = new JButton("À—À˜");
        searchBt.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.PLAIN, 14));
        searchBt.setForeground(Color.WHITE);
        searchBt.setBackground(new Color(0, 64, 128));
        searchBt.setCursor(new Cursor(Cursor.HAND_CURSOR));
        searchBt.setFocusPainted(false);
        searchBt.setBounds(450, 10, 80, 34);
        add(searchBt);

        ImageIcon originalIcon = new ImageIcon("src/main/resources/images/side/course_white.png");
        Image scaledIcon = originalIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(scaledIcon);
        JButton setupCourse = new JButton("…Í«Îø™øŒ", resizedIcon);
        setupCourse.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.PLAIN, 14));
        setupCourse.setForeground(Color.WHITE);
        setupCourse.setBackground(new Color(65, 105, 225));
        setupCourse.setCursor(new Cursor(Cursor.HAND_CURSOR));
        setupCourse.setFocusPainted(false);
        setupCourse.setBounds(600, 10, 130, 34);
        add(setupCourse);

        JLabel  courseMajor = new  JLabel("*Œ™ø…±‡º≠—°œÓ");
        courseMajor.setForeground(Color.black);
        courseMajor.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.PLAIN, 13));
        courseMajor.setBounds(833, 13, 95, 25);
        add(courseMajor);

        JTextField courseTeacherName = new JTextField();

        searchBt.addActionListener(e -> {
            courseData = courseDao.queryAll(courseNumber.getText(), courseName.getText(), courseMajor.getText(), courseTeacherName.getText());
            setTableData(table, scrollPane, header, courseData);
            table.getColumnModel().getColumn(9).setCellRenderer(new ModifyButton());
            table.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(new JTextField()));
            table.getColumnModel().getColumn(4).setCellEditor(new DefaultCellEditor(new JTextField()));
            table.getColumnModel().getColumn(8).setCellEditor(new DefaultCellEditor(new JTextField()));
            table.getColumnModel().getColumn(9).setCellEditor(new ModifyButtonEditor(table, teacher));
        });

        setupCourse.addActionListener(e -> {
            setupCourseGUI();
        });
    }

    @Override
    protected void refreshButtonClick(Teacher teacher) {
        TeacherCourseUtils.refreshCourseForCourseInfo(courseData);
    }

    static class ModifyButton implements TableCellRenderer {
        private final JButton btn;

        public ModifyButton() {
            btn = new JButton("±£¥Ê");
            btn.setPreferredSize(new Dimension(30, 20));
            btn.setBackground(new Color(119, 201, 126));
            btn.setBorder(null);
            btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            btn.setFocusPainted(false);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
                                                       int row, int column) {
            btn.setText("±£¥Ê");
            return btn;
        }
    }

    static class ModifyButtonEditor extends DefaultCellEditor {
        private final JButton btn;

        public ModifyButtonEditor(JTable table, Teacher teacher) {
            super(new JTextField());
            this.setClickCountToStart(1);
            btn = new JButton("±£¥Ê");
            btn.setPreferredSize(new Dimension(30, 20));
            btn.setBackground(new Color(119, 201, 126));
            btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            btn.setBorder(null);
            btn.setFocusPainted(false);
            btn.addActionListener(e -> {
            TeacherCourseUtils.saveDataToDatabase(table);
            });

        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
                btn.setText("±£¥Ê");
            return btn;
        }
    }
}