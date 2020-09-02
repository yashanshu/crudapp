package com.trouty.crudapp.rest;

import com.trouty.crudapp.dao.EmployeeDAO;
import com.trouty.crudapp.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

  private EmployeeDAO employeeDAO;
  // quick and dirty: inject employee dao (constructor injection)
  @Autowired
  public EmployeeRestController(EmployeeDAO theEmployeeDAO) {
    employeeDAO = theEmployeeDAO;
  }

  // expose "/employees" and return list of employees
  @GetMapping("/employees")
  public List<Employee> findAll() {
    return employeeDAO.finadAll();
  }
}
