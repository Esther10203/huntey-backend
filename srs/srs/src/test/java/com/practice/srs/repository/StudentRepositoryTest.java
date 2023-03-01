package com.practice.srs.repository;

import com.practice.srs.model.Student;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@DataJpaTest
@ExtendWith(MockitoExtension.class)
class StudentRepositoryTest {
    @Autowired
    StudentRepository studentRepositoryUnderTest;
    @BeforeEach
    public void setup(){
        List<Student> studentList = Arrays.asList(
                new Student("Chance", "jinezachance@gmail.com")
        );
        studentRepositoryUnderTest.saveAll(studentList);
    }

    @AfterEach
    public void destroyAll(){
        studentRepositoryUnderTest.deleteAll();
    }

    @Test
    @DisplayName("Should Return All the Saved Students")
    public void shouldReturnAllSavedStudents(){
        List<Student> studentList = Arrays.asList(
                new Student("Chance", "jinezachance@gmail.com"),
                new Student("Henriette", "hopehenriette@gmail.com"),
                new Student("Lina", "mwizalina@gmail.com")
        );
        Iterable<Student> allStudents = studentRepositoryUnderTest.saveAll(studentList);
        AtomicInteger count = new AtomicInteger();
        allStudents.forEach(student ->{
            count.getAndIncrement();
        });
//        AtomicInteger expected = new AtomicInteger(3);
        Assertions.assertSame(3, count.intValue(), "Count variable - Check successful");
    }
//        @Autowired
//        TestEntityManager entityManager;


//        @Test
//    public void shouldReturnAllStudents(){
//            List<Student> studentList = Arrays.asList(
//                new Student("Chance", "jinezachance@gmail.com"),
//                new Student("Henriette", "hopehenriette@gmail.com"),
//                new Student("Lina", "mwizalina@gmail.com")
//        );
//            List<Student> expected = new ArrayList<Student>();
//            studentList.forEach(student -> {
//                Student student1 = entityManager.persistAndFlush(student);
//                expected.add(student1);
//            });
//            Assertions.assertEquals(studentRepositoryUnderTest.findAll().size(), expected.size());
////            Assertions.assertEquals(expected.size(), 3);
//        }

}