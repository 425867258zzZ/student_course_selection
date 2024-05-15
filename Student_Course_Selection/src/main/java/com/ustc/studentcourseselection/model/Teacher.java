package com.ustc.studentcourseselection.model;

/**
 *
 * @author ≈À“Â¡º
 */
public class Teacher extends BaseObject {
    private String name;
    private String number;
    private String gender;
    //lalal
    private String course;
    private String password;
    private String department;

    public Teacher(int id, String name, String number, String gender, String department ,String course, String password, String createTime, String updateTime) {
        super(id, createTime, updateTime);
        this.name = name;
        this.number = number;
        this.gender = gender;
        this.department = department;
        this.course = course;
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

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
    public String getCreateTime() { return createTime ;}
    public String getUpdateTime() { return updateTime ; }
}