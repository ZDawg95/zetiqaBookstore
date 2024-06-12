package com.example.zetiqa.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name="t_book_products", uniqueConstraints = @UniqueConstraint(columnNames = {"isbn","title","author"}))

public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(nullable = false)
    String isbn;

    @Column(nullable = false)
    String title;

    @Column(nullable = false)
    String author;

    @Column(nullable = false)
    Integer quantity;

    @Column(nullable = false)
    private BigDecimal bookPrice;

    public Product(Integer id, String isbn, String title, String author, Integer quantity) {
        this.id = id;
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.quantity = quantity;
    }

    public Product() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(BigDecimal bookPrice) {
        this.bookPrice = bookPrice.setScale(2);
    }

}
