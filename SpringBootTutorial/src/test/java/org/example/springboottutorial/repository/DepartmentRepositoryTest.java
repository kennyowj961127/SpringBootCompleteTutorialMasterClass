package org.example.springboottutorial.repository;

import org.example.springboottutorial.entity.Department;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class DepartmentRepositoryTest {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    private Department department;

    @BeforeEach
    void setUp() {
        department =
                Department.builder()
                        .departmentName("IT")
                        .departmentAddress("Bangalore")
                        .departmentCode("IT-06")
                        .build();
        departmentRepository.save(department);
    }

    @Test
    public void whenFindById_thenReturnDepartment() {
        Department found = departmentRepository.findById(department.getDepartmentId()).get();
        assertThat(found).isEqualTo(department);
    }

}