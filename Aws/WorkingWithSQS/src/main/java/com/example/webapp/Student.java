package com.example.webapp;


import lombok.Data;

import java.io.Serializable;

@Data
public class Student implements Serializable {

    private Long id;
    private String name;
    private String email;
    private String mbl;




}
