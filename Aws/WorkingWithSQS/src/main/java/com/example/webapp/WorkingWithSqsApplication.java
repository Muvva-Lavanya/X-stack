package com.example.webapp;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.awspring.cloud.sqs.annotation.SqsListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@Slf4j
@RequiredArgsConstructor
@SpringBootApplication
@EnableScheduling
public class WorkingWithSqsApplication {

    public static void main(String[] args) {
        SpringApplication.run(WorkingWithSqsApplication.class, args);
    }

    @SqsListener(value = "student-topic")
    public void consumer(String student) throws JsonProcessingException {
        System.out.println("consumer side");
        Student student1 = new ObjectMapper().readValue(student, Student.class);
        System.out.println(student1);
    }
}
