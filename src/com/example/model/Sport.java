package com.example.model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.List;

public class Sport {
    private int sportId;//体育编号
    private String sportName;//体育名字
    private int sportNumberNeeded;//需要人数
    private int sportNumberNow;//已经报名人数
    private List <Student> studentList;//报名此运动的学生列表

    public Sport() {
    }

    public Sport(int sportId, String sportName, int sportNumberNeeded, int sportNumberNow) {
        this.sportId = sportId;
        this.sportName = sportName;
        this.sportNumberNeeded = sportNumberNeeded;
        this.sportNumberNow = sportNumberNow;
    }
    public Sport(String sportName, int sportNumberNeeded) {
        this.sportName = sportName;
        this.sportNumberNeeded = sportNumberNeeded;
        this.sportNumberNow = 0;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this, SerializerFeature.DisableCircularReferenceDetect,
                SerializerFeature.WriteDateUseDateFormat);
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
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

    public int getSportNumberNeeded() {
        return sportNumberNeeded;
    }

    public void setSportNumberNeeded(int sportNumberNeeded) {
        this.sportNumberNeeded = sportNumberNeeded;
    }

    public int getSportNumberNow() {
        return sportNumberNow;
    }

    public void setSportNumberNow(int sportNumberNow) {
        this.sportNumberNow = sportNumberNow;
    }
}
