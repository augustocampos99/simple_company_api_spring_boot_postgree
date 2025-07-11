package com.example.spring_boot_simple_company_api.controller;

import com.example.spring_boot_simple_company_api.dto.CreateDepartmentRequestDTO;
import com.example.spring_boot_simple_company_api.entity.Department;
import com.example.spring_boot_simple_company_api.service.DepartmentService;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping({ "", "/" })
    public ResponseEntity<List<Department>> getAll(@RequestParam(required = false, name = "page", defaultValue = "0") int page, @RequestParam(required = false, name = "limit", defaultValue = "100") int limit) {
        try {
            var result = departmentService.getAllPagination(page, limit);
            return new ResponseEntity(result.getResult(), HttpStatus.OK);
        }
        catch(Exception e) {
            return new ResponseEntity("", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Department> getOne(@PathVariable int id) {
        try {
            var result = departmentService.getById(id);
            if(result == null) {
                return new ResponseEntity("", HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity(result.getResult(), HttpStatus.OK);
        }
        catch(Exception e) {
            return new ResponseEntity("", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping({ "", "/" })
    public ResponseEntity<Department> create(@Valid @RequestBody CreateDepartmentRequestDTO request) {
        try {
            var result = this.departmentService.create(request);
            return new ResponseEntity(result.getResult(), HttpStatus.CREATED);
        }
        catch(Exception e) {
            return new ResponseEntity("", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Department> update(@PathVariable int id, @Valid @RequestBody CreateDepartmentRequestDTO request) {
        try {
            var result = this.departmentService.update(id, request);
            return new ResponseEntity(result.getResult(), HttpStatus.OK);
        }
        catch(Exception e) {
            return new ResponseEntity("", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        try {
            this.departmentService.remove(id);
            return new ResponseEntity("OK", HttpStatus.OK);
        }
        catch(Exception e) {
            return new ResponseEntity("", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
