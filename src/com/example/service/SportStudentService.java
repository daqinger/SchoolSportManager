package com.example.service;

import com.example.mapper.SportStudentMapper;
import com.example.model.SportStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SportStudentService implements ISportStudentService{

    @Autowired
    @Qualifier("sportStudentMapper")
    private SportStudentMapper sportStudentMapper;

    @Override
    public void addSportStudent(SportStudent sportStudent) {
        sportStudentMapper.addSportStudent(sportStudent);
    }

    @Override
    public void deleteSportStudentByStudentNumber(Integer studentNumber) {
        sportStudentMapper.deleteSportStudentByStudentNumber(studentNumber);
    }

    @Override
    public void deleteSportStudentBySportId(Integer sportId) {
        sportStudentMapper.deleteSportStudentBySportId(sportId);
    }

    @Override
    public void deleteSportStudentOnly(SportStudent sportStudent) {
        sportStudentMapper.deleteSportStudentOnly(sportStudent);
    }

    @Override
    public List<SportStudent> findAll() {
        return sportStudentMapper.findAll();
    }
}
