package com.example.assignment1.services;

import com.example.assignment1.dto.EmployeeDto;

import java.util.List;

public interface EmployeeServices {

    EmployeeDto createEmployee(EmployeeDto employeeDto);

        List<EmployeeDto> getAllEmployee();

    EmployeeDto getEmployeeById(long id);

    EmployeeDto updateEmployee(EmployeeDto employeeDto, long id);

        void deleteEmployeeById(long id);


    }

