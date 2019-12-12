package com.example.model;

public class Teacher {
    private int teacherId;//老师编号
    private String teacherName;//老师名字
    private String teacherPassword;//老师密码

    public Teacher() {
    }

    public Teacher(int teacherId, String teacherName, String teacherPassword) {
        this.teacherId = teacherId;
        this.teacherName = teacherName;
        this.teacherPassword = teacherPassword;
    }
    public Teacher(String teacherName, String teacherPassword) {
        this.teacherName = teacherName;
        this.teacherPassword = teacherPassword;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "teacherId=" + teacherId +
                ", teacherName='" + teacherName + '\'' +
                ", teacherPassword='" + teacherPassword + '\'' +
                '}';
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherPassword() {
        return teacherPassword;
    }

    public void setTeacherPassword(String teacherPassword) {
        this.teacherPassword = teacherPassword;
    }
}
