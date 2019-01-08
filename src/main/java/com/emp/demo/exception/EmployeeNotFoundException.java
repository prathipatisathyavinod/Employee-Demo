package com.emp.demo.exception;

public class EmployeeNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public EmployeeNotFoundException(long id){
	    super("EmployeeId " + id + " does not exist.");
	  }
}
