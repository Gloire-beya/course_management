package com.glory.course_management.repository;

import com.glory.course_management.entity.Guardian;
import com.glory.course_management.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void saveStudent(){

        Student student = Student.builder()
                .emailId("gloweb78@gmail.com")
                .firstName("Gloire")
                .lastName("Beya")
                //.gardianEmail("gloweb78@gmail.com")
                //.gardianName("Gloire")
                //.gardianMobile("1234567890")
                .build();
        studentRepository.save(student);
    }

    @Test
    public void saveStudentWithGuardianDetails(){
        Guardian guardian = Guardian.builder()
                .email("gloweb78@gmail.com")
                .name("Gloire")
                .mobile("7890056987")
                .build();

        Student student = Student.builder()
                .firstName("Teboho")
                .lastName("Malimabe")
                .emailId("teboho@gmail.com")
                .guardian(guardian)
                .build();
       studentRepository.save(student);
    }

    @Test
    public void findStudentByLastName(){
        Set<Student> students = studentRepository.findByLastName("Malimabe");
        System.out.println("Student by last name "+ students);
    }
    @Test
    public void printAllStudent(){
        List<Student> studentList = studentRepository.findAll();
        System.out.println("Student list " + studentList);
    }

    @Test
    public void updateStudentNameByEmailID(){
        studentRepository.updateStudentNameByEmailId("Tebzile", "teboho@gmail.com");
    }
}