package com.ustc.studentcourseselection.controller;

import com.ustc.studentcourseselection.dao.StudentDao;
import com.ustc.studentcourseselection.model.BaseUtils;
import com.ustc.studentcourseselection.model.Student;

/**
 * @author 何昊
 */
public class StudentManagementUtils {
    /**
     * 增加学生
     *
     *
     * @return 是否成功
     */
    public static  boolean studentAdd(String number, String name, String gender, int grade, String degree, String major, String className, String createTime, String updateTime){
        StudentDao sd=new StudentDao();
        return sd.studentIdGet(number, name, gender, grade, degree, major, className, createTime, updateTime);
    }

    public static boolean studentEdict(String number, String name, String gender, int grade, String degree, String major, String className){
        StudentDao sd=new StudentDao();
        Student newStudent=new Student(sd.query(number).getId(),name,number,gender,grade,degree,major.substring(0,major.length()-2),className,sd.query(number).getPassword(),sd.query(number).getCreateTime(), BaseUtils.getTime());
        return sd.update(newStudent);
    }

    public static boolean passwordRemake(String number){
        StudentDao sd=new StudentDao();
        Student st=sd.query(number);
        Student newStudent=new Student(st.getId(),st.getName(),st.getNumber(),st.getGender(),st.getGrade(),st.getDegree(),st.getMajor(),st.getClassName(),"123456",st.getCreateTime(),BaseUtils.getTime());
        return sd.update(newStudent);
    }
}
