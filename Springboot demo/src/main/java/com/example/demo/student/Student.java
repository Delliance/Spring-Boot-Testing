package com.example.demo.student;

//Be sure that you're importing from javax.persistence
import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;

//All the @ here at the start are used to create the database with this information
//In short we mapped this student info into the database

@Entity
@Table
public class Student {
    @Id
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )

    private Long id;
    private String name;
    private String email;
    private LocalDate dateOfBirth;
    @Transient // this means that age is not necessary to be a column in the database, we can calculate it with the date of birth
    private int age;

    public Student() {
    }

    public Student(Long id,
                   String name,
                   String email,
                   LocalDate dateOfBirth
//                   int age //we removed age with @transient, so we can remove it from the constructors
    ) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
//        this.age = age;
    }

    public Student(String name,
                   String email,
                   LocalDate dateOfBirth
//                   int age
    ) {
        this.name = name;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
//        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getAge() {
//        return age; // we now calculate it with the date of birth
        return Period.between(this.dateOfBirth, LocalDate.now()).getYears();
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", age=" + age +
                '}';
    }
}
