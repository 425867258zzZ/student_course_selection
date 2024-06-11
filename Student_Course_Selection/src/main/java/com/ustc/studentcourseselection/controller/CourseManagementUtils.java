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
     * 返回1代表课程该时间的上课地点被占用
     * 返回2代表该时间的教师有课
     * 返回0代表无冲突
     *
     * @param courseData  课程数据
     * @param teacherName 老师名
     * @param courseTime  课程时间
     * @param location    地点
     * @param number      课程号
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
