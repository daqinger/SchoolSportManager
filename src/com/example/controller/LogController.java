package com.example.controller;

import com.example.model.Log;
import com.example.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;

@Controller
public class LogController {

    @Autowired
    @Qualifier("logService")
    private LogService logService;

    public void addLog(String content){
        Date date = new Date();
        Log log = new Log(content,date.toString());
        logService.addLogs(log);
    }

    //查找日志
    @RequestMapping("findLogs")
    public String findLogs(Model model){
        List<Log> logs = logService.findAllLogs();
        model.addAttribute("logs",logs);
        model.addAttribute("logMessage","日志信息如下");

        addLog("管理员查看日志信息");

        return "findLogs";
    }

    //删除日志
    @RequestMapping("deleteLogs")
    public String deleteLogs(Model model){
        model.addAttribute("deleteLogMessage","删除成功");

        logService.deleteAllLogs();

        addLog("管理员删除日志信息");

        return "deleteLogs";
    }
}
