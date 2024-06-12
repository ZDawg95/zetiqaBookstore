package com.example.zetiqa.service;

import com.example.zetiqa.model.Customer;
import com.example.zetiqa.model.Product;
import com.example.zetiqa.model.Bookstore;

import java.util.List;

public interface BookstoreService {
    public String createBookSale(Product product, Customer customer);

}