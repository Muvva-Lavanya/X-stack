package com.epam.webapplication;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebApplicationController {

    @GetMapping("/welcome")
    public ResponseEntity<String> firstController () {
        return new ResponseEntity<>("Application is deployed in EC2 instance.", HttpStatus.OK);
    }
}
