package com.epam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.mail.MessagingException;

@SpringBootApplication
public class SimpleMailApplication implements CommandLineRunner{
	@Autowired
    private EmailSender emailSender;
//
//    public static void main(String[] args) {
//        SpringApplication.run(SimpleMailApplication.class, args);
//    }
//    @EventListener(ApplicationReadyEvent.class)
//    public void sendEmail()
//    {
//    	//System.out.println("hi");
//    	emailSender.sendEmail();
//    }
	
	
    public static void main(String[] args) {
        SpringApplication.run(SimpleMailApplication.class, args);
    }

    @Override
    public void run(String... args) throws MessagingException {

        emailSender.sendEmail();

    }

    
}


