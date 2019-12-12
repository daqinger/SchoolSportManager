package com.example.controller;

import com.example.model.Log;
import com.example.model.Sport;
import com.example.model.SportListClass;
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
public class SportController {

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

    //老师的找到所有运动
    @RequestMapping("/getAllSports")
    @ResponseBody
    public void getAllSports(@RequestParam(value="page",defaultValue="1")Integer page,
                              @RequestParam(value="rows",defaultValue="10")Integer rows, HttpServletResponse response) {
        List<Sport> list = sportService.findAllSports();
        int total = list.size();
        int firstIndex = (page - 1) * rows;
        int lastIndex = page * rows;

        if (lastIndex>=total){
            list = list.subList(firstIndex,total);
        }else {
            list = list.subList(firstIndex,lastIndex);
        }

        SportListClass sportListClass = new SportListClass(total,list);
        addLog("查询所有运动信息，共"+total+"条记录");
        Util.writeJSON2Response(sportListClass.toString(),response);
    }

    //学生版获得运动项目，分为两种，第一种是全部，第二种是找自己的
    @RequestMapping("/studentGetSports")
    @ResponseBody
    public void studentGetSports(@RequestParam(value="page",defaultValue="1")Integer page,
                                 @RequestParam(value="rows",defaultValue="10")Integer rows,
                                 HttpServletResponse response,HttpServletRequest request) throws ServletException, IOException {
        int firstIndex = (page - 1) * rows;
        int lastIndex = page * rows;

        //学生的运动信息（需要if语句）
        Util.initUTF(request,response);
        int studentNumber = Integer.parseInt(request.getParameter("studentNumber"));
        if (studentNumber == 0){
            //此时为所有数据
            List<Sport> list = sportService.findAllSports();
            int total = list.size();
            if (lastIndex>=total){
                list = list.subList(firstIndex,total);
            }else {
                list = list.subList(firstIndex,lastIndex);
            }
            SportListClass sportListClass = new SportListClass(total,list);
            addLog("查询所有运动信息，共"+total+"条记录");
            Util.writeJSON2Response(sportListClass.toString(),response);
        }else {
            //此时为按学号查询
            List<Sport> list = sportService.findSportsByStudentNumber(studentNumber);
            int total = list.size();
            if (lastIndex>=total){
                list = list.subList(firstIndex,total);
            }else {
                list = list.subList(firstIndex,lastIndex);
            }
            SportListClass sportListClass = new SportListClass(total,list);
            addLog("通过学号查询运动信息，共"+total+"条记录");
            Util.writeJSON2Response(sportListClass.toString(),response);
        }

    }



    //添加运动
    @RequestMapping("/addSport")
    @ResponseBody
    public void addUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object result = "{\"errorMsg\":\"数据保存出错。\"}";
        Util.initUTF(request,response);

        String sportName = request.getParameter("sportName");
        int sportNumberNeeded = Integer.parseInt(request.getParameter("sportNumberNeeded"));

        Sport sport = new Sport(sportName,sportNumberNeeded);
        System.out.println(sport.toString());
        if (sport!=null){
            sportService.addSport(sport);

            addLog("添加运动信息:"+sport.toString());

            result = sport.toString();
        }
        Util.writeJSON2Response(result, response);
    }

    //编辑运动
    @RequestMapping("/editSport")
    public void editUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object result = "{\"errorMsg\":\"数据更新出错。\"}";
        Util.initUTF(request,response);
        String sportName = request.getParameter("sportName");
        int sportNumberNeeded = Integer.parseInt(request.getParameter("sportNumberNeeded"));
        int sportId = Integer.parseInt(request.getParameter("sportId"));
        Sport sport = sportService.findSportBySportId(sportId);
        sport.setSportName(sportName);
        sport.setSportNumberNeeded(sportNumberNeeded);
        if (sport!=null){
            sportService.updateSport(sport);

            addLog("更新运动信息，更新后:"+sport.toString());

            result = sport.toString();
        }
        Util.writeJSON2Response(result, response);
    }

    //删除运动
    @RequestMapping("/deleteSport")
    public void deleteUser(Integer sportId , HttpServletResponse response) {
        Object result = "{\"errorMsg\":\"数据删除出错。\"}";

        if(sportId != 0) {
            sportService.deleteSportById(sportId);
            //删除运动信息之后，相对应的报名表中与运动id相同的报名信息一并删除
            sportStudentService.deleteSportStudentBySportId(sportId);

            addLog("删除运动信息,ID:"+sportId);

            result = "{\"success\":true}";
        }
        Util.writeJSON2Response(result, response);
    }

    //精确查找运动
    @RequestMapping("findSports")
    public String findSports(HttpServletRequest request, HttpServletResponse response, Model model)throws ServletException, IOException {
        Util.initUTF(request,response);
        String value = request.getParameter("value");
        String control = request.getParameter("control");
        List<Sport> sportList = new ArrayList<>();

        if (control.equals("sportId")){
            Sport sport = sportService.findSportBySportId(Integer.parseInt(value));
            sportList.add(sport);

            addLog("通过ID查找到一条运动数据："+sport.toString());
        } else if (control.equals("sportName")){
            sportList = sportService.findSports(value);
            addLog("通过运动名称查找到运动数据，共"+sportList.size()+"条记录");
        } else {
            int studentNumber = Integer.parseInt(value);
            sportList = sportService.findSportsByStudentNumber(studentNumber);

            addLog("通过学生学号查找到运动数据，共"+sportList.size()+"条记录");
        }

        model.addAttribute("sportList",sportList);
        return "findSports";
    }


}
