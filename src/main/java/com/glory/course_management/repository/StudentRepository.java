package com.glory.course_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.glory.course_management.entity.Student;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
	
	Set<Student> findByLastName(String lastName);
	Set<Student> findAllByFirstNameContaining(String name);
	Set<Student> findAllByGuardianName(String name);
	//List<Student> findByLastNameNotNull(String lastName);
	Student findByFirstNameAndLastName(String firstName, String lastName);

	//JPQL
	@Query("select s from Student s where s.emailId = ?1")
    Student getStudentByEmailAddress(String emailID);

	@Transactional
	@Modifying
	@Query("update Student set firstName =?1 where emailId = ?2")
	int updateStudentNameByEmailId(String firstName, String emailID);
}
