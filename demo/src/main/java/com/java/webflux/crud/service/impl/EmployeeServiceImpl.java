package com.java.webflux.crud.service.impl;

import com.java.webflux.crud.dal.model.Employee;
import com.java.webflux.crud.dal.repository.EmployeeRepository;
import com.java.webflux.crud.service.EmployeeService;
import com.java.webflux.crud.service.exception.NotFoundException;
import com.java.webflux.crud.service.mapper.EmployeeServiceMapper;
import com.java.webflux.crud.service.model.EmployeeModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repository;
    private final EmployeeServiceMapper mapper;

    @Override
    public Mono<Employee> saveEmployee(EmployeeModel employeeModel) {
        return repository.save(
                mapper.getEntityFromEmployeeModel(
                        employeeModel
                )
        );
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
    public Mono<Employee> updateEmployee(String employeeId, EmployeeModel employeeModel) {
        Mono<Employee> employeeMono = repository.findById(employeeId);
        return employeeMono.flatMap(
                (existingEmployee) -> {
                    existingEmployee.setFirstName(employeeModel.getFirstName());
                    existingEmployee.setLastName(employeeModel.getLastName());
                    existingEmployee.setEmail(employeeModel.getEmail());
                    return repository.save(existingEmployee);
                }
        );
    }

    @Override
    public Mono<Void> deleteEmployeeById(String employeeId) {
        return repository.deleteById(employeeId);
    }
}
