package com.ustc.studentcourseselection.controller;

import com.ustc.studentcourseselection.dao.StudentCourseDao;
import com.ustc.studentcourseselection.model.BaseUtils;
import com.ustc.studentcourseselection.model.Course;
import com.ustc.studentcourseselection.view.mainmenu.student.panel.StudentSchedule;

import java.util.List;
import java.util.Vector;

/**
 * �γ̱��������跽��
 *
 * @author ������
 */
public class StudentScheduleUtils {
    /**
     * @param studentId ѧ��id
     * @return courseTimeList ���пγ�����ʱ����б�
     */
    public static Vector<StudentSchedule.CourseTime> getClass(int studentId) {

        StudentCourseDao studentCourseDao = new StudentCourseDao();


        // ��ȡѧ����ѡ�����пγ�
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
