package com.ustc.studentcourseselection.dao;

import com.ustc.studentcourseselection.model.Course;

import java.util.List;

/**
 * ��ʦ��γ̺ͽ����߼�
 *
 * @author ������
 */
public class teacherCourseDao {
    /**
     * ��ʦ����һ�ſ�
     *
     * @param teacherId ��ʦid
     * @param courseId  �γ�id
     * @return �Ƿ�ɹ�
     */
    public boolean teacherAddCourse(String teacherId, String courseId) {
        return false;
    }

    /**
     * ��ʦɾ��һ�ſ�
     *
     * @param teacherId ��ʦid
     * @param courseId  �γ�id
     * @return �Ƿ�ɹ�
     */
    public boolean teacherDeleteCourse(String teacherId, String courseId) {
        return false;
    }

    /**
     * ��ȡ��ʦ�̵����пγ�
     *
     * @param teacherId ��ʦid
     * @return ���пγ�
     */
    public List<Course> getCoursesForTeacher(String teacherId) {
        return null;
    }
}
