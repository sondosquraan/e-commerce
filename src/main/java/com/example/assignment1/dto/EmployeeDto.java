package com.example.assignment1.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class EmployeeDto {
      private Long id;
    @NotNull
    @Size(min = 3, max = 250)
    private String name;
    private String email;
    private String password;
    private String date;
}
