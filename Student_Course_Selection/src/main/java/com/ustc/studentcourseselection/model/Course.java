package com.ustc.studentcourseselection.model;

/**
 * number �γ̱��
 * courseName �γ�����
 * teacherName ��ʦ����
 * courseTime �Ͽ�ʱ��
 * major ����רҵ
 * location �Ͽεص�
 * score ѧ��
 * capacity ѡ������
 *
 * @author ������
 */
public class Course extends BaseObject {
    private String number;
    private String courseName;
    private String courseTime;
    private String major;
    private String location;
    private int score;
    private int capacity;

    public Course() {
    }

    public Course(int id, String number, String courseName, String courseTime, String major, String location, int score, int capacity, String createTime, String updateTime) {
        super(id, createTime, updateTime);
        this.number = number;
        this.courseName = courseName;
        this.courseTime = courseTime;
        this.major = major;
        this.location = location;
        this.score = score;
        this.capacity = capacity;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseTime() {
        return courseTime;
    }

    public void setCourseTime(String courseTime) {
        this.courseTime = courseTime;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "Course{" +
                "number='" + number + '\'' +
                ", courseName='" + courseName + '\'' +
                ", courseTime='" + courseTime + '\'' +
                ", major='" + major + '\'' +
                ", location='" + location + '\'' +
                ", score=" + score +
                ", capacity=" + capacity +
                '}';
    }
}
