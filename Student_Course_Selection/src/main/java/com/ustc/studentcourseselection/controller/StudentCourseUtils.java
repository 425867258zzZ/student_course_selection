package com.ustc.studentcourseselection.controller;

import com.ustc.studentcourseselection.dao.CourseDao;
import com.ustc.studentcourseselection.dao.StudentCourseDao;
import com.ustc.studentcourseselection.model.BaseUtils;
import com.ustc.studentcourseselection.model.Course;
import com.ustc.studentcourseselection.model.Student;

import java.util.List;
import java.util.Vector;

import static com.ustc.studentcourseselection.dao.CourseDao.selectCount;

/**
 * @author 孟梓晗
 */
public class StudentCourseUtils {
    /**
     * 选课逻辑
     *
     * @param studentId    学生id
     * @param courseNumber 课程号
     * @return 若时间冲突, 返回1;若课程已满员,返回2;若选课失败(连接失败),返回3;若选课成功;返回4
     */
    public static int chooseCourse(int studentId, String courseNumber) {
        int day = 5;
        int allTime = 13;
        int[][] courseTimes = new int[day][allTime];
        CourseDao courseDao = new CourseDao();
        StudentCourseDao studentCourseDao = new StudentCourseDao();
        Course thisCourse = courseDao.query(courseNumber);

        //获取学生已选的所有课程,写入courseTime
        List<Course> coursesAlreadyChosen = studentCourseDao.getCoursesForStudent(studentId);
        for (Course c : coursesAlreadyChosen) {
            int[][] time = BaseUtils.readCourseTime(c.getCourseTime());
            for (int i = 0; i < day; i++) {
                for (int j = 0; j < allTime; j++) {
                    if (courseTimes[i][j] == 0) {
                        courseTimes[i][j] = time[i][j];
                    }
                }
            }
        }

        //获取当前课程已选人数
        int studentNow = CourseDao.selectCount(thisCourse.getId());
        if (studentNow == thisCourse.getCapacity()) {
            return 1;
        }

        //查询时间是否冲突
        int[][] thisTime = BaseUtils.readCourseTime(thisCourse.getCourseTime());
        for (int i = 0; i < day; i++) {
            for (int j = 0; j < allTime; j++) {
                if (courseTimes[i][j] + thisTime[i][j] > 1) {
                    return 2;
                }
            }
        }

        //时间不冲突，课程未满员，则执行选课逻辑
        boolean res = studentCourseDao.addCourseForStudent(studentId, thisCourse.getId());
        if (!res) {
            return 3;
        }
        return 4;
    }

    public static boolean quitCourse(int studentId, String courseNumber) {
        CourseDao courseDao = new CourseDao();
        StudentCourseDao studentCourseDao = new StudentCourseDao();
        Course thisCourse = courseDao.query(courseNumber);

        return studentCourseDao.removeCourseForStudent(studentId, thisCourse.getId());
    }

    public static void refreshCourseForChooseCourse(Vector<Vector<String>> courseData) {
        CourseDao courseDao = new CourseDao();
        for (Vector<String> course : courseData) {
            Course thisCourse = courseDao.query(course.getFirst());
            String studentNow = Integer.toString(CourseDao.selectCount(thisCourse.getId()));
            course.set(7, studentNow);
        }
    }

    public static void refreshCourseForCourseChosen(Vector<Vector<String>> courseData, Student student) {
        courseData.clear();
        StudentCourseDao studentCourseDao = new StudentCourseDao();
        List<Course> courses = studentCourseDao.getCoursesForStudent(student.getId());
        for (Course course : courses) {
            Vector<String> rowData = new Vector<>();
            rowData.add(course.getNumber());
            rowData.add(course.getCourseName());
            rowData.add(course.getCourseTime());
            rowData.add(course.getMajor() + "学院");
            rowData.add(course.getLocation());
            rowData.add(course.getTeacherName());
            rowData.add(Integer.toString(course.getScore()));
            rowData.add(Integer.toString(selectCount(course.getId())));
            rowData.add(Integer.toString(course.getCapacity()));
            courseData.add(rowData);
        }
    }
}
