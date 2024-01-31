package springbootcurdrestfulwebservices.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import springbootcurdrestfulwebservices.entity.Employee;
import springbootcurdrestfulwebservices.exception.ResourceNotFoundException;
import springbootcurdrestfulwebservices.repository.EmployeeReposistory;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
	
	@Autowired
	
	private EmployeeReposistory employeeReposistory;
	
	// get all employee
	@GetMapping
	public List<Employee> getAllEmployees(){
		return employeeReposistory.findAll();
	}
	//get employees by id
	@GetMapping("/{id}")
	public Employee getEmployeeById(@PathVariable(value= "id") long empID) 
	{
		return this.employeeReposistory.findById(empID)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found with id" +empID));
		
	}
	//create employee
	@PostMapping
	public Employee createEmployee(@RequestBody Employee emp)
	{
		return this.employeeReposistory.save(emp);
	}
	
	//update employee by id
	@PutMapping("/{id}")
	public Employee updateEmployee(@RequestBody Employee emp,
			@PathVariable("id") long empID) 
	{
		Employee existingemp = this.employeeReposistory.findById(empID)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found with id" +empID));
		existingemp.setFirstName(emp.getFirstName());
		existingemp.setLastName(emp.getLastName());
		existingemp.setEmail(emp.getEmail());
		
		//Once you update the employee details save it
		return this.employeeReposistory.save(existingemp);
	}
	
	//delete employee by id
	@DeleteMapping("/{id}")
	public ResponseEntity<Employee> deleteEmp(@PathVariable("id") long empID)
	{
		//when you want to delete user, then get the user from database
		Employee existingemp = this.employeeReposistory.findById(empID)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found with id" +empID));
		//deleteing the user form database
		this.employeeReposistory.delete(existingemp);
		return ResponseEntity.ok().build();
	}
	
}
