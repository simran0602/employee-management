package com.simran.employeeservice;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
@Validated
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllEmployees() {
        try {
            List<Employee> employees = employeeService.getAllEmployees();
            return ResponseEntity.ok(employees);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", "An error occurred: " + e.getMessage()));
        }
    }

    @GetMapping("/getById/{loginId}")
    public ResponseEntity<?> getEmployeeById(@PathVariable Long loginId) {
        try {
            Optional<Employee> employee = employeeService.getEmployeeById(loginId);
            return employee.map(ResponseEntity::ok).orElseGet(() -> (ResponseEntity<Employee>) notFoundResponse("Employee not found"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", "An error occurred: " + e.getMessage()));
        }
    }

    @PostMapping("/addEmployee")
    public ResponseEntity<?> createEmployee(@RequestBody @Valid Employee employee) {
        Employee createdEmployee = employeeService.createEmployee(employee);
        try {
            return ResponseEntity.ok(createdEmployee);
        } catch (Exception e){
            return handleException(e);
        }
    }

    @PutMapping("/update/{loginId}")
    public ResponseEntity<?> updateEmployee(@PathVariable Long loginId, @RequestBody @Valid Employee updatedEmployee) {
        try {
            Optional<Employee> employee = employeeService.updateEmployee(loginId, updatedEmployee);
            return employee.map(ResponseEntity::ok)
                    .orElseGet(() -> (ResponseEntity<Employee>) notFoundResponse("Employee not found"));
        } catch (Exception e) {
            return handleException(e);
        }
    }

    @DeleteMapping("/delete/{loginId}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long loginId) {
        try{
            if (employeeService.deleteEmployee(loginId)) {
                return ResponseEntity.noContent().build();
            }
            return notFoundResponse("Employee not found");
        } catch (Exception e) {
            return handleException(e);
        }
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Collections.singletonMap("error", "An error occurred: " + e.getMessage()));
    }

    private ResponseEntity<?> notFoundResponse(String message) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap("error", message));
    }

}
