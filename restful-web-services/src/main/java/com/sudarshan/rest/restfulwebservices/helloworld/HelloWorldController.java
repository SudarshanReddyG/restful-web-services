package com.sudarshan.rest.restfulwebservices.helloworld;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

//The below annotation is same as @Controller and @ResponseBody 
//Here @ResponseBody annotation searches for Message Converter to represent object into JSON form
@RestController
public class HelloWorldController {
	
	@Autowired
	private MessageSource messageSource;
	
	//public HelloWorldController(MessageSource messageSource) {
	//	this.messageSource = messageSource;
	//}	
	
	//@RequestMapping(method=RequestMethod.GET, path="/hello-world")
	@GetMapping("/hello-world")
	public String sayHello() {
		return "Hello World";
	}
	
	//Converts to JSON object because of the presence of Getter methods in the bean
	@GetMapping("/hello-world-bean")
	public HelloWorldBean sayHelloBean() {
		return new HelloWorldBean("Hello World Bean!!");
	}
	
	@GetMapping("/hello-world/guest/{name}")
	public String sayHelloToGuest(@PathVariable String name) {
		return String.format("Hello World, %s", name);
	}
	
	@GetMapping("/hello-world-bean/guest/{name}")
	public HelloWorldBean sayHelloBeanToGuest(@PathVariable String name) {
		return new HelloWorldBean(String.format("Hello World Bean!!, %s", name));
	}
	
	@GetMapping("/helloorldinternationalized")
	public String sayHelloInternationalized(@RequestHeader(name="Accept-Language", required=false) Locale locale) {
		return messageSource.getMessage("message.good.morning", null, locale);
	}

}
