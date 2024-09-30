package com.smart.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.smart.config.Utility;
import com.smart.dao.UserRepository;
import com.smart.entities.User;
import com.smart.helper.Message;
import com.smart.service.EmailService;
import com.smart.service.UserService;

import net.bytebuddy.utility.RandomString;

@Controller
public class HomeController {
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private UserService userService;

	@GetMapping("/")
	public String home() {
		
		return "home";
	}
	
	
	@GetMapping("/about")
	public String about() {
		
		return "about";
	}
	
	@GetMapping("/network")
	public String network() {
		
		return "finalNetwork";
	}
	
	@GetMapping("/readmore1")
	public String readmore1() {
		
		return "readmore1";
	}
	
	@GetMapping("/readmore2")
	public String readmore2() {
		
		return "readmore2";
	}
	
	@GetMapping("/readmore3")
	public String readmore3() {
		
		return "readmore3";
	}
	
	
	@GetMapping("/signup")
	public String signup(Model model) {
		
		
		model.addAttribute("user", new User());
		return "signup";
	}
	
	
	// Handler for registering user
	
	@PostMapping("/do_register")
	public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result1, Model model, HttpSession session,HttpServletRequest request) {
		
		
		try{
			
			
			if(result1.hasErrors()) {
				
				System.out.println("ERROR"+ result1.toString());
				model.addAttribute("user", user);
				return "signup";
			}
			
			user.setRole("ROLE_USER");
			user.setEnabled(false);
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			
			System.out.println("USER"+user);
			
			//Sending verification link to your Email
			
			String randomCode=RandomString.make(64);
			user.setVerificationcode(randomCode);
			String siteUrl=Utility.getSiteURL(request);
			
			String subject="Veify your Email for Registration";
			String message=""
					+ "<div style='border:1px solid #e2e2e2; padding:20px'>"
					+ "<h1>"
					+ "OTP is "
					+ "<b>"
					+ "</n>"
					+ "</h1>"
					+ "</div>";
			String to=user.getEmail();
			String verifyURL=siteUrl+"/verify?code="+user.getVerificationcode();
			String mainContent="Dear "+user.getName()+",<br>"
					+ "Please click the link below to verify your registration:<br>"
					+"<h3><a href=\""+verifyURL+"\">Verify</a></h3>"
					+ "Thank you,<br>"
				    + "The CEIE Team.";
			
			
			boolean flag=this.emailService.sendEmail(subject,mainContent,to,siteUrl);
			
			
			if(flag) {
				
				
				/*
				 * session.setAttribute("myOtp", otp); session.setAttribute("email", email);
				 * return "verify_otp";
				 */
				
				
			}else {
				
				session.setAttribute("message", "Check your Email Id !");
			}
			
			
			
			
			// Saving new user in the database
			
			User result = userRepo.save(user);
			
			model.addAttribute("user", new User());
			
			session.setAttribute("message", "Verification link sent to your mail !!");
			
			return "signup";
			
		}
			catch(Exception e) {
				
				e.printStackTrace();
				
				model.addAttribute("user", user);
				
				session.setAttribute("message","Email already exist !!");
				
				return "signup";
			}
		
		
		
	}
	
	@GetMapping("/verify")
	public String verifyCode(@Param("code") String code, Model model) {
		
		boolean verified = userService.verify(code);
		
		String pageTitle=verified ? "Verification successed !":"Verification failed !";
		
		model.addAttribute("pageTitle",pageTitle);
		
		return verified ? "verify_success" : "verify_fail";
	}
	
	
	@GetMapping("/login")
	public String customLogin(Model model) {
		
		return "login";
	}
	
	
	/*--------------------------- Map Morocco---------------------------------------*/
	
	
	@GetMapping("/map")
	public String map(Model model) {
		
		return "map";
	}
	
}
