package com.example.controller;

import com.example.model.Log;
import com.example.model.Student;
import com.example.model.Teacher;
import com.example.service.LogService;
import com.example.service.StudentService;
import com.example.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/*
 *
 * 本控制端写登陆的servlet
 *
 * */
@Controller
public class LoginController {

    @Autowired
    @Qualifier("teacherService")
    private TeacherService teacherService;

    @Autowired
    @Qualifier("studentService")
    private StudentService studentService;

    @Autowired
    @Qualifier("logService")
    private LogService logService;


    public void addLog(String content) {
        Date date = new Date();
        Log log = new Log(content, date.toString());
        logService.addLogs(log);
    }

    //登陆验证
    @RequestMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response, Model model) throws ServletException, IOException {
        Util.initUTF(request, response);
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String control = request.getParameter("control");

//        Teacher teacherTest = new Teacher(3, "刘小青", "123456");
//        Integer a = teacherService.updateTeacherTest(teacherTest);
//        if (a > 0) {
//            System.out.println("修改成功");
//            System.out.println(a);
//        }else {
//            System.out.println("修改失败");
//            System.out.println(a);
//        }


        if (control.equals("teacher")) {
            Teacher teacher = teacherService.teacherLogin(new Teacher(name, password));
            if (teacher != null) {
                request.getSession().setAttribute("teacher", teacher);

                addLog("老师" + teacher.getTeacherName() + "登陆成功");

                return "redirect:/views/teacher.jsp";
            } else {

                addLog("老师" + teacher.getTeacherName() + "登陆失败");

                model.addAttribute("message", "老师用户名或密码错误");
                return "error";
            }
        } else {
            Student student = studentService.studentLogin(new Student(name, password));
            if (student != null) {

                addLog("学生" + student.getStudentName() + "登陆成功");

                request.getSession().setAttribute("student", student);
                return "redirect:/views/student.jsp";
            } else {

                addLog("学生" + student.getStudentName() + "登陆失败");

                model.addAttribute("message", "学生用户名或密码错误");
                return "error";
            }
        }
    }

    @RequestMapping("/teacherUnload")
    public String teacherUnload() {
        return "redirect:/login.jsp";
}


    @RequestMapping("/studentUnload")
    public String studentUnload() {
        return "redirect:/login.jsp";
    }
}
