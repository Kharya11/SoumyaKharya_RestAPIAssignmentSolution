package com.greatlearning.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.greatlearning.model.Employee;
import com.greatlearning.repository.EmployeeRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee save(Employee employee){
        return this.employeeRepository.save(employee);
    }

   
    public Map<String, Object> findAll(int page, int size, String direction, String property) {
           //Support pagination
         Sort.Direction sortDirection = direction.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;

         PageRequest pageRequest = PageRequest.of(page, size, sortDirection, property);
         Page<Employee> employees = this.employeeRepository.findAll(pageRequest);
         long totalElements = employees.getTotalElements();
         int totalPages = employees.getTotalPages();
         List<Employee> empList = employees.getContent();
         int currentPage = employees.getNumber();

         HashMap<String, Object> map = new LinkedHashMap<>();
         map.put("page-no", currentPage);
         map.put("total-elements", totalElements);
         map.put("total-pages", totalPages);
         map.put("data", empList);
         return map;
     }



    public Set<Employee> findAll() {
        Iterable<Employee> employeeIterable = this.employeeRepository.findAll();
        Set<Employee> employees = new HashSet<>();
        employeeIterable.forEach(employees::add);
        return employees;

    }
    public Employee findEmployeeById(long employeeId){
        //1. imperative

        /*if(optionalEmployee.isPresent()){
            return optionalEmployee.get();
        } else {
            throw new IllegalArgumentException("Employee with the given employee id does not exists");
        }*/

        //declarative
        /*Optional<Employee> optionalEmployee = this.employeeRepository.findById(employeeId);
        return optionalEmployee.orElseThrow(() -> new IllegalArgumentException("Invalid employee Id"));*/

        //one-liner
        return this.employeeRepository.findById(employeeId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid employee Id"));
    }

    
    public List<Employee> fetchEmployeesByName(String name){
        return this.employeeRepository.findByfNameLike(name);
    }
   

    public void deleteEmployeeById(long employeeId){
        this.employeeRepository.deleteById(employeeId);
    }
    
   
}

