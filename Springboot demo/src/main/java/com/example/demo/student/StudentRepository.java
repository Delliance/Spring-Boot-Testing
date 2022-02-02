package com.example.demo.student;

import org.hibernate.sql.Select;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository //this is a repository because this class is reponsible for data access
//This is a generic repository, we have to specify T(type of object to work with) and the ID (for the type that we want)
public interface StudentRepository extends JpaRepository <Student, Long > { // this is long because we are going to use the id


//    This will be converted into and SQL:
//    SELECT * FROM student WHERE email = ?
//    or this can be also done with @Query
    @Query("SELECT s FROM Student s WHERE s.email = ?1") //The s is just a name line int aux = 1;
//    The student in the Query is the the Class, so we are accessing the email of that Class
    Optional<Student> findStudentByEmail(String email); //This here is to find a student by email, and if it exists in the database, it'll launch an exception

}
