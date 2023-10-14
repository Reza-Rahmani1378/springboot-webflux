package com.java.webflux.crud.api.facade.mapper;

import com.java.webflux.crud.api.dto.EmployeeInputModel;
import com.java.webflux.crud.api.dto.EmployeeOutputModel;
import com.java.webflux.crud.dal.model.Employee;
import com.java.webflux.crud.service.model.EmployeeModel;
import org.mapstruct.Mapper;

@Mapper
public interface EmployeeFacadeMapper {

    EmployeeOutputModel getModelFromEntity(Employee employee);

    EmployeeModel getModelFromEmployeeInputModel(EmployeeInputModel employeeInputModel);
}
