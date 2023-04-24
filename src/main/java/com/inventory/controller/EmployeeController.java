package com.inventory.controller;

import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.inventory.model.Employee;
import com.inventory.repository.EmployeeRepository;
import com.inventory.strategy.EmployeeStrategy;
import com.inventory.strategy.StrategyTrigger;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@PostMapping("/save")
	public void save(@RequestBody Employee employee) {
		StrategyTrigger trig = new StrategyTrigger(new EmployeeStrategy(employeeRepository, employee));
		trig.executeAdd();
	}

	@GetMapping("/getAll")
	public List<Employee> getAll() {
		return employeeRepository.getEmployees();
	}
	
	@GetMapping("/getEmployee/{id}")
	public Employee getEmployee(@PathVariable int id) {
		return employeeRepository.getEmployeebyID(id);
	}
	
	@PutMapping("/update")
	public void update(@RequestBody Employee employee) {
		StrategyTrigger trig = new StrategyTrigger(new EmployeeStrategy(employeeRepository, employee));
		trig.executeUpdate();
	}
	
	@DeleteMapping("/delete/{id}")
	public void deletebyID(@PathVariable int id) {
		StrategyTrigger trig = new StrategyTrigger(new EmployeeStrategy(employeeRepository, id));
		trig.executeDelete();
	}
	
	@PostMapping("/login")
	public Employee login(@RequestBody JSONObject jsoncredentials) {
		String username = (String) jsoncredentials.get("username");
		String password = (String) jsoncredentials.get("password");
		if(!employeeRepository.usernameExists(username))
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username does not exist");
		return employeeRepository.getEmployeeforLogin(username, password);
	}
	
//	@GetMapping("/login")
//	public Employee login(@RequestParam("username") String username, @RequestParam("password") String password) {
//		return employeeService.hasAccess(username, password);
//	}
	
}
