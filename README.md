# employee-management

This is a Spring Boot Microservices Java project with Neo4j integration. It consists of four services - `emp-service`, `auth-service`, `API Gateway`, and `Eureka server`. The system utilizes a Neo4j database to store employee information.

## Overview

Employee-Management is designed to manage employee data using a microservices architecture. The project uses Spring Boot to implement services that communicate with each other.

## Services

### emp-service

The `emp-service` microservice is responsible for managing employee information stored in a Neo4j database. It has endpoints for retrieving, creating, updating, and deleting employee records.

### auth-service

The `auth-service` microservice handles user authentication. It issues JWT tokens upon successful login.

### API Gateway

The `API Gateway` acts as a central entry point for all external requests. It routes incoming requests to the appropriate microservices, ensuring a unified interface for interacting with the system.

### Eureka server

The `Eureka server` is used for service discovery and registration. It allows microservices to locate and communicate with each other dynamically.

## How to run?

To run this project locally, follow the steps below:

### Prerequisites:

Ensure you have Java 17+, Maven, Postman and Neo4j installed on your machine.

Navigate to each service's directory (`eureka-server`,`api-gateway`,`emp-service`, `auth-service`) and run.

Update configuration files as needed. Configure Neo4j connection details in `application.properties` files of `emp-service` and `auth-service`.

### Host-name:

neo4j employeeDB:  neo4j://localhost:7687

emp-service:- http://localhost:8081

eureka-service:-http://localhost:8761

auth-service:- http://localhost:8084

API-Gateway:- http://localhost:8084

### Sample request for emp-service:
{
"loginId": 1,
"name": "Jane Smith",
"position": "HR",
"username": "jane_smith",
"password": "jane@123"
}

### Sample request for auth-service:
{
"username": "akash_verma",
"password": "akash@123"
}


## Endpoints:

### emp-service:
#### GET :-
Host-name/employee/getAll :  Retrieve details of all employee.
Host-name/employee/getById/{id} : Retrieve employee details by ID.
#### POST :-
Host-name/employee/addEmployee : Create a new employee.
#### PUT :-
Host-name/employee/update/{id} : Update an existing employee.
#### DELETE :-
Host-name/employee/delete/{id} : Delete an employee.

### auth-service:
POST
Host-name/auth/login : Authenticate a user and obtain a JWT token.

### Authentication:

To access protected endpoints, include the obtained JWT token in the Authorization header using the "Bearer token".
