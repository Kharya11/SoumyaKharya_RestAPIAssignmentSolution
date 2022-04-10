package com.greatlearning.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.greatlearning.model.Employee;

@Repository

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findByfNameLike(String fname);

} 
