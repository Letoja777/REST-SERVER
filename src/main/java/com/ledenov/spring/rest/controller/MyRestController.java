package com.ledenov.spring.rest.controller;

import com.ledenov.spring.rest.entity.Employee;
import com.ledenov.spring.rest.exception_handling.EmployeeIncorrectData;
import com.ledenov.spring.rest.exception_handling.NoSuchEmployeeException;
import com.ledenov.spring.rest.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MyRestController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees") //getmapping because of get-request
    public List<Employee> showAllEmployees() {

        List<Employee> allEmployees = employeeService.getAllEmployees();
        return  allEmployees;
    }

    @GetMapping("/employees/{id}") //getmapping because of get-request
    public Employee getEmployee(@PathVariable int id) {
        Employee employee = employeeService.getEmployee(id);

        if (employee == null) {
            throw new NoSuchEmployeeException("There is no employee with ID = " + id + " in database");
        }
        return employee;
    }

    @PostMapping("/employees") //postmapping because of post-request
    public Employee addNewEmployee(@RequestBody Employee employee) {
        employeeService.saveEmployee(employee);

        return employee;
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee) {
        employeeService.saveEmployee(employee);
        return employee;
    }

    @DeleteMapping("/employees/{id}")
    public String deleteEmployee(@PathVariable int id) {
        Employee employee = employeeService.getEmployee(id);
        if (employee == null) {
            throw new NoSuchEmployeeException("There is no employee with id= "
            + id + " in database!");
        }
        employeeService.deleteEmployee(id);
        return "Employee with id = " + id + " was deleted!";
    }

}
