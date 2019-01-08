package com.emp.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.emp.demo.model.Employee;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
