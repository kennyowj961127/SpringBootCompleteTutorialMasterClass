package org.example.springdatajpatutorial.repository;

import org.example.springdatajpatutorial.entity.Course;
import org.example.springdatajpatutorial.entity.CourseMaterial;
import org.example.springdatajpatutorial.entity.Student;
import org.example.springdatajpatutorial.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Pageable;

import java.util.List;

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

    @Test
    public void findAllPagination(){
        Pageable firstPageWithThreeElements = PageRequest.of(0, 3);
        Pageable secondPageWithTwoElements = PageRequest.of(1, 2);

        List<Course> courses = courseRepository.findAll(firstPageWithThreeElements).getContent();
        System.out.println("Courses = "+courses);

        long totalElements = courseRepository.findAll(firstPageWithThreeElements).getTotalElements();
        System.out.println("TotalElemnts = " + totalElements);

        long totalPages = courseRepository.findAll(firstPageWithThreeElements).getTotalPages();
        System.out.println("TotalPages = " + totalPages);

    }

    @Test
    public void findAllSorting(){
        Pageable sortByTitle = PageRequest.of(0, 2, Sort.by("title"));
        Pageable sortByCreditDesc = PageRequest.of(0, 2, Sort.by("credit").descending());

        List<Course> courses = courseRepository.findAll(sortByTitle).getContent();
        System.out.println("Courses = "+courses);

        List<Course> courses2 = courseRepository.findAll(sortByCreditDesc).getContent();
        System.out.println("Courses2 = "+courses2);
    }

    @Test
    public void findByTitleContaining(){
        Pageable firstPageTenRecords = PageRequest.of(0, 10, Sort.by("title"));
        List<Course> courses = courseRepository.findByTitleContaining("a", firstPageTenRecords).getContent();
        System.out.println("Courses = "+courses);
    }

    @Test
    public void saveCourseWithStudentAndTeacher(){
        Teacher teacher = Teacher.builder()
                .firstName("Bob")
                .lastName("Doe")
                .build();
        Student student = Student.builder()
                .firstName("Joe")
                .lastName("Hoe")
                .emailId("joe@gmail.com")
                .build();

        Course course = Course.builder()
                .title("ML")
                .credit(12)
                .teacher(teacher)
                .build();

        course.addStudent(student);

        courseRepository.save(course);
    }
}