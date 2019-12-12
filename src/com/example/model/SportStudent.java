package com.example.model;

public class SportStudent {
    private int sportStudentId;
    private int sportId;
    private int studentNumber;


    public SportStudent() {
    }

    public SportStudent(int sportId, int studentNumber) {
        this.sportId = sportId;
        this.studentNumber = studentNumber;
    }

    public SportStudent(int sportStudentId, int sportId, int studentNumber) {
        this.sportStudentId = sportStudentId;
        this.sportId = sportId;
        this.studentNumber = studentNumber;
    }

    @Override
    public String toString() {
        return "sportStudent{" +
                "sportStudentId=" + sportStudentId +
                ", sportId=" + sportId +
                ", studentNumber=" + studentNumber +
                '}';
    }

    public int getSportStudentId() {
        return sportStudentId;
    }

    public void setSportStudentId(int sportStudentId) {
        this.sportStudentId = sportStudentId;
    }

    public int getSportId() {
        return sportId;
    }

    public void setSportId(int sportId) {
        this.sportId = sportId;
    }

    public int getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(int studentNumber) {
        this.studentNumber = studentNumber;
    }
}
