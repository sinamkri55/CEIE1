package com.smart.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EventsController {
	
	
	@GetMapping("/events")
	public String events() {
		
		return "events";
	}


	
/*---------Upcoming Events-------------	*/
	
	

@GetMapping("/upcomeEvent1")
public String upComingEvents1() {
	
	return "upcomeEvent1";
}

@GetMapping("/upcomeEvent2")
public String upComingEvents2() {
	
	return "upcomeEvent2";
}

@GetMapping("/upcomeEvent3")
public String upComingEvents3() {
	
	return "upcomeEvent3";
}


/*---------Previous Events---------*/



@GetMapping("/preEvent1")
public String preEvents1() {
	
	return "preEvent1";
}


@GetMapping("/preEvent2")
public String preEvents2() {
	
	return "preEvent2";
}

@GetMapping("/preEvent3")
public String preEvents3() {
	
	return "preEvent3";
}
@GetMapping("/preEvent4")
public String preEvents4() {
	
	return "preEvent4";
}
@GetMapping("/preEvent5")
public String preEvents5() {
	
	return "preEvent5";
}
@GetMapping("/preEvent6")
public String preEvents6() {
	
	return "preEvent6";
}

}