package com.simran.employeeservice;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Optional<Employee> getEmployeeById(Long loginId) {
        return employeeRepository.findById(loginId);
    }

    @Override
    public Employee createEmployee(@Valid Employee employee) {
        employee.setPassword((new BCryptPasswordEncoder().encode(employee.getPassword())));
        return employeeRepository.save(employee);
    }

    @Override
    public Optional<Employee> updateEmployee(Long loginId, @Valid Employee updatedEmployee) {
        if (employeeRepository.existsById(loginId)) {
            updatedEmployee.setLoginId(loginId);
            return Optional.of(employeeRepository.save(updatedEmployee));
        }
        return Optional.empty();
    }

    @Override
    public boolean deleteEmployee(Long loginId) {
        if (employeeRepository.existsById(loginId)) {
            employeeRepository.deleteById(loginId);
            return true;
        }
        return false;
    }

}
