package net.javaguides.spirngboot.controller;

import net.javaguides.spirngboot.common.Response;
import net.javaguides.spirngboot.entity.Student;
import net.javaguides.spirngboot.repository.StudentRepository;
import net.javaguides.spirngboot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Response<Student> createStudent(@RequestBody Student student){
        return Response.ok(studentService.save(student));
    }

    @GetMapping
    public List<Student> getAllStudents(){
        return studentService.findAll();
    }
}
