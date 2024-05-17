package com.ustc.studentcourseselection.controller;

import com.ustc.studentcourseselection.dao.StudentDao;
import com.ustc.studentcourseselection.model.BaseObject;
import com.ustc.studentcourseselection.model.Student;

/**
 * ��¼��֤
 *
 * @author ������
 * "PB" ѧ��ѧ�ſ�ͷ
 * "PT" ��ʦ���ſ�ͷ
 * "admin" ����Ա�˻���
 */
public class LoginUtils {
    public static BaseObject loginConfirm(String username, String password) {
        if ("PB".equals(username.substring(0, 2))) {
            StudentDao studentDao = new StudentDao();
            Student student = (Student) studentDao.query(username);
            if (student == null) {
                return null;
            }
            if (!student.getPassword().equals(password)) {
                return null;
            }
            return student;
        }
        return null;
    }
}


