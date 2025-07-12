package com.example.spring_boot_simple_company_api.repository;

import com.example.spring_boot_simple_company_api.entity.Department;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

    @Query("SELECT d FROM Department d ORDER BY d.id ASC")
    List<Department> findAllPagination(Pageable pagination);

}
