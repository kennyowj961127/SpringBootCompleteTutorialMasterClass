package org.example.springdatajpatutorial.repository;

import org.example.springdatajpatutorial.entity.Course;
import org.example.springdatajpatutorial.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TeacherRepositoryTest {
    @Autowired
    private TeacherRepository teacherRepository;

    @Test
    public void saveTeacher(){
        Course course = Course.builder()
                .title("Chemistry")
                .credit(6)
                .build();

        Course course2 = Course.builder()
                .title("Additional Chemistry")
                .credit(6)
                .build();

         Teacher teacher = Teacher.builder()
                 .firstName("The")
                 .lastName("Teacher")
//                 .courses(List.of(course, course2))
                 .build();
         teacherRepository.save(teacher);
    }
}