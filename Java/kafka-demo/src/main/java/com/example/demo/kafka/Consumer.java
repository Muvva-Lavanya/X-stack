package com.example.demo.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.example.demo.User;

@Service
public class Consumer {
	@KafkaListener(topics="user-topic" ,groupId="notification")
	public void consume(User user)
	{
		System.out.println("Received user +"+user);
	}

}
