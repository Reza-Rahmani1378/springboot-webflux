package com.java.webflux.crud.api.facade;

import com.java.webflux.crud.api.dto.EmployeeInputModel;
import com.java.webflux.crud.api.dto.EmployeeOutputModel;
import com.java.webflux.crud.api.facade.mapper.EmployeeFacadeMapper;
import com.java.webflux.crud.service.EmployeeService;
import com.java.webflux.crud.service.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class EmployeeFacade {

    private final EmployeeService service;
    private final EmployeeFacadeMapper mapper;


    public Mono<EmployeeOutputModel> getEmployeeById(String id) {
        return service.getEmployeeById(id).map(
                mapper::getModelFromEntity
        );
    }

    public Mono<EmployeeOutputModel> saveEmployee(EmployeeInputModel employeeInputModel) {
       var employeeModel = mapper.getModelFromEmployeeInputModel(employeeInputModel);
       return service.saveEmployee(employeeModel).map(
               mapper::getModelFromEntity
       );
    }

    public Flux<EmployeeOutputModel> getEmployees() {
        return service.getEmployees().map(
                mapper::getModelFromEntity
        ).switchIfEmpty(
                Flux.error(
                        new NotFoundException("Employees Not Registered.")
                )
        );
    }


    public Mono<EmployeeOutputModel> updateEmployee(EmployeeInputModel employeeInputModel, String id) {
        var employeeModel = mapper.getModelFromEmployeeInputModel(employeeInputModel);
        return service.updateEmployee(id, employeeModel).map(
                mapper::getModelFromEntity
        );
    }

    public Mono<Void> deleteEmployeeById(String id) {
        return service.deleteEmployeeById(id);
    }

}
