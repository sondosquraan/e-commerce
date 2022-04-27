package com.example.assignment1.dto;

import lombok.Data;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data

public class ProductDto {
        private Long id;

        @NotNull
        @Size(min = 3, max = 250)
        private String name;
        private String type;
        private Date dateOfExpiry;
    }
