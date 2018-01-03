package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class PwaApplication {

	public static void main(String[] args) {
		SpringApplication.run(PwaApplication.class, args);
	}

	@RestController
	static class HelloController{
		@RequestMapping(value = "/hello",method = RequestMethod.GET)
		public String helloMethod(){
			return "hello";
		}
	}

}
