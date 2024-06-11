package com.ustc.studentcourseselection.controller;

import com.ustc.studentcourseselection.dao.CourseDao;
import com.ustc.studentcourseselection.dao.TeacherCourseDao;
import com.ustc.studentcourseselection.model.BaseUtils;
import com.ustc.studentcourseselection.model.Course;

import java.util.List;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;


/**
 * The type Teacher course utils.
 *
 * @author 潘义良
 */

public class TeacherCourseUtils {

    /**
     * 刷新课程以获取课程信息.
     *
     * @param courseData the course data
     */
    public static void refreshCourseForCourseInfo(Vector<Vector<String>> courseData) {
        CourseDao courseDao = new CourseDao();
        for (Vector<String> course : courseData) {
            Course thisCourse = courseDao.query(course.getFirst());
            String studentNow = Integer.toString(CourseDao.selectCount(thisCourse.getId()));
            course.set(7, studentNow);
        }
    }

    /**
     * 将课程添加到数据库。
     *
     * @param table the table
     */
    @SuppressWarnings("rawtypes")
    public static int addCourse(DefaultTableModel table, String teacherName) {
        Vector<Vector> data = table.getDataVector();
        //不能为空
        for (Vector row : data) {
            boolean isnull = false;
            int col = 8;
            for (int i = 0; i < col; i++) {
                if (row.get(i) == null) {
                    isnull = true;
                    break;
                }
            }
            if (isnull) {
                return 1;
            }
        }

        //时间冲突
        int day = 5;
        int allTime = 13;
        int[][] courseTimes = new int[day][allTime];
        String courseTime = null;
        for (Vector row : data) {
            courseTime = (String) row.get(2);
        }

        List<Course> coursesAlreadyChosen = TeacherCourseDao.getCoursesForTeacher(teacherName);
        if (coursesAlreadyChosen != null) {
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
        }

        int[][] thisTime = new int[0][];
        if (courseTime != null) {
            thisTime = BaseUtils.readCourseTime(courseTime);
        }
        for (int i = 0; i < day; i++) {
            for (int j = 0; j < allTime; j++) {
                if (courseTimes[i][j] + thisTime[i][j] > 1) {
                    return 4;
                }
            }
        }

        //老师姓名错误
        String name = null;
        for (Vector row : data) {
            name = (String) row.get(5);
        }
        if (name != null) {
            boolean mark = name.equals(teacherName);
            if (!mark) {
                return 5;
            }
        }


        //无以上错误，运行开课逻辑
        if (TeacherCourseDao.addCourseToDatabase(table)) {
            return 3;
        } else {
            //数据库异常
            return 2;
        }
    }

}






