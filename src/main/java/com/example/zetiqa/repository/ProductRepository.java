package com.example.zetiqa.repository;

import com.example.zetiqa.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, String> {
    Product findByIsbnAndTitleAndAuthor(String isbn, String title, String author);
    Optional<Product> findByTitle(String title);
    Product deleteByIsbnAndTitle(String isbn, String title);
}
