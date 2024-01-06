package com.simran.employeeservice;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@WebMvcTest(EmployeeController.class)
@ExtendWith(MockitoExtension.class)
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    @Test
    public void testGetAllEmployees() {
        Employee employee1 = new Employee(1L, "John Doe", "Developer","john_doe","john@123");
        Employee employee2 = new Employee(2L, "Jane Smith", "Manager","jane_smith","jane@123");
        List<Employee> employees = Arrays.asList(employee1, employee2);

        when(employeeService.getAllEmployees()).thenReturn(employees);

        ResponseEntity<?> result = employeeController.getAllEmployees();

        assertEquals(2, result.toString().length());
    }

    @Test
    public void testGetEmployeeByIdFound() {
        Long loginId = 1L;
        Employee employee = new Employee(loginId, "John Doe", "Developer","john_doe","john@123");

        when(employeeService.getEmployeeById(loginId)).thenReturn(Optional.of(employee));

        ResponseEntity<?> response = employeeController.getEmployeeById(loginId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(employee, response.getBody());
    }

    @Test
    public void testGetEmployeeByIdNotFound() {
        Long loginId = 1L;

        when(employeeService.getEmployeeById(loginId)).thenReturn(Optional.empty());

        ResponseEntity<?> response = employeeController.getEmployeeById(loginId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testAddEmployee() {
        Employee employeeToAdd = new Employee(2L, "Jane Smith", "Manager","jane_smith","jane@123");
        when(employeeService.createEmployee(any(Employee.class))).thenReturn(employeeToAdd);

        ResponseEntity<?> responseEntity = employeeController.createEmployee(employeeToAdd);

        assertEquals(200, responseEntity.getStatusCodeValue());
        assertNotNull(responseEntity.getBody());
    }

    @Test
    void updateEmployee() {
        Long loginIdToUpdate = 1L;
        Employee updatedEmployeeDetails = new Employee(1L, "Jane Smith", "Developer","jane_smith","jane@123");
        when(employeeService.updateEmployee(eq(loginIdToUpdate), any(Employee.class)))
                .thenReturn(Optional.of(updatedEmployeeDetails));

        ResponseEntity<?> responseEntity = employeeController.updateEmployee(loginIdToUpdate, updatedEmployeeDetails);

        assertEquals(200, responseEntity.getStatusCodeValue());
        assertNotNull(responseEntity.getBody());
    }

    @Test
    void deleteEmployee() {
        Long loginIdToDelete = 1L;
        when(employeeService.deleteEmployee(loginIdToDelete)).thenReturn(true);

        ResponseEntity<?> responseEntity = employeeController.deleteEmployee(loginIdToDelete);

        assertEquals(204, responseEntity.getStatusCodeValue());
        assertNull(responseEntity.getBody());
    }

}
