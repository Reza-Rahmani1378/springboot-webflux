package com.java.webflux.crud.service;

import com.java.webflux.crud.dal.model.Employee;
import com.java.webflux.crud.service.model.EmployeeModel;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EmployeeService {
    Mono<Employee> saveEmployee(EmployeeModel employeeModel);

    Mono<Employee> getEmployeeById(String employeeId);

    Flux<Employee> getEmployees();

    Mono<Void> deleteEmployeeById(String employeeId);

    Mono<Employee> updateEmployee(String employeeId, EmployeeModel employeeModel);
}
