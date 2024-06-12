package com.example.zetiqa.service;

import com.example.zetiqa.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    public String createProduct(Product product);
    public void updateProductQuantity(Product product);  //specific for sales! for changing the quantity of books in the shop
    public String deleteByIsbnAndTitle(Product product);  //for removing a book from the inventory

    Product findByIsbnAndTitleAndAuthor(String isbn, String title, String author);

    public Optional<Product> findProductByTitle(String title);
    public List<Product> getAllProducts();

}
