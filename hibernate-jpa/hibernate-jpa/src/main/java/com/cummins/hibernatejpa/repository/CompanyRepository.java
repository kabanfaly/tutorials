package com.cummins.hibernatejpa.repository;

import com.cummins.hibernatejpa.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
