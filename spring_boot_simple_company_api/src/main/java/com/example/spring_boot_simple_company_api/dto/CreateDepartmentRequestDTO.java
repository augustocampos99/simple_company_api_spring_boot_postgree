package com.example.spring_boot_simple_company_api.dto;

import jakarta.persistence.Column;

public class CreateDepartmentRequestDTO {
    @Column(name = "name")
    private String name;

    @Column(name = "description")
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
}
