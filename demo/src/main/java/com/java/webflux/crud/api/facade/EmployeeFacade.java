package com.java.webflux.crud.api.facade;

import com.java.webflux.crud.api.dto.EmployeeDto;
import com.java.webflux.crud.api.facade.mapper.EmployeeFacadeMapper;
import com.java.webflux.crud.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class EmployeeFacade {

    private final EmployeeService service;
    private final EmployeeFacadeMapper mapper;


    public Mono<EmployeeDto> getEmployee(String id) {
        return service.getEmployeeById(id).map(
                mapper::getModelFromEntity
        );
    }
}
