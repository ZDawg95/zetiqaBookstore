package com.example.zetiqa.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class Home
{
    @GetMapping("")
    @Operation(summary="This is the Home screen of the Zetiqa application")
    public String homeScreen()
    {
        String hellomsg = new String("Hello there! \n " +
                "This is a TEST REST API Application for ZETIQA (Zachary + Etiqa) BOOKSTORE, a demo bookstore for ETIQA's technical assessment!");
        return hellomsg;
    }
}