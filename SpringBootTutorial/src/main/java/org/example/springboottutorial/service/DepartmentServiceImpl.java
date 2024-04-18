package org.example.springboottutorial.service;

import org.example.springboottutorial.entity.Department;
import org.example.springboottutorial.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService{

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public List<Department> fetchDepartmentList() {
        return departmentRepository.findAll();
    }

    @Override
    public Department fetchDepartmentById(Long departmentId) {
        Optional<Department> department = departmentRepository.findById(departmentId);
        return department.orElse(null);
    }

    @Override
    public void deleteDepartmentById(Long departmentId) {
        departmentRepository.deleteById(departmentId);
    }

    @Override
    public Department updateDepartment(Long departmentId, Department department) {
        Optional<Department> dep = departmentRepository.findById(departmentId);
        if(dep.isPresent()){
            Department depDB = dep.get();
            // check if the department name is not null and not empty
            if(department.getDepartmentName() != null && !department.getDepartmentName().isEmpty()){
                depDB.setDepartmentName(department.getDepartmentName());
            }
            if(department.getDepartmentAddress() != null && !department.getDepartmentAddress().isEmpty()){
                depDB.setDepartmentAddress(department.getDepartmentAddress());
            }
            if(department.getDepartmentCode() != null && !department.getDepartmentCode().isEmpty()){
                depDB.setDepartmentCode(department.getDepartmentCode());
            }
            return departmentRepository.save(depDB);
        }
        return null;
    }

    @Override
    public Department fetchDepartmentByName(String departmentName) {
        return departmentRepository.findByDepartmentNameIgnoreCase(departmentName);
    }
}
