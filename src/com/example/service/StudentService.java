package com.example.service;

import com.example.mapper.StudentMapper;
import com.example.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StudentService implements IStudentService {

    @Autowired
    @Qualifier("studentMapper")
    private StudentMapper studentMapper;

    @Override
    public void addStudent(Student student) {
        studentMapper.addStudent(student);
    }

    @Override
    public void deleteStudentByStudentNumber(Integer studentNumber) {
        studentMapper.deleteStudentByStudentNumber(studentNumber);
    }

    @Override
    public void updateStudent(Student student) {
        studentMapper.updateStudent(student);
    }

    @Override
    public Student studentLogin(Student student) {
        return studentMapper.studentLogin(student);
    }

    @Override
    public Student findStudentByStudentNumber(Integer studentNumber) {
        return studentMapper.findStudentByStudentNumber(studentNumber);
    }

    @Override
    public List<Student> findAllStudents() {
        return studentMapper.findAllStudents();
    }

    @Override
    public List<Student> findStudentsBySportId(Integer sportId) {
        return studentMapper.findStudentsBySportId(sportId);
    }

    @Override
    public List<Student> findStudents(String value) {
        return studentMapper.findStudents(value);
    }

    @Override
    public Integer findStudentNumberByStudentName(String studentName) {
        return studentMapper.findStudentNumberByStudentName(studentName);
    }
}
