package com.example.mapper;

import com.example.model.Log;

import java.util.List;

public interface LogMapper {
    public void addLogs(Log log);
    public void deleteAllLogs();
    public List<Log> findAllLogs();
}
