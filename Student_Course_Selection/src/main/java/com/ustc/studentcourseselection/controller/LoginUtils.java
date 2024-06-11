package com.ustc.studentcourseselection.controller;

import com.ustc.studentcourseselection.dao.StudentDao;
import com.ustc.studentcourseselection.dao.TeacherDao;
import com.ustc.studentcourseselection.model.Administrator;
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
    private static final int INDEX = 2;
    private static final String ADMIN_PASSWORD = "123456";

    public static BaseObject loginConfirm(String username, String password) {
        if (username.length() <= 1) {
            return null;
        }
        if (STUDENT_START.equals(username.substring(0, INDEX))) {
            StudentDao studentDao = new StudentDao();
            Student student = studentDao.query(username);
            if (student == null || !student.getPassword().equals(password)) {
                return null;
            }
            return student;
        } else if (TEACHER_START.equals(username.substring(0, INDEX))) {
            TeacherDao teacherDao = new TeacherDao();
            Teacher teacher = teacherDao.query(username);
            if (teacher == null || !teacher.getPassword().equals(password)) {
                return null;
            }
            return teacher;
        } else if (ADMIN_START.equals(username)) {
            if (ADMIN_PASSWORD.equals(password)) {
                return new Administrator();
            }
        }
        return null;
    }
}


