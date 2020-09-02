package com.trouty.crudapp.dao;

import com.trouty.crudapp.entity.Employee;

import java.util.List;

public interface EmployeeDAO {

  public List<Employee> finadAll();

  public Employee findById(int theId);

  public void save(Employee theEmployee);

  public void deleteById(int theId);
}
