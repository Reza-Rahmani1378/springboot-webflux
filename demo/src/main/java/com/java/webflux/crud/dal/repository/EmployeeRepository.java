package com.java.webflux.crud.dal.repository;

import com.java.webflux.crud.dal.model.Employee;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface EmployeeRepository extends ReactiveMongoRepository<Employee , String> {
}
