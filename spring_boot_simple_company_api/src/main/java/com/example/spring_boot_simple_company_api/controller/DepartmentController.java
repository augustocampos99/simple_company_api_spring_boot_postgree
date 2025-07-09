package com.example.spring_boot_simple_company_api.controller;

import com.example.spring_boot_simple_company_api.dto.CreateDepartmentRequestDTO;
import com.example.spring_boot_simple_company_api.entity.Department;
import com.example.spring_boot_simple_company_api.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping({ "", "/" })
    public ResponseEntity<List<Department>> getAll(@RequestParam(required = false, name = "page", defaultValue = "0") int page, @RequestParam(required = false, name = "limit", defaultValue = "10") int limit) {
        var result = departmentService.getALlPagination(page, limit);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping({ "", "/" })
    public ResponseEntity<Department> create(@RequestBody CreateDepartmentRequestDTO request) {
        var result = this.departmentService.create(request);
        return ResponseEntity.ok().body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Department> update(@PathVariable int id, @RequestBody CreateDepartmentRequestDTO request) {
        var result = this.departmentService.update(id, request);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        this.departmentService.remove(id);
        return ResponseEntity.ok().body("OK");
    }
}
