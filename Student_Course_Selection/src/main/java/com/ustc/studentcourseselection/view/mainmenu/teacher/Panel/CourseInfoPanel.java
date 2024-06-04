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
 * @author 潘义良
 */

public class CourseInfoPanel extends JPanel {
    public static JScrollPane scrollPane = new JScrollPane();

    public CourseInfoPanel(Teacher teacher) {
        initializeComponents(teacher);
        layoutComponents();
    }

    private void initializeComponents(Teacher teacher) {
        // 初始化表格列名
        String[] columnNames = {"ID", "课程编号", "课程名称", "上课时间", "专业", "地点", "教师姓名", "分数", "已选人数","容量", "创建时间", "更新时间"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        List<Course> courses = TeacherCourseDao.getCoursesForTeacher(teacher.getName());

        // 将查询结果添加到表格模型中
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