package com.example.controller;

import com.example.model.Log;
import com.example.model.Teacher;
import com.example.service.LogService;
import com.example.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Controller
public class TeacherController {

    @Autowired
    @Qualifier("teacherService")
    private TeacherService teacherService;

    @Autowired
    @Qualifier("logService")
    private LogService logService;


    public void addLog(String content){
        Date date = new Date();
        Log log = new Log(content,date.toString());
        logService.addLogs(log);
    }

    //更新学生信息
    @RequestMapping("/updateTeacher")
    public void updateTeacher(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        Util.initUTF(request,response);
        int teacherId = Integer.parseInt(request.getParameter("teacherId"));
        String teacherName = request.getParameter("teacherName");
        String teacherPassword = request.getParameter("teacherPassword");
        Teacher teacher = new Teacher(teacherId,teacherName,teacherPassword);
        teacherService.updateTeacher(teacher);
        addLog("更新老师信息："+teacher.toString());
    }
}
