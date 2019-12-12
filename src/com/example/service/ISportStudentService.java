package com.example.service;

import com.example.model.SportStudent;

import java.util.List;

public interface ISportStudentService {
    public void addSportStudent(SportStudent sportStudent);
    public void deleteSportStudentByStudentNumber(Integer studentNumber);
    public void deleteSportStudentBySportId(Integer sportId);
    public void deleteSportStudentOnly(SportStudent sportStudent);
    public List<SportStudent> findAll();
}
