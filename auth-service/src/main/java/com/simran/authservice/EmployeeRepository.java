package com.simran.authservice;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends Neo4jRepository<Employee, Long> {
    Employee findByUsername(String username);
}
