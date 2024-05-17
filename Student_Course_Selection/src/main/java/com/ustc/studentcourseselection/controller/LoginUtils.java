package com.ustc.studentcourseselection.controller;

import com.ustc.studentcourseselection.dao.StudentDao;
import com.ustc.studentcourseselection.model.BaseObject;
import com.ustc.studentcourseselection.model.Student;

/**
 * 登录验证
 *
 * @author 孟梓晗
 * "PB" 学生学号开头
 * "PT" 教师工号开头
 * "admin" 管理员账户名
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


