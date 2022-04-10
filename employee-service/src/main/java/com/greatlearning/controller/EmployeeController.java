package com.greatlearning.controller;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.greatlearning.model.Employee;
import com.greatlearning.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public Map<String, Object> fetchAllEmployees(
            @RequestParam(value="page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size,
            @RequestParam(value = "direction", required = false, defaultValue = "asc") String direction,
            @RequestParam(value = "property", required = false, defaultValue = "fName") String property
    ){
        return this.employeeService.findAll(page, size, direction, property);
    }


    
   
    @GetMapping("/{id}")
    public Employee fetchEmployeeById(@PathVariable("id") long empId){
        return this.employeeService.findEmployeeById(empId);
    }

    @PostMapping
    public Employee saveEmployee(@RequestBody @Valid Employee employee)
    {
        return this.employeeService.save(employee);
    }


    @DeleteMapping("/{id}")
    public void deleteEmployeeById(@PathVariable("id") long empId) {
        this.employeeService.deleteEmployeeById(empId);
    }

    
    @GetMapping("/name/{name}")
    public List<Employee> fetchByName(@PathVariable String name){
        return this.employeeService.fetchEmployeesByName(name);
    }
  
    

}
