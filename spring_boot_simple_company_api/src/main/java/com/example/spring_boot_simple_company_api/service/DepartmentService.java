package com.example.spring_boot_simple_company_api.service;

import com.example.spring_boot_simple_company_api.dto.CreateDepartmentRequestDTO;
import com.example.spring_boot_simple_company_api.entity.Department;
import com.example.spring_boot_simple_company_api.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public List<Department> getALl() {
        return this.departmentRepository.findAll();
    }

    public List<Department> getALlPagination(int page, int limit) {
        return this.departmentRepository.GetAllPagination(PageRequest.of(page, limit));
    }

    public Department create(CreateDepartmentRequestDTO dto) {
        var department = new Department(0, dto.getName(), dto.getDescription());
        this.departmentRepository.save(department);
        return department;
    }

    public Department update(int id, CreateDepartmentRequestDTO dto) {
        var result = this.departmentRepository.findById(id);
        if(result.isEmpty()) {
            return null;
        }

        var department = result.get();
        department.setName(dto.getName());
        department.setDescription(dto.getDescription());
        this.departmentRepository.save(department);

        return department;
    }

    public void remove(int id) {
        this.departmentRepository.deleteById(id);
    }

}
