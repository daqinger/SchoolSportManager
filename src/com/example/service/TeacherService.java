package com.example.service;

import com.example.mapper.TeacherMapper;
import com.example.model.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class TeacherService implements ITeacherService{

    @Autowired
    @Qualifier("teacherMapper")
    private TeacherMapper teacherMapper;

    @Override
    public Teacher teacherLogin(Teacher teacher) {
        return teacherMapper.teacherLogin(teacher);
    }

    @Override
    public void updateTeacher(Teacher teacher) {
        teacherMapper.updateTeacher(teacher);
    }

    @Override
    public Integer findTeacherIdByTeacherName(String teacherName) {
        return teacherMapper.findTeacherIdByTeacherName(teacherName);
    }

    @Override
    public Integer updateTeacherTest(Teacher teacher) {
        return teacherMapper.updateTeacherTest(teacher);
    }
}
