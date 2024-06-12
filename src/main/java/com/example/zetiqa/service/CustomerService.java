package com.example.zetiqa.service;

import com.example.zetiqa.model.Customer;
import com.example.zetiqa.model.Bookstore;

public interface CustomerService {
    public String createCustomer(Customer customer);
    public Customer getCustomerByEmail(String id);
    public Bookstore buyBook(String id);
    public Bookstore returnBook(String id);


}
