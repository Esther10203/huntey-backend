package com.practice.srs.controller;

import com.practice.srs.model.Student;
import com.practice.srs.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping("/add")
    public Student saveStudent(@RequestBody Student student){
       return studentService.saveStudent(student);
    }

    @GetMapping("/")
    public List<Student> getAllStudents(){
       return studentService.getAllStudents();
    }
}
