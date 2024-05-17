package com.ustc.studentcourseselection.model;

/**
 * id 主键 不可修改
 * name 姓名 不可修改
 * number 学号，不可修改
 * gender 性别 不可修改
 * grade 年级 20xx级 不可修改
 * degree 学历 本科/硕士/博士/ 默认为本科 可修改
 * major 专业 可修改sss
 * className 班级名称 可修改
 * password 密码 初始设置为123456，可修改
 *
 * @author 孟梓晗
 */
public class Student extends BaseObject {

    private String name;
    private String number;
    private String gender;
    private int grade;
    private String degree;
    private String major;
    private String className;
    private String password;

    public Student() {
    }

    public Student(int id, String name, String number, String gender, int grade, String degree, String major, String className, String password, String creatTime, String updateTime) {
        super(id, creatTime, updateTime);
        this.name = name;
        this.number = number;
        this.gender = gender;
        this.grade = grade;
        this.degree = degree;
        this.major = major;
        this.className = className;
        this.password = password;
    }


    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name +
                ", number='" + number +
                ", gender='" + gender +
                ", grade=" + grade +
                ", degree='" + degree +
                ", major='" + major +
                ", className='" + className +
                '}';
    }
}
