package com.example.zetiqa.controller;

import com.example.zetiqa.model.Bookstore;
import com.example.zetiqa.model.Customer;
import com.example.zetiqa.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController
{
    public static final Logger logger = LoggerFactory.getLogger(CustomerController.class);
    CustomerService customerService;
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    //1. Register a new customer
    @PutMapping("/register")
    @Operation(summary="1. Register a new customer")
    public String registerCustomer(@RequestBody Customer customer)
    {
        if (customer.getFirstName() != null && customer.getLastName() != null && customer.getEmail() != null) {
            if (customerService.getCustomerByEmail(customer.getEmail()) != null) {
                return "Sorry, a customer with this email address is already registered! Adding failed.";
            } else {
            customerService.createCustomer(customer);
            logger.info("===LOG: Customer created as: " + customer.getId() + "=====");
            String successMsg = "Success! Customer Details are: " + customer.getFirstName() + customer.getLastName();
            return successMsg;
            }
        }else{
            String failMsg = "Sorry! A new customer must have a valid first name, last name and email address";
            return failMsg;
        }


    }

    //2. Retrieve customer details by email
    @GetMapping("/getCustomer")
    @Operation(summary="2. Retrieve customer details by email")
    public Customer getCustomer(@RequestParam String email)
    {
        Customer customerDetails = customerService.getCustomerByEmail(email);
        if (customerDetails != null){

            logger.info("Hello, " + customerDetails.getFirstName() + " " + customerDetails.getLastName() + "!");
            //String successMsg = "Hello, " + customerDetails.getFirstName() + " " + customerDetails.getLastName() + "!";
            return customerDetails;
        }else{
            logger.error("Sorry, the email address is not registered with our bookstore.");
            return null;
        }

    }




}
