package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path= "api/v1/students") //this is the normal direction for a webpage, this path uses the local host, so http://localhost:8080/api/v1/students
//If you try to hit the endpoint host in the browser http://localhost:8080/ , you'll get an error, you need to use the full path
public class StudentController {

//    To be able to autowire this, we have to say that the StudentService.java should be instantiated, to do it, we use @Component on Student.Service.java
    private final StudentService studentService;

//    This autowired means that the  StudentService from the "private final StudentService studentService;"  should be autowired to the method
    @Autowired
    public StudentController(StudentService studentService) {
        // There is not an instance of studentService, so instead we have to create a new one so "new studentService" instead of just "studentService"
        // However we have to avoid this: "this.studentService = new StudentService();" and use as much as possible dependency injection
//        this.studentService = studentService;
//        To make a dependency injection we write @Autowired on top of this constructor
        this.studentService = studentService;
    }

    @GetMapping
    //This allows for this method to work as a REST endpoint, this GET means what is GET from the webpage with the server for this API
    public List<Student> getStudents(){
////////////////////////////////////// This section of the method was moved to the StudentService Class the service class, or layer, is the one in charge of the service logic
//        return List.of(
//                new Student(
//                        1L,
//                        "Maria",
//                        "maria.jml@gmail.com",
//                        LocalDate.of(2000, Month.JANUARY, 5),
//                        21 //This age can also be done as the current year minus the dae of birth
//                )
//        );

        //Since the method that was here was moved to the StudentService, now we call it
        return studentService.getStudents();
    }

    @PostMapping
//    here we want to add a new student, and if the email exist it will launch an exeption
    public void registerNewStudent(@RequestBody Student student){
        studentService.addNewStudent(student);
    }

    @DeleteMapping(path = "{studentId}")
//    This method here is the DELETE to delete students
    public void deleteStudent (@PathVariable("studentId") long studentId){

        studentService.deleteStudent(studentId);

    }

    @PutMapping (path = "{studentId}")
    public void updateStudent (@PathVariable("studentId") long studentId, @RequestParam(required = false) String name, @RequestParam(required = false) String email){

        studentService.updateStudent(studentId, name, email);

    }

}
