package com.spring.security.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.security.model.Student;
import com.spring.security.service.SecurityService;


@RestController
@RequestMapping("/students")
public class SecurityController {
    @Autowired
    private SecurityService securityService;

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping
    public List<Student> getAllStudents() {
        return securityService.getAllStudentList();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public Student saveStudent(@RequestBody Student student) {
        return securityService.saveStudent(student);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/{roll}")
    public Student getStudent(@PathVariable int roll) {
        return securityService.getStudent(roll);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{roll}")
    public void deleteStudent(@PathVariable int roll) {
        securityService.deleteStudent(roll);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{roll}")
    public Student updateStudent(@RequestBody Student student, @PathVariable int roll) {
        return securityService.updateStudent(student, roll);
    }
}
