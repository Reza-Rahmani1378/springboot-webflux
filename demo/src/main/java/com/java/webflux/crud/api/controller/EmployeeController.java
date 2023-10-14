package com.java.webflux.crud.api.controller;

import com.java.webflux.crud.api.dto.EmployeeInputModel;
import com.java.webflux.crud.api.dto.EmployeeOutputModel;
import com.java.webflux.crud.api.facade.EmployeeFacade;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeFacade facade;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<EmployeeOutputModel> saveEmployee(@RequestBody EmployeeInputModel employeeInputModel) {
        return facade.saveEmployee(employeeInputModel);
    }

    @GetMapping("/{id}")
    public Mono<EmployeeOutputModel> getEmployeeById(@PathVariable("id") String id) {
        return facade.getEmployeeById(id);
    }

    @GetMapping
    public Flux<EmployeeOutputModel> getEmployees() {
        return facade.getEmployees();
    }

    @PutMapping("/{id}")
    public Mono<EmployeeOutputModel> updateEmployee(@RequestBody EmployeeInputModel employeeInputModel ,
                                                    @PathVariable("id") String id) {
        return facade.updateEmployee(employeeInputModel, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteEmployeeById(@PathVariable("id") String id) {
        return facade.deleteEmployeeById(id);
    }
}
