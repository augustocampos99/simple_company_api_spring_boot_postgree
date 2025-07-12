package com.example.spring_boot_simple_company_api.dto;


import com.example.spring_boot_simple_company_api.entity.Employee;
import jakarta.validation.constraints.*;

import java.util.UUID;

public class CreateEmployeeRequestDTO {

    @NotEmpty(message = "The name is required.")
    @Size(min = 2, max = 100, message = "The length of name must be between 2 and 100 characters.")
    private String name;

    @NotEmpty(message = "The cpf is required.")
    @Size(min = 11, max = 11, message = "The length of cpf must be 11 characters.")
    private String cpf;

    @NotEmpty(message = "The email is required.")
    @Email(message = "The email is not valid")
    private String email;

    @NotEmpty(message = "The phone is required.")
    @Size(min = 9, max = 15, message = "The length of phone must be between 9 and 15 characters.")
    private String phone;

    @Min(value = 1, message = "The idDepartment is required")
    private int idDepartment;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getIdDepartment() {
        return idDepartment;
    }

    public void setIdDepartment(int idDepartment) {
        this.idDepartment = idDepartment;
    }

    public Employee toEmployee() {
        return new Employee(0,
                null,
                this.getName(),
                this.getCpf(),
                this.getEmail(),
                this.getPhone(),
                this.getIdDepartment());
    }

}
