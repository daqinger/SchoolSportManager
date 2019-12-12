package com.example.service;

import com.example.model.Sport;

import java.util.List;

public interface ISportService {
    public void addSport(Sport sport);
    public void deleteSportById(Integer sportId);
    public void updateSport(Sport sport);
    public Sport findSportBySportId(Integer sportId);
    public List<Sport> findAllSports();//查询所有运动
    public List<Sport> findSportsByStudentNumber(Integer studentNumber);
    public List<Sport> findSports(String value);//模糊查询
    public Integer findSportIdByName(String sportName);
}
