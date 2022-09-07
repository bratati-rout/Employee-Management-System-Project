package com.employee.employee_management_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employee.employee_management_backend.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long>{

}
