package com.ustc.studentcourseselection.controller;

import com.ustc.studentcourseselection.dao.StudentDao;
import com.ustc.studentcourseselection.dao.TeacherDao;
import com.ustc.studentcourseselection.model.BaseObject;
import com.ustc.studentcourseselection.model.Student;
import com.ustc.studentcourseselection.model.Teacher;

/**
 * ��¼��֤
 *
 * @author ������
 * "PB" ѧ��ѧ�ſ�ͷ
 * "PT" ��ʦ���ſ�ͷ
 * "admin" ����Ա�˻���
 */
public class LoginUtils {
    private static final String STUDENT_START = "PB";
    private static final String TEACHER_START = "PT";
    private static final String ADMIN_START = "admin";

    public static BaseObject loginConfirm(String username, String password) {
        if (username.length() <= 1) {
            return null;
        }
        if (STUDENT_START.equals(username.substring(0, 2))) {
            StudentDao studentDao = new StudentDao();
            Student student = (Student) studentDao.query(username);
            if (student == null || !student.getPassword().equals(password)) {
                return null;
            }
            return student;
        } else if (TEACHER_START.equals(username.substring(0, 2))) {
            TeacherDao teacherDao = new TeacherDao();
            Teacher teacher = teacherDao.query(username);
            if (teacher == null || !teacher.getPassword().equals(password)) {
                return null;
            }
            return teacher;
        }
        return null;
    }

}


