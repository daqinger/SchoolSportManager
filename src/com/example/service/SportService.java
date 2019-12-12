package com.example.service;

import com.example.mapper.SportMapper;
import com.example.model.Sport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SportService implements ISportService {

    @Autowired
    @Qualifier("sportMapper")
    private SportMapper sportMapper;

    @Override
    public void addSport(Sport sport) {
        sportMapper.addSport(sport);
    }

    @Override
    public void deleteSportById(Integer sportId) {
        sportMapper.deleteSportById(sportId);
    }

    @Override
    public void updateSport(Sport sport) {
        sportMapper.updateSport(sport);
    }

    @Override
    public Sport findSportBySportId(Integer sportId) {
        return sportMapper.findSportBySportId(sportId);
    }

    @Override
    public List<Sport> findAllSports() {
        return sportMapper.findAllSports();
    }

    @Override
    public List<Sport> findSportsByStudentNumber(Integer studentNumber) {
        return sportMapper.findSportsByStudentNumber(studentNumber);
    }

    @Override
    public List<Sport> findSports(String value) {
        return sportMapper.findSports(value);
    }

    @Override
    public Integer findSportIdByName(String sportName) {
        return sportMapper.findSportIdByName(sportName);
    }
}
