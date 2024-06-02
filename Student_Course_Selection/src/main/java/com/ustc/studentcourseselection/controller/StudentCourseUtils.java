package com.ustc.studentcourseselection.controller;

import com.ustc.studentcourseselection.dao.CourseDao;
import com.ustc.studentcourseselection.dao.StudentCourseDao;
import com.ustc.studentcourseselection.model.BaseUtils;
import com.ustc.studentcourseselection.model.Course;

import java.util.List;

/**
 * @author ������
 */
public class StudentCourseUtils {
    /**
     * ѡ���߼�
     *
     * @param studentId    ѧ��id
     * @param courseNumber �γ̺�
     * @return ��ʱ���ͻ, ����1;���γ�����Ա,����2;��ѡ��ʧ��(����ʧ��),����3;��ѡ�γɹ�;����4
     */
    public static int chooseCourse(int studentId, String courseNumber) {
        int day = 5;
        int allTime = 13;
        int[][] courseTimes = new int[day][allTime];
        CourseDao courseDao = new CourseDao();
        StudentCourseDao studentCourseDao = new StudentCourseDao();
        Course thisCourse = courseDao.query(courseNumber);

        //��ȡѧ����ѡ�����пγ�,д��courseTime
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

        //��ȡ��ǰ�γ���ѡ����
        int studentNow = CourseDao.selectCount(thisCourse.getId());
        if (studentNow == thisCourse.getCapacity()) {
            return 1;
        }

        //��ѯʱ���Ƿ��ͻ
        int[][] thisTime = BaseUtils.readCourseTime(thisCourse.getCourseTime());
        for (int i = 0; i < day; i++) {
            for (int j = 0; j < allTime; j++) {
                if (courseTimes[i][j] + thisTime[i][j] > 1) {
                    return 2;
                }
            }
        }

        //ʱ�䲻��ͻ���γ�δ��Ա����ִ��ѡ���߼�
        boolean res = studentCourseDao.addCourseForStudent(studentId, thisCourse.getId());
        if (!res) {
            return 3;
        }
        return 4;
    }
}
