package com.smart.controller;

import java.security.Principal;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.smart.dao.UserRepository;
import com.smart.entities.User;
import com.smart.helper.Message;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private UserRepository userRepo;
	
	@GetMapping("/index")
	public String dashboard(Model model, Principal princple) {
		
		String userName = princple.getName();
		//System.out.println("UserName is"+ userName);// Getting Email id
		
		// Get the user using userName(Email)
		
		User user = userRepo.getUserByUserName(userName);
		
		//System.out.println(user);
		
		model.addAttribute("user",user);
		return "normal/dashboard";
	}
	
	@GetMapping("/home")
	public String details(Model model, Principal princple) {
		String userName = princple.getName();
		
		User user = userRepo.getUserByUserName(userName);
		
		model.addAttribute("user",user);
		return "normal/home1";
	}
	
	@GetMapping("/about")
	public String aboutdashboard(Model model, Principal princple) {
		String userName = princple.getName();
		
		User user = userRepo.getUserByUserName(userName);
		
		model.addAttribute("user",user);
		return "normal/about1";
	}
	
	
	@GetMapping("/contact")
	public String contactdashboard(Model model, Principal princple) {
		String userName = princple.getName();
		
		User user = userRepo.getUserByUserName(userName);
		
		model.addAttribute("user",user);
		return "normal/contact1";
	}
	

	@GetMapping("/profile")
	public String userProfile(Model model, Principal princple) {
		String userName = princple.getName();
		
		User user = userRepo.getUserByUserName(userName);
		
		model.addAttribute("user",user);
		return "normal/profile";
	}
// Open settings(Change password) handler
	
	@GetMapping("/settings")
	public String openSettings(Model model, Principal princple) {
String userName = princple.getName();
		
		User user = userRepo.getUserByUserName(userName);
		
		model.addAttribute("user",user);
		model.addAttribute("title","This is Profile Page");
		return "normal/settings";
	}
	
	// Change user password in User Profile
	
	@PostMapping("/change-password")
	public String changePassword(Model model, Principal princple, @RequestParam("oldPassword") String oldPassword, @RequestParam("newPassword") String newPassword,Principal principal,HttpSession session) {
		
        String userName = principal.getName();
		
		User currentUser = userRepo.getUserByUserName(userName);
		if(this.bCryptPasswordEncoder.matches(oldPassword, currentUser.getPassword())) {
		
			//Change the password
			currentUser.setPassword(this.bCryptPasswordEncoder.encode(newPassword));
			this.userRepo.save(currentUser);
			session.setAttribute("message",new Message("You password successfully changed !!","alert-success"));
		}
		
		else {
			session.setAttribute("message", new Message("Please enter correct password","alert-danger"));
			
			return "redirect:/user/settings";
		}
		
		model.addAttribute("user",currentUser);
		System.out.println("OLD PASSWORD"+oldPassword);
		System.out.println("New PASSWORD"+newPassword);

		
		return "redirect:/user/settings";
	}
	
	// Open Update form (Profile) handler
	
	@PostMapping("/update-profile/{uid}")
	
	   public String updateForm(@PathVariable("uid") Integer uid, Model model, Principal princple){
		
       String userName = princple.getName();
		
		User user = userRepo.getUserByUserName(userName);
		
		model.addAttribute("user",user);
		model.addAttribute("title","Update your profile");
		
		//Optional<User> user1 = this.userRepo.findById(uid);
		
		return "normal/update-profile";
	}

	// Update contact handler
	
	@PostMapping("/process-update-profile")
	public String processUpdateProfile(@ModelAttribute User user, Model model, HttpSession session, Principal principal) {
		
		//System.out.println("User name is "+user.getId());
		
		try {
		User user1=this.userRepo.getUserByUserName(principal.getName());
		 // User user1=this.userRepo.findById(1).get();
		// System.out.println(user1.getId());
		 
		
		  user1.setName(user.getName()); 
		  user1.setEmail(user.getEmail());
		  this.userRepo.save(user1);
		}
		
		catch(Exception e) {
			e.printStackTrace();
		}
		return "redirect:/login/";
		
	}
}
