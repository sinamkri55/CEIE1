package com.smart.service;

import java.io.File;
import java.io.FileOutputStream;

import javax.mail.Multipart;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message.RecipientType;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class EmailService_CoCreate {

	
	 @Autowired 
	  private JavaMailSender javaMailSender;
	  
	  
	  
	  
	  public boolean sendEmail(String toEmail, String subject, String content,
	  String fromEmail, MultipartFile multipartFile) throws Exception {
	  
	  
	  
	  MimeMessage mimeMessage =javaMailSender.createMimeMessage();
	  
	  
	  mimeMessage.setFrom(fromEmail); 
	  mimeMessage.setRecipients(RecipientType.TO, toEmail);
	  mimeMessage.setSubject(subject); 
	   //mimeMessageHelper.setText(name, false);
	  
	  MimeMultipart multipart=new MimeMultipart();
	  
	  // body of Email
	  MimeBodyPart bodyPart = new MimeBodyPart();
	  
	  bodyPart.setContent(content,"text/html");
	  multipart.addBodyPart(bodyPart);
	  
	  //Attachment of Email
	  MimeBodyPart mimbodyPart = new MimeBodyPart();
	  FileDataSource fileDataSource = new FileDataSource(convert(multipartFile));	
	  mimbodyPart.setDataHandler(new DataHandler(fileDataSource));
	  mimbodyPart.setFileName(multipartFile.getOriginalFilename());
	  multipart.addBodyPart(mimbodyPart);
	  
	  
	  mimeMessage.setContent(multipart);
	  
	  javaMailSender.send(mimeMessage);
	  
	  return true;
	
}
	  
	  private File convert(MultipartFile multipartfile) throws Exception {
		  File file=new File(multipartfile.getOriginalFilename());
		  
		  file.createNewFile();
		  FileOutputStream fileOutputStream=new FileOutputStream(file);
		  
		  fileOutputStream.write(multipartfile.getBytes());
		  fileOutputStream.close();
		  
		  return file;
	  }
}
