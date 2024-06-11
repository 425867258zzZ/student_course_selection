package com.ustc.studentcourseselection.controller;

import com.ustc.studentcourseselection.dao.CourseDao;
import com.ustc.studentcourseselection.model.BaseUtils;
import com.ustc.studentcourseselection.model.Course;

import java.util.Vector;

/**
 * @author 13901
 */
public class CourseManagementUtils {
    public static boolean courseAdd(String number, String courseName, String courseTime, String major, String location, String teacherName, int score, int capacity, String createTime, String updateTime) {
        CourseDao cd = new CourseDao();
        return cd.courseIdGet(number, courseName, courseTime, major, location, teacherName, score, capacity, createTime, updateTime);
    }


    public static boolean courseEdict(String number, String courseName, String courseTime, String major, String location, String teacherName, int score, int capacity) {
        CourseDao cd = new CourseDao();
        Course newCourse = new Course(cd.query(number).getId(), number, courseName, courseTime, major, location, teacherName, score, capacity, cd.query(number).getCreateTime(), BaseUtils.getTime());
        return cd.update(newCourse);
    }

    /**
     * ����1����γ̸�ʱ����Ͽεص㱻ռ��
     * ����2�����ʱ��Ľ�ʦ�п�
     * ����0�����޳�ͻ
     *
     * @param courseData  �γ�����
     * @param teacherName ��ʦ��
     * @param courseTime  �γ�ʱ��
     * @param location    �ص�
     * @param number      �γ̺�
     * @return the int
     */
    public static int check(Vector<Vector<String>> courseData, String teacherName, String courseTime, String location, String number) {
        for (Vector<String> courseDatum : courseData) {
            if (!courseDatum.getFirst().equals(number)) {
                if (courseDatum.get(5).equals(teacherName) && courseDatum.get(2).equals(courseTime)) {
                    return 2;
                }
                if ((courseDatum.get(4).equals(location) && courseDatum.get(2).equals(courseTime))) {
                    return 1;
                }
            }
        }
        return 0;
    }
}
