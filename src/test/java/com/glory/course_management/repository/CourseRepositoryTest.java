package com.glory.course_management.repository;

import com.glory.course_management.entity.Course;
import com.glory.course_management.entity.Student;
import com.glory.course_management.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseRepositoryTest {
    @Autowired
    private CourseRepository courseRepository;

    @Test
    public void printAllCourses(){
        List<Course> courses = courseRepository.findAll();
        System.out.println("All courses "+ courses);
    }

    @Test
    public void saveCourseWithTeacher(){
        Teacher teacher = Teacher.builder()
                .lastName("Tshinguli")
                .firstName("Sony")
                .build();

        Course course = Course.builder()
                .title("Java")
                .credit(6)
                .teacher(teacher)
                .build();

        courseRepository.save(course);
    }

    @Test
    public void findAllPagination(){
        Pageable firstWithThreeRecords = PageRequest.of(0,3);
        Pageable secondWithTwoRecords = PageRequest.of(1,2);

        List<Course> courses = courseRepository
                .findAll(secondWithTwoRecords)
                .getContent();

        long totalElements = courseRepository
                .findAll(secondWithTwoRecords)
                .getTotalElements();
        long totalPages = courseRepository
                .findAll(secondWithTwoRecords)
                .getTotalPages();
        System.out.println("Total pages "+ totalPages);
        System.out.println("Total Elements "+ totalElements);
        System.out.println("Courses "+ courses);
    }

    @Test
    public void findAllSorting(){
        Pageable sortByTitle = PageRequest
                .of(0,3, Sort.by("title"));

        Pageable sortByCreditDesc  = PageRequest
                .of(0,2, Sort.by("credit").descending());

        Pageable sortByTitleAndCreditDesc = PageRequest
                .of(0, 3, Sort.by("title")
                        .and(Sort.by("credit").descending()));
        List<Course> courses = courseRepository
                .findAll(sortByTitle)
                .getContent();
        System.out.println("Courses by title "+ courses);
    }
    @Test
    public void findByTitleContaining(){
        Pageable firstPagedFiveRecords = PageRequest.of(0, 5);

        List<Course> courses = courseRepository
                .findByTitleContaining("Ja", firstPagedFiveRecords).getContent();
        System.out.println("Courses containing Ja " + courses);
    }
    @Test
    public void saveCourseWithStudentAndTeacher(){
        Teacher teacher = Teacher.builder()
                .firstName("Glory")
                .lastName("Beya")
                .build();

        Course course = Course.builder()
                .credit(8)
                .title("DBA")
                .teacher(teacher)
                .build();

        Student student = Student.builder()
                .emailId("ben@gmail.com")
                .firstName("Ben")
                .lastName("Ntumba")
                .build();

        course.addStudent(student);

        courseRepository.save(course);
    }
}