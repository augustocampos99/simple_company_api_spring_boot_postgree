package com.example.spring_boot_simple_company_api.dto;

import com.example.spring_boot_simple_company_api.entity.Department;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class CreateDepartmentRequestDTO {
    @NotEmpty(message = "The name is required.")
    @Size(min = 2, max = 100, message = "The length of name must be between 2 and 100 characters.")
    private String name;

    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CreateDepartmentRequestDTO(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Department toDepartment() {
        return new Department(0, this.getName(), this.getDescription());
    }
}
