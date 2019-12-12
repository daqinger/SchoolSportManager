package com.example.model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.List;

public class Student {
    private int studentNumber;//学号
    private String studentName;//学生姓名
    private int studentAge;//学生年龄
    private String studentPassword;//学生密码
    private int studentClassNumber;//学生班级号
    private List <Sport> sportList;//学生所选的体育运动列表

    public Student() {
    }

    public Student(int studentNumber, String studentName, int studentAge, String studentPassword, int studentClassNumber) {
        this.studentNumber = studentNumber;
        this.studentName = studentName;
        this.studentAge = studentAge;
        this.studentPassword = studentPassword;
        this.studentClassNumber = studentClassNumber;
    }
    public Student(String studentName,String studentPassword) {
        this.studentName = studentName;
        this.studentPassword = studentPassword;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this, SerializerFeature.DisableCircularReferenceDetect,
                SerializerFeature.WriteDateUseDateFormat);
    }

    public int getStudentAge() {
        return studentAge;
    }

    public void setStudentAge(int studentAge) {
        this.studentAge = studentAge;
    }

    public int getStudentClassNumber() {
        return studentClassNumber;
    }

    public void setStudentClassNumber(int studentClassNumber) {
        this.studentClassNumber = studentClassNumber;
    }

    public int getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(int studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentPassword() {
        return studentPassword;
    }

    public void setStudentPassword(String studentPassword) {
        this.studentPassword = studentPassword;
    }

    public List<Sport> getSportList() {
        return sportList;
    }

    public void setSportList(List<Sport> sportList) {
        this.sportList = sportList;
    }
}
