package com.example.Exercise_2_Controller_layer_REST_CRUD.Controller;


import com.example.Exercise_2_Controller_layer_REST_CRUD.ApiResponse.ApiResponse;
import com.example.Exercise_2_Controller_layer_REST_CRUD.Model.Customer;
import org.springframework.scheduling.config.Task;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/customers")
public class CustomersController {


    ArrayList<Customer> customers = new ArrayList<>();

    @GetMapping("/get")
    public ArrayList<Customer> getCustomers() {
        return customers;
    }


    @PostMapping("/add")
    public ApiResponse addCustomer(@RequestBody Customer customer) {
        customers.add(customer);
        return new ApiResponse(customer.getUserName()+ "  added successfully to Customers List");

    }


    @PutMapping("/update/{id}")
    public ApiResponse updateCustomer(@PathVariable int id, @RequestBody Customer customer) {
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getId() == id) {
                customers.set(i, customer);
                return new ApiResponse("Customer updated successfully");
            }
        }
        return new ApiResponse("Customer not found");
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse deleteCustomer(@PathVariable int id) {
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getId() == id) {
                customers.remove(i);
                return new ApiResponse("Customer deleted successfully");
            }
        }
        return new ApiResponse("Customer not found");
    }

    @PatchMapping("/deposit/{id}/{amount}")
    public ApiResponse depositMoneyToCustomer(@PathVariable int id  ,@PathVariable int amount) {
       for (Customer customer: customers){
        if (customer.getId() == id){
            double oldBalance = customer.getBalance();
            customer.setBalance(oldBalance + amount);
            return new ApiResponse("Deposit successful. Old Balance:"+ oldBalance+ " | Current Balance: "+ customer.getBalance());
        }
       }
        return new ApiResponse("Customer not found");

    }

    @PatchMapping("/withdraw/{id}/{amount}")
    public ApiResponse withdrawMoneyToCustomer(@PathVariable int id  ,@PathVariable int amount) {
        for (Customer customer : customers) {
            if (customer.getId() == id) {
                double oldBalance = customer.getBalance();
                if (amount > customer.getBalance()) {
                    return new ApiResponse("amount above the balance");
                }
                customer.setBalance(oldBalance - amount);
                return new ApiResponse("Deposit successful. Old Balance:"+ oldBalance+ " | Current Balance: "+ customer.getBalance());


            }
        }
        return new ApiResponse("Customer not found");


    }


}
