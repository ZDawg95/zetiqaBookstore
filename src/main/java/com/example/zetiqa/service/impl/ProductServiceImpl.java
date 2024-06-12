package com.example.zetiqa.service.impl;

import com.example.zetiqa.model.Product;
import com.example.zetiqa.repository.ProductRepository;
import com.example.zetiqa.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    public static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public String createProduct(Product product) {
        productRepository.save(product);
        return "The book titled \"" + product.getTitle() + "\" has been added successfully to the store inventory! :)";
    }

    @Override
    public void updateProductQuantity(Product product)
    {
        productRepository.save(product);
        logger.info("===LOG=== Updating the book quantity to " + product.getQuantity());
    }

    @Override
    public String deleteByIsbnAndTitle(Product product)
    {
        productRepository.deleteByIsbnAndTitle(product.getIsbn(),product.getTitle());
        return "Delete done";
    }

    @Override
    public Product findByIsbnAndTitleAndAuthor(String isbn, String title, String author) {
        return productRepository.findByIsbnAndTitleAndAuthor(isbn, title, author);
    }

    @Override
    public Optional<Product> findProductByTitle(String title) {
        return productRepository.findByTitle(title);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
