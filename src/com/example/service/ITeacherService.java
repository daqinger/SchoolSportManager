package com.example.service;

import com.example.model.Teacher;

public interface ITeacherService {
    public Teacher teacherLogin(Teacher teacher);
    public void updateTeacher(Teacher teacher);
    public Integer findTeacherIdByTeacherName(String teacherName);
    public Integer updateTeacherTest(Teacher teacher);
}
