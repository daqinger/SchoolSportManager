package com.example.model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
//本类是报名表，里面的属性都是sport和student类里面的
public class Application {
    private int studentNumber;
    private String studentName;
    private int studentAge;
    private int studentClassNumber;
    private int sportId;
    private String sportName;

    public Application(int studentNumber, String studentName, int studentAge, int studentClassNumber, int sportId, String sportName) {
        this.studentNumber = studentNumber;
        this.studentName = studentName;
        this.studentAge = studentAge;
        this.studentClassNumber = studentClassNumber;
        this.sportId = sportId;
        this.sportName = sportName;
    }

    public Application() {
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

    public int getSportId() {
        return sportId;
    }

    public void setSportId(int sportId) {
        this.sportId = sportId;
    }

    public String getSportName() {
        return sportName;
    }

    public void setSportName(String sportName) {
        this.sportName = sportName;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this, SerializerFeature.DisableCircularReferenceDetect,
                SerializerFeature.WriteDateUseDateFormat);
    }
}
