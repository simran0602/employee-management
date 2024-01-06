package com.simran.employeeservice;

import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    List<Employee> getAllEmployees();

    Optional<Employee> getEmployeeById(Long loginId);

    Employee createEmployee(@Valid Employee employee);

    Optional<Employee> updateEmployee(Long loginId, @Valid Employee updatedEmployee);

    boolean deleteEmployee(Long loginId);
}
