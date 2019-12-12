package com.example.controller;

import com.example.model.*;
import com.example.service.LogService;
import com.example.service.SportService;
import com.example.service.SportStudentService;
import com.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class GetExcel {
    @Autowired
    @Qualifier("logService")
    private LogService logService;

    @Autowired
    @Qualifier("sportService")
    private SportService sportService;

    @Autowired
    @Qualifier("studentService")
    private StudentService studentService;

    @Autowired
    @Qualifier("sportStudentService")
    private SportStudentService sportStudentService;

    public void addLog(String content){
        Date date = new Date();
        Log log = new Log(content,date.toString());
        logService.addLogs(log);
    }

    //获取学生数据的文件
    @RequestMapping("/getStudentExcel")
    public void getStudentExcel(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        init(request,response);
        PrintWriter out = response.getWriter();

        List<Student> studentList = studentService.findAllStudents();

        out.print("学生学号");
        out.print("\t");
        out.print("学生姓名");
        out.print("\t");
        out.print("学生年龄");
        out.print("\t");
        out.print("学生密码");
        out.print("\t");
        out.print("学生班级");
        out.print("\n");

        for (Student student:studentList){
            out.print(student.getStudentNumber());
            out.print("\t");
            out.print(student.getStudentName());
            out.print("\t");
            out.print(student.getStudentAge());
            out.print("\t");
            out.print("******");
            out.print("\t");
            out.print(student.getStudentClassNumber());
            out.print("\n");
        }

        //4.4清除缓冲
        out.flush();
        //4.5关闭PrintWriter输出对象
        out.close();

        addLog("导出学生数据");
    }


    //获取运动文件
    @RequestMapping("/getSportExcel")
    public void getSportExcel(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        init(request,response);
        PrintWriter out = response.getWriter();

        List<Sport> sportList = sportService.findAllSports();
        out.print("运动编号");
        out.print("\t");
        out.print("运动名称");
        out.print("\t");
        out.print("需要人数");
        out.print("\t");
        out.print("报名人数");
        out.print("\n");

        for (Sport sport:sportList){
            out.print(sport.getSportId());
            out.print("\t");
            out.print(sport.getSportName());
            out.print("\t");
            out.print(sport.getSportNumberNeeded());
            out.print("\t");
            out.print(sport.getSportNumberNow());
            out.print("\n");
        }

        //4.4清除缓冲
        out.flush();
        //4.5关闭PrintWriter输出对象
        out.close();

        addLog("导出运动数据");

    }

    //获取报名表
    @RequestMapping("/getApplicationExcel")
    public void getApplicationExcel(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        init(request,response);
        PrintWriter out = response.getWriter();

        List<Application> applicationList = new ArrayList<>();
        List<SportStudent> sportStudentList = sportStudentService.findAll();
        for (SportStudent sportStudent:sportStudentList){
            applicationList.add(getApplication(sportStudent.getSportId(),sportStudent.getStudentNumber()));
        }
        out.print("学生学号");
        out.print("\t");
        out.print("学生姓名");
        out.print("\t");
        out.print("学生年龄");
        out.print("\t");
        out.print("学生班级");
        out.print("\t");
        out.print("运动编号");
        out.print("\t");
        out.print("运动名称");
        out.print("\n");

        for (Application application:applicationList){
            out.print(application.getStudentNumber());
            out.print("\t");
            out.print(application.getStudentName());
            out.print("\t");
            out.print(application.getStudentAge());
            out.print("\t");
            out.print(application.getStudentClassNumber());
            out.print("\t");
            out.print(application.getSportId());
            out.print("\t");
            out.print(application.getSportName());
            out.print("\n");
        }

        //4.4清除缓冲
        out.flush();
        //4.5关闭PrintWriter输出对象
        out.close();

        addLog("导出报名数据");

    }

    //获取指定的报名信息
    private Application getApplication(int sportId,int studentNumber){
        Sport sport = sportService.findSportBySportId(sportId);
        Student student = studentService.findStudentByStudentNumber(studentNumber);
        Application application = new Application(student.getStudentNumber(),student.getStudentName(),
                student.getStudentAge(),student.getStudentClassNumber(),sport.getSportId(),sport.getSportName());
        return application;
    }

    //初始化方法，设置文件的类型
    private void init(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Util.initUTF(request,response);
        response.setContentType("application/vnd-ms.excel");
        //设置页面不缓存
        response.setHeader("Pragma","No-cache");
        response.setHeader("Cache-Control","no-cache");
        response.setDateHeader("Expires", 0);
        //在线浏览的方式：
        response.setHeader("Content-disposition","inline; filename=test1.xls");
        //下载的方式：
        //response.setHeader("Content-disposition","attachment; filename=test2.xls");
    }
}
