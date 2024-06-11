package com.ustc.studentcourseselection.view.mainmenu.student.panel;

import com.ustc.studentcourseselection.controller.StudentScheduleUtils;
import com.ustc.studentcourseselection.model.Student;
import com.ustc.studentcourseselection.view.UIUtil;
import com.ustc.studentcourseselection.view.mainmenu.student.AbstractStudentSchedule;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;


/**
 * @author ������
 */
public class StudentSchedule extends AbstractStudentSchedule {
    public static Vector<StudentSchedule.CourseTime> courseTimeList = new Vector<>();
    public static JScrollPane scrollPane = new JScrollPane();
    public static JTable table;
    private final Student student;

    public StudentSchedule(Student student) {
        super(); // ���ø���Ĺ��췽��
        this.student = student;
        initializeUi();
    }

    private void initializeUi() {
        // ��ʼ���������ͱ��
        scrollPane = new JScrollPane();
        table = UIUtil.creatTable();
        scrollPane.setViewportView(table);
        scrollPane.setBounds(20, 50, 944, 550);
        scrollPane.getViewport().setBackground(Color.WHITE);
        add(scrollPane);

        // ��ʼ���γ����ݲ����ñ������
        updateTableData();

        // ����ˢ�°�ť���¼�������
        refreshBt.addActionListener(e -> refreshButtonClick(student));
    }

    private void updateTableData() {
        // ��ȡ�γ�ʱ��������б�
        courseTimeList.clear();
        courseTimeList.addAll(StudentScheduleUtils.getClass(student.getId()));

        // ʹ�ø���� setTableData �������±������
        setTableData(table, scrollPane, header, courseTimeList);
    }

    @Override
    protected void refreshButtonClick(Student student) {
        // ����ʵ�ִ���
        courseTimeList.clear();
        courseTimeList.addAll(StudentScheduleUtils.getClass(student.getId()));
        updateTableData();
    }

    /**
     * @author ������
     */
    public record CourseTime(String courseName, int[][] courseTime, String courseLocation) {

    }

}