package org.example.springdatajpatutorial.repository;

import org.example.springdatajpatutorial.entity.Course;
import org.example.springdatajpatutorial.entity.CourseMaterial;
import org.example.springdatajpatutorial.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseRepositoryTest {
    @Autowired
    private CourseRepository courseRepository;

    @Test
    public void printCourses(){
        System.out.println(courseRepository.findAll());
    }

    @Test
    public void saveCourseWithTeacher(){

        Course course = Course.builder()
                .title("AI")
                .credit(6)
                .teacher(Teacher.builder().firstName("Sign").lastName("Doe").build())
                .build();
        courseRepository.save(course);
    }
}