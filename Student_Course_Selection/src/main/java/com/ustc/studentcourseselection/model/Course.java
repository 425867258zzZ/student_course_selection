package com.ustc.studentcourseselection.model;

/**
 * number 课程编号
 * courseName 课程名称
 * teacherName 老师名字
 * courseTime 上课时间，按照“周一（1、2），周二（3、4）”的格式,注意用中文的逗号隔开周内天数，用中文顿号隔开节数
 * major 所属专业
 * location 上课地点
 * score 学分
 * capacity 选课容量
 *
 * @author 孟梓晗
 */
public class Course extends BaseObject {
    private String number;
    private String courseName;
    private String courseTime;
    private String major;
    private String location;
    private String teacherName;
    private int score;
    private int capacity;

    public Course(int id, String number, String courseName, String courseTime, String major, String location, String teacherName, int score, int capacity, String createTime, String updateTime) {
        super(id, createTime, updateTime);
        this.number = number;
        this.courseName = courseName;
        this.courseTime = courseTime;
        this.major = major;
        this.location = location;
        this.teacherName = teacherName;
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

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
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
