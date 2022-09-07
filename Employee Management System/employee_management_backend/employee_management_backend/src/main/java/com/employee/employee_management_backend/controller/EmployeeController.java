package com.employee.employee_management_backend.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.employee.employee_management_backend.exception.ResourceNotFoundException;
import com.employee.employee_management_backend.model.Employee;
import com.employee.employee_management_backend.repository.EmployeeRepository;

//import antlr.collections.List;


@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "http://localhost:4200")
//@CrossOrigin("*")
public class EmployeeController {
	
	@Autowired
	private EmployeeRepository emprepo;
	
	//get All Employee
	@GetMapping("/AllEmployees")
	//@RequestMapping(value = "/AllEmployees",method=RequestMethod.GET)
	public List<Employee> getAllEmployees()
	{
		return emprepo.findAll();
	}
	
	@PostMapping("/AllEmployees")
	//@RequestMapping(value = "/addEmployee",method=RequestMethod.POST)
	public Employee createEmployee(@RequestBody Employee employee)
	{
		return emprepo.save(employee);
	}
	//get employee by id rest api
	@GetMapping("/AllEmployees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id)
	{
		Employee employee=emprepo.findById(id).
				orElseThrow(() -> new ResourceNotFoundException("Employee not exists with id:"+ id));
		return ResponseEntity.ok(employee);
	}
	
	//update employee rest api
	@PutMapping("/AllEmployees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails)
	{
		Employee employee=emprepo.findById(id).
				orElseThrow(() -> new ResourceNotFoundException("Employee not exists with id:"+ id));
		employee.setFname(employeeDetails.getFname());
		employee.setLname(employeeDetails.getLname());
		employee.setEmail(employeeDetails.getEmail());
		
		Employee updatedEmployee=emprepo.save(employee);
		return ResponseEntity.ok(updatedEmployee);
	}
	//delete employee rest api
	
	@DeleteMapping("/AllEmployees/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id){
		Employee employee= emprepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee does not exists with id:"+id));
		
		emprepo.delete(employee);
		Map<String, Boolean> response= new HashMap<>();
		response.put("delted successfully", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}

}
