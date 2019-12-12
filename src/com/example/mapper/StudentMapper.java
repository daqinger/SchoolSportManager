package com.example.mapper;

import com.example.model.Student;

import java.util.List;

public interface StudentMapper {
    public void addStudent(Student student);
    public void deleteStudentByStudentNumber(Integer studentNumber);
    public void updateStudent(Student student);
    public Student studentLogin(Student student);
    public Student findStudentByStudentNumber(Integer studentNumber);
    public List<Student> findAllStudents();//查询所有学生
    public List<Student> findStudentsBySportId(Integer sportId);
    public List<Student> findStudents(String value);
    public Integer findStudentNumberByStudentName(String studentName);
}
