package com.optimum.employeedashboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService{

	@Autowired
	public JavaMailSender emailSender;

	@Override
	public void sendEmail(String recipient,String subject, String text) {
		
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(recipient);
		message.setText(text);
		message.setSubject(subject);
		emailSender.send(message);		
		
	}
	
	
	
}
