package com.glory.course_management.repository;

import com.glory.course_management.entity.Course;
import com.glory.course_management.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TeacherRepositoryTest {

   @Autowired
   private TeacherRepository teacherRepository;

    @Test
    public void saveTeacher(){
        Teacher teacher = Teacher.builder()
                .firstName("Chiminya")
                .lastName("Ndolo")
                .build();

        Course courseDBA = Course.builder()
                .credit(7)
                .title("DBA")
                .teacher(teacher)
                .build();
        Course courseJava = Course.builder()
                .credit(6)
                .title("Java")
                .teacher(teacher)
                .build();

        teacherRepository.save(teacher);
    }
}