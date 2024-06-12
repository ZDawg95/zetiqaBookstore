package com.example.zetiqa.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name="t_bookstore_sales")

public class Bookstore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer salesId;

    @Column(nullable = false)
    private String bookIsbn;

    @Column(nullable = false)
    private String bookTitle;

    @Column(nullable = false)
    private String bookAuthor;

    @Column(nullable = false)
    private BigDecimal bookPrice;

    @Column
    private Integer customerId;

    @Column
    private String customerName;

    @Column
    private Integer quantitySold;

    public Bookstore() {
    }

    public Bookstore(Integer salesId, String bookIsbn, String bookTitle, String bookAuthor, Integer quantitySold) {
        this.salesId = salesId;
        this.bookIsbn = bookIsbn;
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.quantitySold = quantitySold;
    }

    public Integer getSalesId() {
        return salesId;
    }

    public void setSalesId(Integer salesId) {
        this.salesId = salesId;
    }

    public String getBookIsbn() {
        return bookIsbn;
    }

    public void setBookIsbn(String bookIsbn) {
        this.bookIsbn = bookIsbn;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public BigDecimal getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(BigDecimal bookPrice) {
        this.bookPrice = bookPrice.setScale(2);
    }

    public Integer getQuantitySold() {
        return quantitySold;
    }

    public void setQuantitySold(Integer quantitySold) {
        this.quantitySold = quantitySold;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
}
