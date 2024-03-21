package com.cummins.hibernatejpa.repository;

import com.cummins.hibernatejpa.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findByCompanyName(String name);

    @Query("SELECT e FROM Employee e WHERE e.company.name = ?1")
    List<Employee> findByCompanyNameJPQL(String name);

    @Query(value = "SELECT e.* FROM employee e LEFT JOIN company c ON c.id = e.company_id WHERE c.company_name = :name", nativeQuery = true)
    List<Employee> findByCompanyNameNativeQuery(@Param("name") String name);
}
