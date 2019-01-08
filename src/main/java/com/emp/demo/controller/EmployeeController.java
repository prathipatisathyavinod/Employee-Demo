package com.emp.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.emp.demo.exception.EmployeeNotFoundException;
import com.emp.demo.model.Employee;
import com.emp.demo.repository.EmployeeRepository;

@RestController
public class EmployeeController {

	@Autowired
	EmployeeRepository empRepo;
	
	@RequestMapping(value="/addEmp", method=RequestMethod.POST)
	public ResponseEntity<Object> createEmployee(@RequestBody Employee emp){
		empRepo.save(emp);
		return new ResponseEntity<Object>("Employee Created successfully",HttpStatus.OK);
	}
	
	@RequestMapping(value="/getAll",method=RequestMethod.GET)
	public List<Employee> getEmployees(){
		return empRepo.findAll();
	}
	
	
	@RequestMapping(value="/updateEmp/{empId}", method=RequestMethod.PUT)
	public ResponseEntity<Object> updateEmployee(@RequestBody Employee employee, @PathVariable("empId") int id) throws EmployeeNotFoundException{
		Employee emp=empRepo.findById(id).orElseThrow(()->new EmployeeNotFoundException(id));
			emp.setEmpId(employee.getEmpId());
			emp.setEmpName(employee.getEmpName());
			emp.setAge(employee.getAge());
			emp.setAddress(employee.getAddress());
			emp.setSalary(employee.getSalary());
			empRepo.save(emp);
			return new ResponseEntity<Object>("Employee updated successfully",HttpStatus.OK);
			
	}
	
	
	@RequestMapping(value="/removeEmp/{empId}", method=RequestMethod.DELETE)
	public ResponseEntity<Object> updateEmployee(@PathVariable("empId") int id){
		empRepo.deleteById(id);
		return new ResponseEntity<Object>("Employee deleted successfully",HttpStatus.OK);
	}
}
