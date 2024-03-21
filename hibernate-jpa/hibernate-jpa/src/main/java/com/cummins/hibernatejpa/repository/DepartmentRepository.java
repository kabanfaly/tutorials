package com.cummins.hibernatejpa.repository;

import com.cummins.hibernatejpa.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
