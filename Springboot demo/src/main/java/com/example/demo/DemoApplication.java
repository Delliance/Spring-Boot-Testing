package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@RestController //This allows for this class to have REST endpoints
//////////////////This Controller was moved to the Student Controller Class, all the controllers of the API will be there
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

//////////////////This method was moved to the StudentController Class, where all the
//	@GetMapping //This allows for this method to work as a REST endpoint, this GET means what is GET from the webpage with the server for this API
//	public List <Student> hello(){
//		return List.of(
//				new Student(
//						1L,
//						"Maria",
//						"maria.jml@gmail.com",
//						LocalDate.of(2000, Month.JANUARY, 5),
//						21 //This age can also be done as the current year minus the dae of birth
//				)
//		);
//	}

}
