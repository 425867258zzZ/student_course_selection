package com.ustc.studentcourseselection.model;

/**
 * id ���� �����޸�
 * name ���� �����޸�
 * number ѧ�ţ������޸�
 * gender �Ա� �����޸�
 * grade �꼶 20xx�� �����޸�
 * degree ѧ�� ����/˶ʿ/��ʿ/ Ĭ��Ϊ���� ���޸�
 * major רҵ ���޸�sss
 * className �༶���� ���޸�
 * password ���� ��ʼ����Ϊ123456�����޸�
 *
 * @author ������
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
