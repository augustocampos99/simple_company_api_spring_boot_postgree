package com.example.spring_boot_simple_company_api.controller;

import com.example.spring_boot_simple_company_api.dto.CreateEmployeeRequestDTO;
import com.example.spring_boot_simple_company_api.entity.Employee;
import com.example.spring_boot_simple_company_api.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping({ "", "/" })
    public ResponseEntity<List<Employee>> getAll(@RequestParam(required = false, name = "page", defaultValue = "0") int page, @RequestParam(required = false, name = "limit", defaultValue = "100") int limit) {
        try {
            var result = employeeService.getAllPagination(page, limit);
            return new ResponseEntity(result.getResult(), HttpStatus.OK);
        }
        catch(Exception e) {
            return new ResponseEntity("Contact the IT employee...", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{guid}")
    public ResponseEntity<Employee> getOne(@PathVariable UUID guid) {
        try {
            var result = employeeService.getByGuid(guid);
            if(result == null) {
                return new ResponseEntity("", HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity(result.getResult(), HttpStatus.OK);
        }
        catch(Exception e) {
            return new ResponseEntity("Contact the IT employee...", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping({ "", "/" })
    public ResponseEntity<Employee> create(@Valid @RequestBody CreateEmployeeRequestDTO request) {
        try {
            var result = this.employeeService.create(request.toEmployee());

            if(result.isSuccess() == false) {
                return new ResponseEntity(result.getMessage(), HttpStatus.BAD_REQUEST);
            }

            return new ResponseEntity(result.getResult(), HttpStatus.CREATED);
        }
        catch(Exception e) {
            return new ResponseEntity("Contact the IT employee...", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{guid}")
    public ResponseEntity<Employee> update(@PathVariable UUID guid, @Valid @RequestBody CreateEmployeeRequestDTO request) {
        try {
            var result = this.employeeService.update(guid, request.toEmployee());
            if(result == null) {
                return new ResponseEntity("", HttpStatus.NOT_FOUND);
            }

            if(result.isSuccess() == false) {
                return new ResponseEntity(result.getMessage(), HttpStatus.BAD_REQUEST);
            }

            return new ResponseEntity(result.getResult(), HttpStatus.OK);
        }
        catch(Exception e) {
            return new ResponseEntity("Contact the IT employee...", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{guid}")
    public ResponseEntity<String> delete(@PathVariable UUID guid) {
        try {
            this.employeeService.remove(guid);
            return new ResponseEntity("OK", HttpStatus.OK);
        }
        catch(Exception e) {
            return new ResponseEntity("Contact the IT employee...", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
