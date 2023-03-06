package com.practice.srs.service;

import com.practice.srs.model.Student;
import com.practice.srs.repository.StudentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class StudentServiceImplTest {

    Student student;
    @Mock
    StudentRepository studentRepository;

    @InjectMocks
    StudentServiceImpl studentServiceUnderTest;

    @BeforeEach
    public void setup(){
        studentServiceUnderTest = new StudentServiceImpl();
    }

    @Test
    public void shouldCheckIfStudentIsSaved(){
        List<Student> studentList = Arrays.asList(
                new Student("Chance", "jinezachance@gmail.com")
        );
        when(studentRepository.findAll()).thenReturn(studentList);
    }


}