package com.ustc.studentcourseselection.model;

/**
 * @author ������
 */
public class Teacher extends BaseObject {
    private String name;
    private String number;
    private String gender;
    private String password;
    private String department;

    public Teacher(int id, String name, String number, String gender, String department,String password, String createTime, String updateTime) {
        super(id, createTime, updateTime);
        this.name = name;
        this.number = number;
        this.gender = gender;
        this.department = department;
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

    @Override
    public String toString() {
        return "Teacher{" +
                "name='" + name +
                ", number='" + number +
                ", gender='" + gender +
                ", department='" + department +
                '}';
    }
}