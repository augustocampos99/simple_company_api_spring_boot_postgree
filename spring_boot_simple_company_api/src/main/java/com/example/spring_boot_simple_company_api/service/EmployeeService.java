package com.example.spring_boot_simple_company_api.service;

import com.example.spring_boot_simple_company_api.dto.BaseResult;
import com.example.spring_boot_simple_company_api.entity.Employee;
import com.example.spring_boot_simple_company_api.repository.DepartmentRepository;
import com.example.spring_boot_simple_company_api.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private DepartmentRepository departmentRepository;

    public BaseResult<List<Employee>> getAllPagination(int page, int limit) throws Exception {
        try {
            if(limit > 100) {
                limit = 100;
            }

            var result = this.employeeRepository.findAllPagination(PageRequest.of(page, limit));
            return new BaseResult<List<Employee>>(true, result, "");
        }
        catch(Exception e) {
            throw new Exception("Error database: " + e.getMessage());
        }
    }

    public BaseResult<Employee> getByGuid(UUID guid) throws Exception {
        try {
            var result = this.employeeRepository.findByGuid(guid);
            if(result == null) {
                return null;
            }

            return new BaseResult<Employee>(true, result, "");
        }
        catch(Exception e) {
            throw new Exception("Error database: " + e.getMessage());
        }
    }

    public BaseResult<Employee> create(Employee employee) throws Exception {
        try {
            var departmentResult = this.departmentRepository.findById(employee.getIdDepartment());
            if(departmentResult.isEmpty()) {
                return new BaseResult<Employee>(false, null, "Department not found");
            }

            var employeeResult = this.employeeRepository.findByCpf(employee.getCpf());
            if(employeeResult != null) {
                return new BaseResult<Employee>(false, null, "CPF already exists in the database");
            }

            employee.setGuid(UUID.randomUUID());
            this.employeeRepository.save(employee);
            return new BaseResult<Employee>(true, employee, "");
        }
        catch(Exception e) {
            throw new Exception("Error database: " + e.getMessage());
        }
    }

    public BaseResult<Employee> update(UUID guid, Employee employee) throws Exception {
        try {
            var employeeResult = this.employeeRepository.findByGuid(guid);
            if(employeeResult == null) {
                return null;
            }

            var departmentResult = this.departmentRepository.findById(employee.getIdDepartment());
            if(departmentResult.isEmpty()) {
                return new BaseResult<Employee>(false, null, "Department not found");
            }

            employeeResult.setName(employee.getName());
            employeeResult.setEmail(employee.getEmail());
            employeeResult.setPhone(employee.getPhone());
            employeeResult.setIdDepartment(employee.getIdDepartment());
            this.employeeRepository.save(employeeResult);

            return new BaseResult<Employee>(true, employeeResult, "");
        }
        catch(Exception e) {
            throw new Exception("Error database: " + e.getMessage());
        }
    }

    @Transactional
    public void remove(UUID guid) throws Exception {
        try {
            this.employeeRepository.deleteByGuid(guid);
        }
        catch(Exception e) {
            throw new Exception("Error database: " + e.getMessage());
        }
    }

}
