package com.trouty.crudapp.rest;

import com.trouty.crudapp.entity.Employee;
import com.trouty.crudapp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

  private EmployeeService employeeService;
  // quick and dirty: inject employee dao (constructor injection)
  @Autowired
  public EmployeeRestController(EmployeeService theEmployeeService) {
    employeeService = theEmployeeService;
  }

  // expose "/employees" and return list of employees
  @GetMapping("/employees")
  public List<Employee> findAll() {
    return employeeService.findAll();
  }

  // add mapping for GET / employee/{employeeId}
  @GetMapping("/employees/{employeeId}")
  public Employee getEmployee(@PathVariable int employeeId) {
    Employee theEmployee = employeeService.findById(employeeId);
    if (theEmployee == null) {
      throw new RuntimeException("Employee id not found - " + employeeId);
    }
    return theEmployee;
  }

  // add mapping for POST /employees - add new employee
  @PostMapping("/employees")
  public Employee addEmployee(@RequestBody Employee theEmployee) {
    // set id to 0
    theEmployee.setId(0);
    employeeService.save(theEmployee);

    return theEmployee;
  }

  // add mapping for PUT /employees - add new employee
  @PutMapping("/employees")
  public Employee updateEmployee(@RequestBody Employee theEmployee) {
    employeeService.save(theEmployee);
    return theEmployee;
  }

  // add mapping for Delete /employees/{id} - delete employee
  @DeleteMapping("/employees/{employeeId}")
  public String deleteEmployee(@PathVariable int employeeId) {
    Employee tempEmployee = employeeService.findById(employeeId);

    // throw exception if null
    if (tempEmployee == null) {
      throw new RuntimeException("Employee id not found - " + employeeId);
    }

    employeeService.deleteById(employeeId);

    return "Deleted.";
  }
}
