package org.example.springdatajpatutorial.repository;

import org.example.springdatajpatutorial.entity.Guardian;
import org.example.springdatajpatutorial.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentRepositoryTest {
    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void saveStudent(){
        Student student = Student.builder()
                .emailId("kenny@gmail.com")
                .firstName("Kenny")
                .lastName("Ong")
//                .guardianName("Kenny's Dad")
//                .guardianEmail("kennysdad@gmail.com")
//                .guardianMobile("0123456789")
                .build();

        studentRepository.save(student);
    }

    @Test
    public void saveStudentWithGuardian(){
        Guardian guardian = Guardian.builder()
                .name("John's Dad")
                .email("johnsdad@gmail.com")
                .mobile("0123456789")
                .build();

        Student student = Student.builder()
                .emailId("john@gmail.com")
                .firstName("John")
                .lastName("Ong")
                .guardian(guardian)
                .build();
        studentRepository.save(student);
    }

    @Test
    public void printAllStudents(){
        System.out.println(studentRepository.findAll());
    }

    @Test
    public void printStudentByFirstName(){
        System.out.println(studentRepository.findByFirstName("Kenny"));
    }

    @Test
    public void printStudentByFirstNameContaining(){
        System.out.println(studentRepository.findByFirstNameContaining("Ken"));
    }

    @Test
    public void printStudentByLastNameNotNull(){
        System.out.println(studentRepository.findByLastNameNotNull());
    }

    @Test
    public void printStudentByGuardianName(){
        System.out.println(studentRepository.findByGuardianName("Kenny's Dad"));
    }

    @Test
    public void printStudentByFirstNameAndLastName(){
        System.out.println(studentRepository.findByFirstNameAndLastName("John", "Ong"));
    }

    @Test
    public void printStudentByEmailId() {
        System.out.println(studentRepository.getStudentByEmailAddress("kenny@gmail.com"));
    }

    @Test
    public void printStudentFirstNameByEmailId() {
        System.out.println(studentRepository.getStudentFirstNameByEmailAddress("kenny@gmail.com"));
    }

    @Test
    public void printStudentByEmailIdNative() {
        System.out.println(studentRepository.getStudentByEmailAddressNative("kenny@gmail.com"));
    }

    @Test
    public void printStudentByEmailIdNativeNamedParam() {
        System.out.println(studentRepository.getStudentByEmailAddressNativeNamedParam("kenny@gmail.com"));
    }

    @Test
    public void updateStudentNameByEmailId() {
        studentRepository.updateStudentNameByEmailId("kenny@gmail.com", "KennyNew");
    }
}