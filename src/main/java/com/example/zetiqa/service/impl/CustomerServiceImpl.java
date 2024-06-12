package com.example.zetiqa.service.impl;

import com.example.zetiqa.model.Bookstore;
import com.example.zetiqa.model.Customer;
import com.example.zetiqa.repository.CustomerRepository;
import com.example.zetiqa.repository.BookstoreRepository;
import com.example.zetiqa.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.dao.DataIntegrityViolationException;

@Service
public class CustomerServiceImpl implements CustomerService {

    CustomerRepository customerRepository;
    @Autowired
    BookstoreRepository bookstoreRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;

    }

    @Override
    public String createCustomer(Customer customer) {
        try {
            customerRepository.save(customer);
            String successMsg = "Success! A new customer has been added: " + customer.getFirstName();
            return successMsg;
        }catch (DataIntegrityViolationException e) {
            return "Duplicate customer is detected, the same name with the same email has been entered, please try again";

        }
    }

    @Override
    public Customer getCustomerByEmail(String email) {
        return customerRepository.findByEmail(email);
    }

    @Override
    public Bookstore buyBook(String id) {
        return null;
    }

    @Override
    public Bookstore returnBook(String id) {
        return null;
    }


    /*@Override
    public Bookstore buyBook(String id) {
        Bookstore targetBook =  bookstoreRepository.findById(id).get();
        if (targetBook != null && !targetBook.isBorrowed()){
            targetBook.setBorrowed(true);
            bookstoreRepository.save(targetBook);
        }else{
            return null;
        }
        return targetBook;
    }

    @Override
    public Bookstore returnBook(String id) {
        Bookstore targetBook =  bookstoreRepository.findById(id).get();
        if (targetBook != null && targetBook.isBorrowed()){
            targetBook.setBorrowed(false);
            bookstoreRepository.save(targetBook);
        }else{
            return null;
        }
        return targetBook;
    }*/


}