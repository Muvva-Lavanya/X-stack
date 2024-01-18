package com.epam.webapp;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.awspring.cloud.dynamodb.DynamoDbTemplate;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.model.Page;
import software.amazon.awssdk.enhanced.dynamodb.model.PageIterable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StudentService {
        @Autowired
        private DynamoDbTemplate dynamoDbTemplate;
        @Autowired
        private SqsTemplate sqsTemplate;
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        PageIterable<Student> studentPageIterable = dynamoDbTemplate.scanAll(Student.class);
        for(Page<Student> studentPage : studentPageIterable) {
            students.addAll(studentPage.items());
        }
        return students;
    }

    public Optional<Student> getStudentById(String id) {
        return Optional.ofNullable(dynamoDbTemplate.load(Key.builder().partitionValue(String.valueOf(id)).build(), Student.class));
    }

    public Student saveStudent(Student student) throws JsonProcessingException {
        student.setId(UUID.randomUUID());
        sqsTemplate.send("Student-topic",new ObjectMapper().writeValueAsString(student));
        return dynamoDbTemplate.save(student);

    }

    public void updateStudent(UUID id,Student student) {
        Optional<Student> s= Optional.ofNullable(dynamoDbTemplate.load(Key.builder().partitionValue(String.valueOf(id)).build(), Student.class));
        if(s.isEmpty())
        {
            System.out.println("no record exist");
        }
        else {
            student.setId(id);
            dynamoDbTemplate.update(student);
        }



    }

    public void deleteStudent(UUID id) {
        dynamoDbTemplate.delete(Key.builder().partitionValue(String.valueOf(id)).build(),Student.class);
    }
}
