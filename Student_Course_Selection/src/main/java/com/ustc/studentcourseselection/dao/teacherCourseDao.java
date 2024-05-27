package com.ustc.studentcourseselection.dao;

import com.ustc.studentcourseselection.model.Course;

import java.util.List;

/**
 * 教师与课程和交互逻辑
 *
 * @author 孟梓晗
 */
public class teacherCourseDao {
    /**
     * 老师增加一门课
     *
     * @param teacherId 老师id
     * @param courseId  课程id
     * @return 是否成功
     */
    public boolean teacherAddCourse(String teacherId, String courseId) {
        return false;
    }

    /**
     * 老师删除一门课
     *
     * @param teacherId 老师id
     * @param courseId  课程id
     * @return 是否成功
     */
    public boolean teacherDeleteCourse(String teacherId, String courseId) {
        return false;
    }

    /**
     * 获取老师教的所有课程
     *
     * @param teacherId 老师id
     * @return 所有课程
     */
    public List<Course> getCoursesForTeacher(String teacherId) {
        return null;
    }
}
