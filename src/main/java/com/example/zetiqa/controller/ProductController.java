package com.example.zetiqa.controller;

import com.example.zetiqa.model.Bookstore;
import com.example.zetiqa.model.Customer;
import com.example.zetiqa.model.OrderRequest;
import com.example.zetiqa.model.Product;
import com.example.zetiqa.repository.BookstoreRepository;
import com.example.zetiqa.repository.ProductRepository;
import com.example.zetiqa.service.CustomerService;
import com.example.zetiqa.service.ProductService;
import com.example.zetiqa.service.BookstoreService;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    public static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    //Declare the book and bookstore service
    @Autowired
    ProductService productService;

    @Autowired
    CustomerService customerService;

    @Autowired
    private BookstoreRepository bookstoreRepository;

    @Autowired
    private ProductRepository productRepository;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }



    //1. Add a new product (books) to the shop
    @PostMapping("/add-product")
    @Operation(summary = "Add a new product (books) to the shop")
    public String addProduct(@RequestBody Product product)
    {
        String successMsg = "The book titled \"" + product.getTitle() + "\" has been added successfully to the bookstore! :)";
        String updateMsg = "We added another copy of the book titled \"" + product.getTitle() + "\" into the bookstore! :)";

        //check in T_PRODUCTS if there exists any books with the same ISBN, Title and Author combination
        Product existingProduct = productService.findByIsbnAndTitleAndAuthor(product.getIsbn(), product.getTitle(), product.getAuthor());
        if (existingProduct != null) {
            logger.info("===LOG=== Product exists in the bookstore = " + existingProduct.getTitle());
            //if present, just add the book and increase the quantity
            Integer updatedQuantity = product.getQuantity() + existingProduct.getQuantity();
            logger.info("===LOG=== Quantity to be added is = " + updatedQuantity);
            existingProduct.setQuantity(updatedQuantity);
            //Add the product to the Bookstore
            productRepository.save(existingProduct);
            return updateMsg;
        } else {
            // the book product is new, simply add a new book to the bookstore
            productService.createProduct(product);
        }
        return successMsg;
    }

    //3. Get a list of all the books in the bookstore.
    @GetMapping("/getAllBooks")
    @Operation(summary = "Get a list of all the books in the bookstore.")
    public List<Product> getListOfAllBooks()
    {
        return productService.getAllProducts();
    }

    //4. Delete a book from the bookstore by isbn and title
    @DeleteMapping("/removeBook")
    @Operation(summary = "Delete a book from the bookstore by isbn, title and author")
    public String removeBook(String isbn, String title, String author){
        String successMsg = "The book titled \"" + title + "\" has been deleted successfully from the bookstore.";
        String failMsg = "The book titled \"" + title + "\" does not exist in the bookstore.";
        Product productToDelete = productService.findByIsbnAndTitleAndAuthor(isbn, title, author);
        if (productToDelete != null){
            productRepository.deleteById(String.valueOf(productToDelete.getId()));
        }else{
            return failMsg;
        }
        return successMsg;

    }

    /*//5. Make a book sale - to be improved
    @PostMapping("/buyOneBook")
    @Operation(summary = "Enables a registered customer to purchase one (1) book")
    public Bookstore purchaseSingleProduct(OrderRequest orderRequest){
        //first, find out of the customer exists
        Bookstore bookstoreSale = new Bookstore();
        Customer customer = customerService.getCustomerByEmail(orderRequest.getCustomer().getEmail());
        if (customer != null){
            bookstoreSale.setCustomerId(customer.getId());
            bookstoreSale.setCustomerName(customer.getFirstName() + " " + customer.getLastName());
        }else{
            logger.error("This email address \"" + orderRequest.getCustomer().getFirstName() + "\" does not belong to a registered user.");
            return null;
        }
        //next, find out if the book exists
        Product productToBeSold = productService.findByIsbnAndTitleAndAuthor(orderRequest.getProduct().getIsbn(),
                orderRequest.getProduct().getTitle(), orderRequest.getProduct().getAuthor());
        if (productToBeSold != null){
            //find out if we have stock
            if (productToBeSold.getQuantity() == 0){
                logger.error("The book titled \"" + orderRequest.getProduct().getTitle() + "\" is out of stock.");
                return null;
            }else{
                //reduce amount by 1
                productToBeSold.setQuantity(productToBeSold.getQuantity() - 1);
                productRepository.save(productToBeSold);
            }
            //finally Make the sale, and create a new bookstore sales record
            bookstoreSale.setBookAuthor(productToBeSold.getAuthor());
            bookstoreSale.setBookIsbn(productToBeSold.getIsbn());
            bookstoreSale.setBookTitle(productToBeSold.getTitle());
            bookstoreSale.setBookPrice(productToBeSold.getBookPrice().multiply(BigDecimal.valueOf(productToBeSold.getQuantity())).setScale(2));
            bookstoreRepository.save(bookstoreSale);
            logger.info("The book titled \"" + bookstoreSale.getBookTitle() + "\" has been sold successfully from the bookstore.");

        }else{
            logger.error("The book titled \"" + orderRequest.getProduct().getTitle() + "\" does not exist in the bookshop.");
            return null;

        }

        return bookstoreSale;

    }*/


}
