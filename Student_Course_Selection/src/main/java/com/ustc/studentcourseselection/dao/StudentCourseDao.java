package com.ustc.studentcourseselection.dao;

import com.ustc.studentcourseselection.model.Course;
import com.ustc.studentcourseselection.model.Student;

import java.util.List;

/**
 * ѧ��ѡ�ε�һϵ�����ݿ⽻���߼�
 *
 * @author ������
 */
public class StudentCourseDao {
    /**
     * ѧ��ѡ��
     *
     * @param student ѧ��
     * @param course  �γ�
     */
    public void addCourseForStudent(Student student, Course course) {

    }

    /**
     * ѧ���˿�
     *
     * @param student ѧ��
     * @param course  �γ�
     */
    public void removeCourseForStudent(Student student, Course course) {

    }

    /**
     * ����ѧ��ID�����ݿ��в�ѯ��ѧ����ѡ�Ŀγ��б�
     *
     * @param student ѧ��
     * @return ��ѧ��ѡ�����пγ�
     */
    public List<Course> getCoursesForStudent(Student student) {

        return null;
    }

    /**
     * ���ݿγ�ID�����ݿ��в�ѯѡ���˸ÿγ̵�ѧ���б�
     *
     * @param course �γ�
     * @return ����ѡ�˸ÿε�ѧ��
     */
    public List<Student> getStudentsForCourse(Course course) {

        return null;
    }
}
