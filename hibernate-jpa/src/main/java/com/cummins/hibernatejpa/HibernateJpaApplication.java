package com.cummins.hibernatejpa;

import com.cummins.hibernatejpa.entity.Address;
import com.cummins.hibernatejpa.entity.Company;
import com.cummins.hibernatejpa.entity.Department;
import com.cummins.hibernatejpa.entity.Employee;
import com.cummins.hibernatejpa.repository.AddressRepository;
import com.cummins.hibernatejpa.repository.CompanyRepository;
import com.cummins.hibernatejpa.repository.DepartmentRepository;
import com.cummins.hibernatejpa.repository.EmployeeRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@Log4j2
public class HibernateJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(HibernateJpaApplication.class, args);
	}


	@Bean
	public CommandLineRunner commandLineRunner(EmployeeRepository employeeRepository, CompanyRepository companyRepository, DepartmentRepository departmentRepository, AddressRepository addressRepository) {
		return args -> {
			Address address = new Address();
			address.setStreetName("street");
			addressRepository.save(address);

			Company company = new Company();
			company.setName("cummins");
			companyRepository.save(company);

			Employee employee = new Employee();
			employee.setName("John");
			employee.setAddress(address);
			employee.setCompany(company);
			employeeRepository.save(employee);

			Department department = new Department();
			department.setName("ONB");
			departmentRepository.save(department);
			department.getEmployees().add(employee);
			employee.getDepartments().add(department);
			employeeRepository.save(employee);

			employeeRepository.findByCompanyName(company.getName())
					.forEach(e -> log.info("Employee ---- {}", e.getName()));

			employeeRepository.findByCompanyNameJPQL(company.getName())
					.forEach(e -> log.info("Employee JPQL {}", e.getName()));

			employeeRepository.findByCompanyNameNativeQuery(company.getName())
					.forEach(e -> log.info("Employee NQ {}", e.getName()));

		};
	}

}
