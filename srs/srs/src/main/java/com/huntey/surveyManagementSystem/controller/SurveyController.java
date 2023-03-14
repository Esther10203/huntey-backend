package com.huntey.surveyManagementSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/student")
public class SurveyController {
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
