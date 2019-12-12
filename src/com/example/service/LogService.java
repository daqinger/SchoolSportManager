package com.example.service;

import com.example.mapper.LogMapper;
import com.example.model.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class LogService implements ILogService {

    @Autowired
    @Qualifier("logMapper")
    private LogMapper logMapper;

    @Override
    public void addLogs(Log log) {
        logMapper.addLogs(log);
    }

    @Override
    public void deleteAllLogs() {
        logMapper.deleteAllLogs();
    }

    @Override
    public List<Log> findAllLogs() {
        return logMapper.findAllLogs();
    }
}
