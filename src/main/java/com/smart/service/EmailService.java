package com.smart.service;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service

public class EmailService {

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	  //this is responsible to send email 
	public boolean sendEmail(String subject,String message,String to,String siteUrl)
	   {
	  
	  boolean f=false; String from="sinamkri55@gmail.com";
	  
	  //Variable for gmail 
	  String host="smtp.gmail.com";
	  
	  //get system properties 
	  Properties properties=System.getProperties();
	  System.out.println("PROPERTIES"+properties);
	  
	  //Setting important information to properties object
	  
	  //Host set
	  
	  properties.put("mail.smtp.host", host); properties.put("mail.smtp.port",
	  "465"); properties.put("mail.smtp.ssl.enable", "true");
	  properties.put("mail.smtp.auth", "true");
	  
	  //Step 1: to get the session object ...
	  
	  Session session=Session.getInstance(properties, new Authenticator() {
	  
	  @Override protected PasswordAuthentication getPasswordAuthentication() { 
	  //TODO Auto-generated method stub 
		 
		  return new  PasswordAuthentication("sinamkri55@gmail.com","ngpekpfudciwbzuv"); }
	  
	 
	  
	  });
	  
	 
	  session.setDebug(true);
	  
	  // Step 2 : Compose message [text,multimedia]
	  
	  MimeMessage m = new MimeMessage(session);
	  
	  
	  // From Email
	  
	  try { m.setFrom(from);
	  
	  // Adding Recipient to message
	  
	  m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
	  
	  // Adding Subject to message
	  
	  m.setSubject(subject);
	  
	  //Adding text to message // m.setText(message);
	  m.setContent(message,"text/html");
	  
	  
	  // Step 3: Send the message using Transport class
	  
	  Transport.send(m);
	  
	  System.out.println("SENT Successfully");
	  
	  f=true; }catch(Exception e) { e.printStackTrace(); } return f;
	  
	  
	 
	  
	  
	  }}
	 

