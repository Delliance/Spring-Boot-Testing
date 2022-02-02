package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static java.time.Month.JANUARY;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){

        return args -> {

                Student mariam = new Student(
                        1L,
                        "Mariam",
                        "maria.jml@gmail.com",
                        LocalDate.of(2000, JANUARY, 5)
//                        21 //This age can also be done as the current year minus the dae of birth. it was removed after adding @Transient to the age in Student.java
                );

            Student alex = new Student(
//                    1L, We can remove this ID, the application will automatically add it.
                    "Alex",
                    "alex@gmail.com",
                    LocalDate.of(2004, JANUARY, 5)
//                    21
            );

            repository.saveAll(
                    List.of(mariam,alex)
            );

        };
    }

}
