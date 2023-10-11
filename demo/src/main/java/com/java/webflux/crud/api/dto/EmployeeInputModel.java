package com.java.webflux.crud.api.dto;

import lombok.Data;

@Data
public class EmployeeInputModel {

    private String firstName;
    private String lastName;
    private String email;
}
