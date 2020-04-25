package com.example.springrmiserver;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringRmiServerApplication {

	public static void main(String[] args) {
		//System.setProperty("java.rmi.server.codebase", "http://localhost:8081/");
		 
		SpringApplication.run(SpringRmiServerApplication.class, args);
		
		System.out.print("this is my server ");
		
	}

}
