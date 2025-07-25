package com.example.spring_boot_simple_company_api.service;

import com.example.spring_boot_simple_company_api.dto.BaseResult;
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

    public BaseResult<List<Department>> getAllPagination(int page, int limit) throws Exception {
        try {
            if(limit > 100) {
                limit = 100;
            }

            var result = this.departmentRepository.findAllPagination(PageRequest.of(page, limit));
            return new BaseResult<List<Department>>(true, result, "");
        }
        catch(Exception e) {
            throw new Exception("Error database: " + e.getMessage());
        }
    }

    public BaseResult<Department> getById(int id) throws Exception {
        try {
            var result = this.departmentRepository.findById(id);
            if(result.isEmpty()) {
                return null;
            }

            return new BaseResult<Department>(true, result.get(), "");
        }
        catch(Exception e) {
            throw new Exception("Error database: " + e.getMessage());
        }
    }

    public BaseResult<Department> create(Department department) throws Exception {
        try {
            this.departmentRepository.save(department);
            return new BaseResult<Department>(true, department, "");
        }
        catch(Exception e) {
            throw new Exception("Error database: " + e.getMessage());
        }
    }

    public BaseResult<Department> update(int id, Department department) throws Exception {
        try {
            var result = this.departmentRepository.findById(id);
            if(result.isEmpty()) {
                return null;
            }

            var departmentResult = result.get();
            departmentResult.setName(department.getName());
            departmentResult.setDescription(department.getDescription());
            this.departmentRepository.save(departmentResult);

            return new BaseResult<Department>(true, departmentResult, "");
        }
        catch(Exception e) {
            throw new Exception("Error database: " + e.getMessage());
        }
    }

    public void remove(int id) throws Exception {
        try {
            this.departmentRepository.deleteById(id);
        }
        catch(Exception e) {
            throw new Exception("Error database: " + e.getMessage());
        }
    }

}
