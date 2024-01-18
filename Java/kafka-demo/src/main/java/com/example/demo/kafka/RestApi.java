package com.example.demo.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.User;

@RestController
@RequestMapping("kafka")
public class RestApi {
	
	@Autowired
	private KafkaTemplate<String, User> kafkaTemplate;
	
	@PostMapping("/viewUser")
	public ResponseEntity<String> getMessage(@RequestBody User user)
	{
		kafkaTemplate.send("user-topic",user);
		return new ResponseEntity<>("Data received"+user,HttpStatus.OK);
	}

}
