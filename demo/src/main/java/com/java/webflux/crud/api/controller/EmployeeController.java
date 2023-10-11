package com.java.webflux.crud.api.controller;

import com.java.webflux.crud.api.dto.EmployeeDto;
import com.java.webflux.crud.api.facade.EmployeeFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeFacade facade;


    @GetMapping("/{id}")
    public Mono<EmployeeDto> getEmployee(@PathVariable("id") String id) {
        return facade.getEmployee(id);
    }
}
