package com.example.spring_boot_simple_company_api.repository;

import com.example.spring_boot_simple_company_api.entity.Employee;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    @Query("SELECT e FROM Employee e ORDER BY e.id ASC")
    List<Employee> findAllPagination(Pageable pagination);

    @Query("SELECT e FROM Employee e WHERE e.guid = :guid")
    Employee findByGuid(@Param("guid") UUID guid);

    @Query("SELECT e FROM Employee e WHERE e.cpf = :guid")
    Employee findByCpf(@Param("guid") String cpf);

    @Modifying
    @Query("DELETE FROM Employee WHERE guid = :guid")
    void deleteByGuid(@Param("guid") UUID guid);

}
