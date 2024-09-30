package com.smart.service;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service

public class EmailService_ContactForm {

	
	
	 @Autowired 
	  private JavaMailSender javaMailSender;
	  
	  
	  
	  
	  public boolean sendEmail(String toEmail, String subject, String content,
	  String fromEmail) throws Exception {
	  
	  
	  
	  MimeMessage mimeMessage =javaMailSender.createMimeMessage();
	  
	  MimeMessageHelper mimeMessageHelper= new MimeMessageHelper(mimeMessage,true);
	  mimeMessageHelper.setFrom(fromEmail); mimeMessageHelper.setTo(toEmail);
	  mimeMessageHelper.setSubject(subject); mimeMessageHelper.setText(content,
	  true); //mimeMessageHelper.setText(name, false);
	  javaMailSender.send(mimeMessage);
	  
	  
	  
	  return true;
	  
	  
	  
	  
	  
	  } }
	 
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	 * public boolean sendEmail(String subject,String message,String to,String
	 * siteUrl) {
	 * 
	 * boolean f=false; String from="sinamkri55@gmail.com";
	 * 
	 * 
	 * String host="smtp.gmail.com";
	 * 
	 * 
	 * Properties properties=System.getProperties();
	 * System.out.println("PROPERTIES"+properties);
	 * 
	 * 
	 * properties.put("mail.smtp.host", host); properties.put("mail.smtp.port",
	 * "465"); properties.put("mail.smtp.ssl.enable", "true");
	 * properties.put("mail.smtp.auth", "true");
	 * 
	 * 
	 * 
	 * Session session=Session.getInstance(properties, new Authenticator() {
	 * 
	 * @Override protected PasswordAuthentication getPasswordAuthentication() {
	 * 
	 * return new PasswordAuthentication("sinamkri55@gmail.com","ngpekpfudciwbzuv");
	 * }
	 * 
	 * 
	 * 
	 * });
	 * 
	 * 
	 * session.setDebug(true);
	 * 
	 * 
	 * 
	 * MimeMessage m = new MimeMessage(session);
	 * 
	 * 
	 * 
	 * 
	 * try { m.setFrom(from);
	 * 
	 * 
	 * 
	 * m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
	 * 
	 * 
	 * 
	 * m.setSubject(subject);
	 * 
	 * m.setContent(message,"text/html");
	 * 
	 * 
	 * 
	 * 
	 * Transport.send(m);
	 * 
	 * System.out.println("SENT Successfully");
	 * 
	 * f=true; }catch(Exception e) { e.printStackTrace(); } return f;
	 * 
	 * 
	 * 
	 * 
	 * 
	 * } }
	 */



 

