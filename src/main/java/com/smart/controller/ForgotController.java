package com.smart.controller;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.smart.config.Utility;
import com.smart.dao.UserRepository;
import com.smart.entities.User;
import com.smart.service.EmailService;

@Controller
public class ForgotController {

	Random random = new Random(1000);
	//Email Id Form open Handler
	
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private UserRepository uerRepo;
	
	@Autowired
	private BCryptPasswordEncoder bcryptPassword;
	
	
	@GetMapping("/forgot")
	public String openEmailForm(){
		
		return "forgot_email_form";
	}
	
	@PostMapping("/send-otp")
	public String sendOTP(@RequestParam("email") String email,HttpSession session,HttpServletRequest request) {
		
		System.out.println("EMAIL "+email);
		//Generating otp of 4 digit
		
		
		
		int otp = random.nextInt(999999);
		
		System.out.println("OTP is "+otp);
		
		//Write code for sending OTP to Email.
		
		String subject="OTP from Network";
		String message=""
				+ "<div style='border:1px solid #e2e2e2; padding:20px'>"
				+ "<h1>"
				+ "OTP is "
				+ "<b>"+otp
				+ "</n>"
				+ "</h1>"
				+ "</div>";
		String to=email;
		String siteUrl=Utility.getSiteURL(request);
		boolean flag=this.emailService.sendEmail(subject,message,to,siteUrl);
		
		
		if(flag) {
			
			
			session.setAttribute("myOtp", otp);
			session.setAttribute("email", email);
			return "verify_otp";
			
			
		}else {
			
			session.setAttribute("message", "Check your Email Id !");
		}
		
		
		return "forgot_email_form";
	}
	
	// Verify OTP
	
	@PostMapping("/verify-otp")
	public String verify_OTP(@RequestParam("otp") int otp,HttpSession session) {
		
		int myOtp = (int) session.getAttribute("myOtp");
		String email = (String) session.getAttribute("email");
		
		
		if(otp==myOtp) {
			
			
			
			
			//password change Form
			
				User user = this.uerRepo.getUserByUserName(email);
				
				if(user==null) {
					
					session.setAttribute("message", "Email Id does not exist !!");
					
					return "forgot_email_form";
				
				}
				else {
					
					
				}
			
		return "password_change_form";
			
		}else {
			
			session.setAttribute("message", "You have entered wrong OTP !!");
			return "verify_otp";
			
		}
			
		
	}
	
	// Change Password
	
	@PostMapping("/change-password")
	public String changePassword(@RequestParam("new-password") String newpassword,HttpSession session) {
		
		String email = (String) session.getAttribute("email");
		User user = this.uerRepo.getUserByUserName(email);
		
		
		user.setPassword(this.bcryptPassword.encode(newpassword));
		
		this.uerRepo.save(user);
		return "redirect:/login?change=Password changed successfully...";
	}
}
