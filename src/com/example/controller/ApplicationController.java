package com.example.controller;

import com.example.model.*;
import com.example.service.LogService;
import com.example.service.SportService;
import com.example.service.SportStudentService;
import com.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class ApplicationController {

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

    //找到所有报名表
    @RequestMapping("/findAllStudentSport")
    @ResponseBody
    public void findAllStudentSport(@RequestParam(value="page",defaultValue="1")Integer page,
                                    @RequestParam(value="rows",defaultValue="10")Integer rows, HttpServletResponse response) {

        List<Application> applicationList = new ArrayList<>();
        List<SportStudent> sportStudentList = sportStudentService.findAll();
        for (SportStudent sportStudent:sportStudentList){
            applicationList.add(getApplication(sportStudent.getSportId(),sportStudent.getStudentNumber()));
        }

        int total = applicationList.size();
        int firstIndex = (page - 1) * rows;
        int lastIndex = page * rows;
        if (lastIndex>=total){
            applicationList = applicationList.subList(firstIndex,total);
        }else {
            applicationList = applicationList.subList(firstIndex,lastIndex);
        }

        ApplicationListClass applicationListClass = new ApplicationListClass(total,applicationList);
        addLog("查询所有报名情况，共"+total+"条记录");

        Util.writeJSON2Response(applicationListClass.toString(),response);
    }

    //获取指定报名表
    private Application getApplication(int sportId,int studentNumber){
        Sport sport = sportService.findSportBySportId(sportId);
        Student student = studentService.findStudentByStudentNumber(studentNumber);
        Application application = new Application(student.getStudentNumber(),student.getStudentName(),
                student.getStudentAge(),student.getStudentClassNumber(),sport.getSportId(),sport.getSportName());
        return application;
    }

    //精确查询报名表
    @RequestMapping("/findStudentSport")
    public String findStudentSport(HttpServletRequest request, HttpServletResponse response, Model model)throws ServletException, IOException {
        Util.initUTF(request,response);
        String value = request.getParameter("value");
        String control = request.getParameter("control");
        List<Application> applicationList = new ArrayList<>();
        List<SportStudent> sportStudentList = sportStudentService.findAll();

        if (control.equals("studentNumber")){
            for (SportStudent sportStudent:sportStudentList){
                if (sportStudent.getStudentNumber() == Integer.parseInt(value)){
                    applicationList.add( getApplication(sportStudent.getSportId(),sportStudent.getStudentNumber()));
                }
            }

            addLog("通过学号查找到报名信息,共"+applicationList.size()+"条记录");
        } else if (control.equals("studentName")){
            int studentNumber = studentService.findStudentNumberByStudentName(value);
            for (SportStudent sportStudent:sportStudentList){
                if (sportStudent.getStudentNumber() == studentNumber){
                    applicationList.add( getApplication(sportStudent.getSportId(),sportStudent.getStudentNumber()));
                }
            }
            addLog("通过学生姓名查找到报名信息,共"+applicationList.size()+"条记录");
        } else {
            for (SportStudent sportStudent:sportStudentList){
                if (sportStudent.getSportId() == Integer.parseInt(value)){
                    applicationList.add( getApplication(sportStudent.getSportId(),sportStudent.getStudentNumber()));
                }
            }
            addLog("通过运动编号查找到报名信息,共"+applicationList.size()+"条记录");
        }

        model.addAttribute("applicationList",applicationList);

        return "findStudentSport";
    }

    //报名
    @RequestMapping("/signSport")
    public void signSport(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object result = "{\"errorMsg\":\"报名出错\"}";
        Util.initUTF(request,response);
        int sportId = Integer.parseInt(request.getParameter("sportId"));
        int studentNumber = Integer.parseInt(request.getParameter("studentNumber"));

        //遍历报名表看看我是否已经报名
        boolean flag = false;
        List<SportStudent> sportStudentList = sportStudentService.findAll();
        for (SportStudent sportStudent:sportStudentList){
            if (sportStudent.getStudentNumber()==studentNumber&&sportStudent.getSportId()==sportId){
                flag = true;
                //已经报名
            }
        }

        Sport sport = sportService.findSportBySportId(sportId);
        if (sport.getSportNumberNeeded() <= sport.getSportNumberNow()){
                        //当运动已经满了
            result = "{\"errorMsg\":\"名额已满，报名失败\"}";
            addLog("学号为"+studentNumber+
                    "报名参加编号"+sportId+"的运动时，名额已满，报名失败");
        }else if (flag){
            result = "{\"errorMsg\":\"已经报名该项目\"}";
            addLog("学号为"+studentNumber+
                    "报名参加编号"+sportId+"的运动时，已经报名此项目，报名失败");
        }else {
            //如果运动所需要的人大于现在的人，即还有报名余额
            sport.setSportNumberNow(sport.getSportNumberNow()+1);
            sportService.updateSport(sport);
            SportStudent sportStudent = new SportStudent(sportId,studentNumber);
            sportStudentService.addSportStudent(sportStudent);

            addLog("学号为"+sportStudent.getStudentNumber()+
                    "报名参加了编号为"+sportStudent.getSportId()+"的运动");
            result = sport.toString();
        }

//        if (sport.getSportNumberNeeded() > sport.getSportNumberNow()){
//            //如果运动所需要的人大于现在的人，即还有报名余额
//            sport.setSportNumberNow(sport.getSportNumberNow()+1);
//            sportService.updateSport(sport);
//            SportStudent sportStudent = new SportStudent(sportId,studentNumber);
//            sportStudentService.addSportStudent(sportStudent);
//
//            addLog("学号为"+sportStudent.getStudentNumber()+
//                    "报名参加了编号为"+sportStudent.getSportId()+"的运动");
//            result = sport.toString();
//        } else {
//            //当运动已经满了
//            result = "{\"errorMsg\":\"名额已满，报名失败\"}";
//            addLog("学号为"+studentNumber+
//                    "报名参加编号"+sportId+"的运动时，名额已满，报名失败");
//        }
        Util.writeJSON2Response(result, response);
    }

    //退出报名
    @RequestMapping("/unSignSport")
    public void unSignSport(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object result = "{\"errorMsg\":\"退出报名出错\"}";
        Util.initUTF(request,response);
        int sportId = Integer.parseInt(request.getParameter("sportId"));
        int studentNumber = Integer.parseInt(request.getParameter("studentNumber"));
        SportStudent sportStudent = new SportStudent(sportId,studentNumber);
        if (sportStudent!=null) {
            sportStudentService.deleteSportStudentOnly(sportStudent);
            Sport sport = sportService.findSportBySportId(sportId);
            sport.setSportNumberNow(sport.getSportNumberNow()-1);
            sportService.updateSport(sport);
            result = sport.toString();
            addLog("学号为"+sportStudent.getStudentNumber()+
                    "退出了编号为"+sportStudent.getSportId()+"的运动");
        } else {
            result = "{\"errorMsg\":\"退出报名失败\"}";
            addLog("学号为"+studentNumber+ "退出报名失败");
        }
        Util.writeJSON2Response(result, response);
    }

}
