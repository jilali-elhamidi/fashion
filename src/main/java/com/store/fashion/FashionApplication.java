package com.store.fashion;

import com.store.fashion.Service.CSVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class FashionApplication {

    public static void main(String[] args) {
        SpringApplication.run(FashionApplication.class, args);
    }


    
}
