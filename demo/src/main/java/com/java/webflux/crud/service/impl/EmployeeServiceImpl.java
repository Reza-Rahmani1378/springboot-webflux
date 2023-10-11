package com.java.webflux.crud.service.impl;

import com.java.webflux.crud.dal.model.Employee;
import com.java.webflux.crud.dal.repository.EmployeeRepository;
import com.java.webflux.crud.service.EmployeeService;
import com.java.webflux.crud.service.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repository;

    @Override
    public Mono<Employee> saveEmployee(Employee employee) {
        return repository.save(employee);
    }

    @Override
    public Mono<Employee> getEmployeeById(String employeeId) {
        return repository.findById(employeeId).switchIfEmpty(
                Mono.error(
                        new NotFoundException("User Notfound...")
                )
        );
    }

    @Override
    public Flux<Employee> getEmployees() {
        return repository.findAll();
    }

    @Override
    public Mono<Employee> updateEmployee(String employeeId, Employee employee) {
        Mono<Employee> employeeMono = repository.findById(employeeId);
        return employeeMono.flatMap(
                (existingEmployee) -> {
                    existingEmployee.setFirstName(employee.getFirstName());
                    existingEmployee.setLastName(employee.getLastName());
                    existingEmployee.setEmail(employee.getEmail());
                    return repository.save(existingEmployee);
                }
        );
    }

    @Override
    public Mono<Void> deleteEmployeeById(String employeeId) {
        return repository.deleteById(employeeId);
    }
}
