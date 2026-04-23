package com.example.Exercise_2_Controller_layer_REST_CRUD.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class Customer {

    private int id;
    private String userName ;
    private double balance;
}
