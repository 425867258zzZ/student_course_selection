package com.ustc.studentcourseselection.view.mainmenu.student.panel;

import com.ustc.studentcourseselection.controller.StudentScheduleUtils;
import com.ustc.studentcourseselection.model.Student;
import com.ustc.studentcourseselection.view.UIUtil;
import com.ustc.studentcourseselection.view.mainmenu.student.AbstractStudentSchedule;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;


/**
 * @author 沈文杨
 */
public class StudentSchedule extends AbstractStudentSchedule {
    public static Vector<StudentSchedule.CourseTime> courseTimeList = new Vector<>();
    public static JScrollPane scrollPane = new JScrollPane();
    public static JTable table;
    private final Student student;

    public StudentSchedule(Student student) {
        super(); // 调用父类的构造方法
        this.student = student;
        initializeUi();
    }

    private void initializeUi() {
        // 初始化滚动面板和表格
        scrollPane = new JScrollPane();
        table = UIUtil.creatTable();
        scrollPane.setViewportView(table);
        scrollPane.setBounds(20, 50, 944, 550);
        scrollPane.getViewport().setBackground(Color.WHITE);
        add(scrollPane);

        // 初始化课程数据并设置表格数据
        updateTableData();

        // 设置刷新按钮的事件监听器
        refreshBt.addActionListener(e -> refreshButtonClick(student));
    }

    private void updateTableData() {
        // 获取课程时间和名称列表
        courseTimeList.clear();
        courseTimeList.addAll(StudentScheduleUtils.getClass(student.getId()));

        // 使用父类的 setTableData 方法更新表格数据
        setTableData(table, scrollPane, header, courseTimeList);
    }

    @Override
    protected void refreshButtonClick(Student student) {
        // 您的实现代码
        courseTimeList.clear();
        courseTimeList.addAll(StudentScheduleUtils.getClass(student.getId()));
        updateTableData();
    }

    /**
     * @author 沈文杨
     */
    public record CourseTime(String courseName, int[][] courseTime, String courseLocation) {

    }

}