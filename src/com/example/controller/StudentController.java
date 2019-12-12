package com.example.controller;

import com.example.model.Log;
import com.example.model.Student;
import com.example.model.StudentListClass;
import com.example.service.LogService;
import com.example.service.SportService;
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
public class StudentController {

    @Autowired
    @Qualifier("studentService")
    private StudentService studentService;

    @Autowired
    @Qualifier("logService")
    private LogService logService;

    @Autowired
    @Qualifier("sportService")
    private SportService sportService;


    public void addLog(String content){
        Date date = new Date();
        Log log = new Log(content,date.toString());
        logService.addLogs(log);
    }


    //注册学生信息
    @RequestMapping("/register")
    public String register(HttpServletRequest request, HttpServletResponse response, Model model)throws ServletException, IOException {
        Util.initUTF(request,response);
        int studentNumber = Integer.parseInt(request.getParameter("studentNumber"));
        String studentName = request.getParameter("studentName");
        int studentAge = Integer.parseInt(request.getParameter("studentAge"));
        String studentPassword = request.getParameter("studentPassword");
        int studentClassNumber = Integer.parseInt(request.getParameter("studentClassNumber"));

        Student student = new Student(studentNumber,studentName,studentAge,studentPassword,studentClassNumber);
        studentService.addStudent(student);

        addLog("添加学生成功:"+student.toString());

        model.addAttribute("successMessage","注册成功快去<a href=\"login.jsp\">登陆</a>吧！");
        return "success";
    }


    //获取所有学生信息
    @RequestMapping("/getAllStudents")
    @ResponseBody
    public void getAllStudents(@RequestParam(value="page",defaultValue="1")Integer page,
                               @RequestParam(value="rows",defaultValue="10")Integer rows, HttpServletResponse response) {

        List<Student> list = studentService.findAllStudents();
        int total = list.size();
        int firstIndex = (page - 1) * rows;
        int lastIndex = page * rows;

        if (lastIndex>=total){
            list = list.subList(firstIndex,total);
        }else {
            list = list.subList(firstIndex,lastIndex);
        }
        StudentListClass studentListClass = new StudentListClass(total,list);

        addLog("查询所有学生信息，共"+total+"条记录");

        Util.writeJSON2Response(studentListClass.toString(),response);
    }

    //精确查找学生数据
    @RequestMapping("/findStudents")
    public String findStudents(HttpServletRequest request, HttpServletResponse response, Model model)throws ServletException, IOException {
        Util.initUTF(request,response);
        String value = request.getParameter("value");
        String control = request.getParameter("control");
        List<Student> studentList = new ArrayList<>();

        if (control.equals("studentNumber")){
            Student student = studentService.findStudentByStudentNumber(Integer.parseInt(value));
            studentList.add(student);

            addLog("通过学号查找到一条学生数据："+student.toString());
        } else if (control.equals("studentName")){
            studentList = studentService.findStudents(value);

            addLog("通过学生姓名查找到学生数据，共"+studentList.size()+"条记录");
        } else {
            int sportId = sportService.findSportIdByName(value);
            studentList = studentService.findStudentsBySportId(sportId);

            addLog("通过体育运动名字查找到学生数据，共"+studentList.size()+"条记录");
        }


        model.addAttribute("studentList",studentList);
        return "findStudents";
    }


    //更新学生数据
    @RequestMapping("/updateStudent")
    public void updateStudent(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        Util.initUTF(request,response);
        int studentNumber = Integer.parseInt(request.getParameter("studentNumber"));
        String studentName = request.getParameter("studentName");
        int studentAge = Integer.parseInt(request.getParameter("studentAge"));
        String studentPassword = request.getParameter("studentPassword");
        int studentClassNumber = Integer.parseInt(request.getParameter("studentClassNumber"));

        Student student = studentService.findStudentByStudentNumber(studentNumber);
        student.setStudentName(studentName);
        student.setStudentAge(studentAge);
        student.setStudentPassword(studentPassword);
        student.setStudentClassNumber(studentClassNumber);
        studentService.updateStudent(student);
        addLog("更新学生信息："+student.toString());

    }


}
