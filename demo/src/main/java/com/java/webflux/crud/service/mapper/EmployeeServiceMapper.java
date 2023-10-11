package com.java.webflux.crud.service.mapper;

import com.java.webflux.crud.dal.model.Employee;
import com.java.webflux.crud.service.model.EmployeeModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeServiceMapper {

    Employee getEntityFromEmployeeModel(EmployeeModel employeeModel);
}
