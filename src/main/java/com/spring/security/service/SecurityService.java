package com.spring.security.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.security.model.Student;
import com.spring.security.repository.SecurityRepo;
@Service
public class SecurityService {
    @Autowired
    private SecurityRepo securityRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<Student> getAllStudentList() {
        return securityRepo.findAll();
    }

    public Student saveStudent(Student student) {
        student.setPassword(passwordEncoder.encode(student.getPassword()));
        return securityRepo.save(student);
    }

    public Student getStudent(int roll) {
        return securityRepo.findById(roll).orElse(null);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void deleteStudent(int roll) {
        securityRepo.deleteById(roll);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public Student updateStudent(Student student, int roll) {
        if (securityRepo.existsById(roll)) {
            student.setRoll(roll);
            return securityRepo.save(student);
        }
        return null;
    }
}
