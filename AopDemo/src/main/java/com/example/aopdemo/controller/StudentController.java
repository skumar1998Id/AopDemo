package com.example.aopdemo.controller;

import com.example.aopdemo.entity.Student;
import com.example.aopdemo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/employees")
    public ResponseEntity<?>fetchAllEmployees() {
        return new ResponseEntity<List<Student>>(studentService.getAllEmployees(), HttpStatus.OK);
    }

    @PostMapping("/employees")
    public ResponseEntity<?>fetchAllEmployees( @RequestBody Student student) {
        return new ResponseEntity<Student>(studentService.addEmployee(student), HttpStatus.OK);
    }
}
