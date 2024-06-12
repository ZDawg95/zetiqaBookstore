package com.example.zetiqa.service.impl;

import com.example.zetiqa.model.Customer;
import com.example.zetiqa.model.Product;
import com.example.zetiqa.model.Bookstore;
import com.example.zetiqa.repository.BookstoreRepository;
import com.example.zetiqa.service.BookstoreService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookstoreServiceImpl implements BookstoreService {
    BookstoreRepository bookstoreRepository;

    public BookstoreServiceImpl(BookstoreRepository bookstoreRepository) {
        this.bookstoreRepository = bookstoreRepository;
    }

    @Override
    public String createBookSale(Product product, Customer customer) {
        //first check if book exists
        Bookstore newBookSale = new Bookstore();
        newBookSale.setBookAuthor(product.getAuthor());
        newBookSale.setBookTitle(product.getTitle());
        newBookSale.setBookIsbn(product.getIsbn());
        newBookSale.setQuantitySold(1);

        bookstoreRepository.save(newBookSale);
        return "The book titled \"" + product.getTitle() + "\" has been added successfully to the bookshop inventory! :)";
    }
}
