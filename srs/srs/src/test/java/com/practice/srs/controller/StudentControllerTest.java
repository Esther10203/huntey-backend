package com.practice.srs.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.srs.model.Student;
import com.practice.srs.service.StudentService;
import com.practice.srs.service.StudentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest({StudentController.class})
class StudentControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    StudentServiceImpl studentService;

    ObjectMapper mapper = new ObjectMapper();

    @Test
    public void endpointShouldReturnSavedStudent() throws Exception {
        Student student = new Student("chance", "jinezachance@gmail.com");
        when(studentService.saveStudent(any(Student.class))).thenReturn(student);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/student/add")
                        .content(mapper.writeValueAsString(student))
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(student.getName()));
    }


}