package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

//@Component //Component is a general annotation, we don't actually want this class to be just a component, but a service so we use @Service
@Service //@Component and @Service are similar, @Service is more about resability
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

//        WE DON'T NEED THIS LIST ANYMORE, THIS IS JUST A STATIC LIST WE ARE GOING TO USE THE DATABASE
//    public List<Student> getStudents(){
//        return List.of(
////                Right now this is a static list, we want this to come from a database
//                new Student(
//                        1L,
//                        "Maria",
//                        "maria.jml@gmail.com",
//                        LocalDate.of(2000, Month.JANUARY, 5),
//                        21 //This age can also be done as the current year minus the dae of birth
//                )
//        );
//    }

    public List <Student> getStudents(){
//        The good part about SPRING data JPA is that we get all this methods, but our StudentRepository haven't implemented any.
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {

//      to go from this  studentRepository.findStudentByEmail(student.getEmail()) to the one in the next line, first select it, and press Ctrl+W, then Ctrl+Alt+V
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
        if (studentOptional.isPresent()){ //this is a simple validation to show if the extudent exists or not, there is not something like valid email or anything else
            throw new IllegalStateException("Email taken");
        }
//        System.out.println(student);
        studentRepository.save(student);

    }

    public void deleteStudent(long studentId) {

        boolean exists = studentRepository.existsById(studentId);

        if (!exists){
            throw new IllegalStateException("Student with ID "+studentId+" does not exist");
        }
        studentRepository.deleteById(studentId);

    }

    @Transactional //thsi entity goes into manage statement
    public void updateStudent(long studentId, String name, String email) {

        Student student = studentRepository.findById(studentId).orElseThrow(() -> new IllegalStateException("Student with ID \"+studentId+\" does not exist"));

        if (name != null && name.length() > 0 && !Objects.equals(student.getName(), name)){
            student.setName(name);
        }

        if (email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email)) {
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
            if (studentOptional.isPresent()) {
                throw new IllegalStateException("Email Taken");
            }
            student.setEmail(email);
        }

    }
}
