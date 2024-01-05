package com.simran.employeeservice;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> getEmployeeById(Long loginId) {
        return employeeRepository.findById(loginId);
    }

    public Employee createEmployee(@Valid Employee employee) {
        return employeeRepository.save(employee);
    }

    public Optional<Employee> updateEmployee(Long loginId, @Valid Employee updatedEmployee) {
        if (employeeRepository.existsById(loginId)) {
            updatedEmployee.setLoginId(loginId);
            return Optional.of(employeeRepository.save(updatedEmployee));
        }
        return Optional.empty();
    }

    public boolean deleteEmployee(Long loginId) {
        if (employeeRepository.existsById(loginId)) {
            employeeRepository.deleteById(loginId);
            return true;
        }
        return false;
    }

}
