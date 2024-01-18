package com.epam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailSender {
	@Autowired
    private JavaMailSender javaMailSender;
	
	void sendEmail() throws MessagingException {
		System.out.println("h1");
		
		//mail with normal message
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom("muvvalavanya2@gmail.com");
        msg.setTo("lavanyamuvva1@gmail.com");
        msg.setSubject("Testing from Spring Boot");
        msg.setText("Hello World \n Spring Boot Email");

        javaMailSender.send(msg);
        System.out.println("Email sent........");
        
        
        MimeMessage msg1 = javaMailSender.createMimeMessage();
        
        //mail with attachments,html inline elements
        MimeMessageHelper helper = new MimeMessageHelper(msg1, true);
        helper.setFrom("muvvalavanya2@gmail.com");
        helper.setTo("lavanyamuvva1@gmail.com");

        helper.setSubject("Testing from Spring Boot");

        
        helper.setText("<h1>Check attachment for image!</h1>", true);
        FileSystemResource file = new FileSystemResource("C:\\Users\\Lavanya_Muvva\\Desktop\\wallpaper.jpg");
		helper.addAttachment(file.getFilename(), file);

        javaMailSender.send(msg1);
	}
}
