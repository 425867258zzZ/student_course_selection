package com.ustc.studentcourseselection.view.mainmenu.teacher.panel;

import com.ustc.studentcourseselection.dao.CourseDao;
import com.ustc.studentcourseselection.dao.TeacherCourseDao;
import com.ustc.studentcourseselection.model.Course;
import com.ustc.studentcourseselection.model.Teacher;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 * @author ������
 */

public class CourseInfoPanel extends JPanel {
    public static JScrollPane scrollPane = new JScrollPane();

    public CourseInfoPanel(Teacher teacher) {
        initializeComponents(teacher);
        layoutComponents();
    }

    private void initializeComponents(Teacher teacher) {
        // ��ʼ���������
        String[] columnNames = {"ID", "�γ̱��", "�γ�����", "�Ͽ�ʱ��", "רҵ", "�ص�", "��ʦ����", "����", "��ѡ����","����", "����ʱ��", "����ʱ��"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        List<Course> courses = TeacherCourseDao.getCoursesForTeacher(teacher.getName());

        // ����ѯ�����ӵ����ģ����
        if (courses != null) {
            for (Course course : courses) {
                int studentNow = CourseDao.selectCount(course.getId());
                Object[] rowData = {
                        course.getId(),
                        course.getNumber(),
                        course.getCourseName(),
                        course.getCourseTime(),
                        course.getMajor(),
                        course.getLocation(),
                        course.getTeacherName(),
                        course.getScore(),
                        studentNow,
                        course.getCapacity(),
                        course.getCreateTime(),
                        course.getUpdateTime()
                };
                model.addRow(rowData);
            }
        }

        JTable courseTable = new JTable(model);
        scrollPane = new JScrollPane(courseTable);
        scrollPane.getViewport().setBackground(Color.WHITE);
    }

    private void layoutComponents() {
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
    }

}