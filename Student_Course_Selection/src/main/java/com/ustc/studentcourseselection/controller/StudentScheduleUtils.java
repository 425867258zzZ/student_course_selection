package com.ustc.studentcourseselection.controller;

import com.ustc.studentcourseselection.dao.StudentCourseDao;
import com.ustc.studentcourseselection.model.BaseUtils;
import com.ustc.studentcourseselection.model.Course;
import com.ustc.studentcourseselection.view.mainmenu.student.panel.StudentSchedule;

import java.util.List;
import java.util.Vector;

/**
 * 课程表制作所需方法
 *
 * @author 沈文杨
 */
public class StudentScheduleUtils {
    /**
     * @param studentId 学生id
     * @return courseTimeList 带有课程名和时间的列表
     */
    public static Vector<StudentSchedule.CourseTime> getClass(int studentId) {

        StudentCourseDao studentCourseDao = new StudentCourseDao();


        // 获取学生已选的所有课程
        List<Course> coursesAlreadyChosen = studentCourseDao.getCoursesForStudent(studentId);
        Vector<StudentSchedule.CourseTime> courseTimeList = new Vector<>();

        for (Course c : coursesAlreadyChosen) {
            int[][] time = BaseUtils.readCourseTime(c.getCourseTime());
            String courseLocation = c.getLocation();
            StudentSchedule.CourseTime courseTime = new StudentSchedule.CourseTime(c.getCourseName(), time, courseLocation);
            courseTimeList.add(courseTime);
        }

        return courseTimeList;
    }
}
