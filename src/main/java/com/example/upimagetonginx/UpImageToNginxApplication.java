package com.example.upimagetonginx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

//
//@CrossOrigin("*")
@SpringBootApplication
public class UpImageToNginxApplication {

    public static void main(String[] args) {
        SpringApplication.run(UpImageToNginxApplication.class, args);
    }

}
