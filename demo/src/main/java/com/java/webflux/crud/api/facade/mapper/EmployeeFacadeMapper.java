package com.java.webflux.crud.api.facade.mapper;

import com.java.webflux.crud.api.dto.EmployeeDto;
import com.java.webflux.crud.dal.model.Employee;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeFacadeMapper {

    EmployeeDto getModelFromEntity(Employee employee);
}
