package com.greatlearning.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	@GetMapping("/")
	public String home() {
		//http://localhost:8080/viewMonthlyFinancials
		String homePage="";
		homePage+="<h1> Surabi Restraunts</h1>";
		homePage+="<a href=\"http://localhost:8080/login\">Login</a><br>";
		homePage+="<a href=\"http://localhost:8080/logout\">Logout</a><br>";
		homePage+="<a href=\"http://localhost:8080/swagger-ui.html#/register-controller/createUserUsingGET_1\">Register</a><br>";
		homePage+="Directions: <br>";
		homePage+="open localhost:8080<br>";
		homePage+="click register button, will redirect to swagger tool. Use register controller<br>";
		homePage+="re-open localhost, click login and enter credentials<br>";
		homePage+="Go to swagger tool to continue<br>";
		return homePage;
	}
	

}
