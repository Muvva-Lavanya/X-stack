package com.example.webapp;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    @Autowired
    private SqsTemplate sqsTemplate;


    @PostMapping
    public ResponseEntity<String> createStudent(@RequestBody Student student) throws JsonProcessingException {
        sqsTemplate.send("student-topic",new ObjectMapper().writeValueAsString(student));
        return ResponseEntity.ok("Message pushed.");
    }

}
