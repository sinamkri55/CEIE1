package com.smart.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.smart.config.Utility;
import com.smart.dao.UserRepository;
import com.smart.entities.Contactform;
import com.smart.entities.User;
import com.smart.helper.Message;
import com.smart.service.EmailService;
import com.smart.service.EmailService_ContactForm;

@Controller
public class ContactController {

	
	@Autowired
	private EmailService_ContactForm emailService;
	
	
	
	@Autowired
	private UserRepository uerRepo;
	
	
	@GetMapping("/contact")
	public String contact() {
		
		return "contact";
	}
	
	
	@PostMapping("/contact") 
	public String send(@ModelAttribute("contactform")
	Contactform contactform,Model model,HttpSession session,HttpServletRequest request) {
		try {
			/*
			 * String name=request.getParameter("name"); String
			 * from=request.getParameter("email"); String
			 * subject=request.getParameter("subject"); String
			 * message=request.getParameter("message");
			 */
			
			
			  String content= "Sender-Name: "+contactform.getName()+ "<br>";
			  
			  content+="Sender E-mail: "+contactform.getEmail()+"<br>";
			  content+="Message: "+contactform.getMessage()+"<br>";
			  
			  boolean flag=this.emailService.sendEmail("sinamkri55@gmail.com",contactform.getSubject(),content,contactform.getEmail());
			  if(flag) {
				  
				  
				  session.setAttribute("message", "Email sent successfully!");
				  
				  
				  
				  
				  return "contact";
				  
				  
				  }else {
					  
					  session.setAttribute("message", "Something went wrong !"); }
					  
					  
					  return "contact"; 
			  
		}
		
	  catch(Exception e) {
		  
		   e.printStackTrace(); 
		  
	  }
		  
		return "contact";  
		  
	}
	
}
	
	
	/*
	 * @PostMapping("/contact") public String submitContact(@ModelAttribute("user")
	 * User user,Model model,HttpSession session, HttpServletRequest request) {
	 * String name=request.getParameter("name"); String
	 * to=request.getParameter("email"); String
	 * subject=request.getParameter("subject"); String
	 * message=request.getParameter("message");
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * boolean flag=this.emailService.sendEmail(subject,message,to,name);
	 * 
	 * 
	 * if(flag) {
	 * 
	 * 
	 * session.setAttribute("message", "Email sent successfully!");
	 * 
	 * 
	 * 
	 * 
	 * return "contact";
	 * 
	 * 
	 * }else {
	 * 
	 * session.setAttribute("message", "Something went wrong !"); }
	 * 
	 * 
	 * return "contact"; }
	 */
	

		
	
