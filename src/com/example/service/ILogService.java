package com.example.service;

import com.example.model.Log;

import java.util.List;

public interface ILogService {
    public void addLogs(Log log);
    public void deleteAllLogs();
    public List<Log> findAllLogs();
}
