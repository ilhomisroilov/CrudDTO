package com.example.cruddto.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentDTO {

    private String firstName;

    private String lastName;

    private Integer group_id;

    private String  city;

    private String district;

    private String street;

}
