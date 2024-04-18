package org.example.springboottutorial.repository;

import org.example.springboottutorial.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    // Spring Data JPA Query Methods
    // https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html
    Department findByDepartmentNameIgnoreCase(String departmentName);
}
