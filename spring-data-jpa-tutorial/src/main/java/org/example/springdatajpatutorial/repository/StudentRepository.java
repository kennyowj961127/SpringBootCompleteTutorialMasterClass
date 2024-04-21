package org.example.springdatajpatutorial.repository;

import jakarta.transaction.Transactional;
import org.example.springdatajpatutorial.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByFirstName(String firstName);

    List<Student> findByFirstNameContaining(String name);

    List<Student> findByLastNameNotNull();

    List<Student> findByGuardianName(String guardianName);

    List<Student> findByFirstNameAndLastName(String firstName, String lastName);

    // JPQL
    @Query("select s from Student s where s.emailId = ?1")
    Student getStudentByEmailAddress(String email);

    @Query("select s.firstName from Student s where s.emailId = ?1")
    String getStudentFirstNameByEmailAddress(String email);

    // Native Query
    @Query(
            value ="""
                    select * from tbl_student s where s.email_address = ?1
                    """,
            nativeQuery = true
    )
    Student getStudentByEmailAddressNative(String email);

    // Named Param
    @Query(
            value ="""
                    select * from tbl_student s where s.email_address = :emailId
                    """,
            nativeQuery = true
    )
    Student getStudentByEmailAddressNativeNamedParam(@Param("emailId") String email);

    @Modifying
    @Transactional
    @Query(
            value = """
                    update tbl_student set first_name = ?2 where email_address = ?1
                    """,
            nativeQuery = true
    )
    int updateStudentNameByEmailId(String emailId, String firstName);
}
