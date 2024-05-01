package com.robin.jwtbackend.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@CrossOrigin("http://localhost:4200")
@RestController
public class MessagesController {

    @GetMapping("/messages")
    public ResponseEntity<List<String>> getMessages()
    {
        return ResponseEntity.status(HttpStatus.OK).body(Arrays.asList("first","second"));
    }
}
