package com.cummins.hibernatejpa.repository;

import com.cummins.hibernatejpa.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
