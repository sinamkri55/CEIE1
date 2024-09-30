package com.smart.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.smart.entities.ToolkitCocreateForm;
import com.smart.service.EmailService_CoCreate;




@Controller
public class ToolkitController {
	
	
	@Autowired
	
	private EmailService_CoCreate emailserviceToolkit;
	
	@Autowired
	private Environment environment;


	@GetMapping("/toolkits")
	public String toolkit()
	{
		return "toolkits";
	}

	
	@PostMapping("/toolkits")
	public String send(@ModelAttribute("ToolkitCocreateForm") ToolkitCocreateForm toolkitCocreateForm,HttpSession session, @RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
		try{
			
			String from=environment.getProperty("spring.mail.username");
			String content= "Sender-Name: "+toolkitCocreateForm.getName()+ "<br>";
			  
			  content+="Sender E-mail: "+toolkitCocreateForm.getEmail()+"<br>";
			  content+="Message: "+toolkitCocreateForm.getMessage()+"<br>";
			
			  String subject="Regarding Toolkits";
			  boolean flag=this.emailserviceToolkit.sendEmail(from, subject, content, toolkitCocreateForm.getEmail(),file);
			  
			  
			  
           if(flag) {
				  
				  
				  session.setAttribute("message", "Email sent successfully!");
				  
				  
				  
				  
				  return "toolkits";
				  
				  
				  }else {
					  
					  session.setAttribute("message", "Something went wrong !"); }
					  
					  
					  return "toolkits"; 
			  
		}catch(Exception e) {
			
			e.printStackTrace();
		}
		
		
		
		return "toolkits";
		
	}

	
	
	
	
	
	
	
	
	@GetMapping("/engage")
	public String engage()
	{
		return "engage";
	}
	
	
	@GetMapping("/co-create")
	public String cocreate()
	{
		return "co-create";
	}
	
	
	@GetMapping("/download-full-pdf")
	public String downloadFullPdf()
	{
		return "download-full-pdf";
	}
	
	
	
	
	
	/*---------------------------------Toolkit 1 -------------------------------------*/
	
	
	/*
	 * @GetMapping("/download-pdf") public void downloadPDF(HttpServletResponse
	 * response) throws IOException { // Get the PDF data as a byte array (replace
	 * with your logic) Path pdfPath = Paths.get(pdfLocation, "my-report.pdf"); //
	 * Adjust filename as needed
	 * 
	 * if (Files.exists(pdfPath)) { byte[] pdfData = Files.readAllBytes(pdfPath);
	 * response.setContentType("application/pdf");
	 * response.setHeader("Content-Disposition",
	 * "attachment; filename=my-report.pdf");
	 * response.getOutputStream().write(pdfData); } else { // Handle case where PDF
	 * is not found (e.g., return error message)
	 * response.setStatus(HttpStatus.NOT_FOUND); }
	 * 
	 * response.setContentType("application/pdf");
	 * response.setHeader("Content-Disposition", "attachment; filename=my-pdf.pdf");
	 * response.getOutputStream().write(pdfData); }
	 */
	
}
