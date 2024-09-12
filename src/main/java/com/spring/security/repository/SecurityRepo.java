package com.spring.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.security.model.Student;


public interface SecurityRepo extends JpaRepository<Student,Integer>{
	
}
