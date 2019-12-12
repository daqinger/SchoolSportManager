package com.example.mapper;

import com.example.model.Teacher;

public interface TeacherMapper {
    public Teacher teacherLogin(Teacher teacher);
    public void updateTeacher(Teacher teacher);
    public Integer findTeacherIdByTeacherName(String teacherName);
    public Integer updateTeacherTest(Teacher teacher);
}
