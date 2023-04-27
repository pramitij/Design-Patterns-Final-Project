package com.inventory.strategy;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.inventory.api.StrategyAPI;
import com.inventory.model.Employee;
import com.inventory.repository.EmployeeRepository;

public class EmployeeStrategy implements StrategyAPI{

	private EmployeeRepository employeeRepository;
	private int id;
	private Employee employee;
	
	public EmployeeStrategy(EmployeeRepository employeeRepository, Employee employee) {
		this.employeeRepository = employeeRepository;
		this.employee = employee;
	}

	public EmployeeStrategy(EmployeeRepository employeeRepository, int id) {
		this.employeeRepository = employeeRepository;
		this.id = id;
	}

	@Override
	public void add() {
		if(employeeRepository.usernameExists(employee.getUsername()))
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username already exists");
		employeeRepository.saveEmployee(employee);
	}

	@Override
	public void update() {
		employeeRepository.updateEmployee(employee);
	}

	@Override
	public void delete() {
		Employee emp = employeeRepository.getEmployeebyID(id);
		employeeRepository.deleteEmployee(emp);
	}

}
