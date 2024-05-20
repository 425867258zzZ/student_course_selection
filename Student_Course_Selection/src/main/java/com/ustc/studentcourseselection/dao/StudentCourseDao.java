package com.ustc.studentcourseselection.dao;

import com.ustc.studentcourseselection.model.Course;
import com.ustc.studentcourseselection.model.Student;

import java.util.List;

/**
 * 学生选课的一系列数据库交互逻辑
 *
 * @author 孟梓晗
 */
public class StudentCourseDao {
    /**
     * 学生选课
     *
     * @param student 学生
     * @param course  课程
     */
    public void addCourseForStudent(Student student, Course course) {

    }

    /**
     * 学生退课
     *
     * @param student 学生
     * @param course  课程
     */
    public void removeCourseForStudent(Student student, Course course) {

    }

    /**
     * 根据学生ID从数据库中查询该学生所选的课程列表
     *
     * @param student 学生
     * @return 该学生选的所有课程
     */
    public List<Course> getCoursesForStudent(Student student) {

        return null;
    }

    /**
     * 根据课程ID从数据库中查询选择了该课程的学生列表
     *
     * @param course 课程
     * @return 所有选了该课的学生
     */
    public List<Student> getStudentsForCourse(Course course) {

        return null;
    }
}
