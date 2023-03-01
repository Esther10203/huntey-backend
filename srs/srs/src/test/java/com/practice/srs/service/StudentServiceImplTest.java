package com.practice.srs.service;

import com.practice.srs.model.Student;
import com.practice.srs.repository.StudentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

class StudentServiceImplTest {

    Student student;
    @Mock
    StudentRepository studentRepository;

    @InjectMocks
    StudentService studentService;

    @BeforeEach
    public void setup(){
        student = new Student("Chance", "jinezachance@gmail.com");
    }

    @Test
    public void shouldCheckIfStudentIsSaved(){
//        when(studentService.saveStudent(student));
    }


}