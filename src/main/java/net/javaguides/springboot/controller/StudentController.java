package net.javaguides.springboot.controller;

import net.javaguides.springboot.common.Response;
import net.javaguides.springboot.entity.Student;
import net.javaguides.springboot.remoteCall.StudentClient;
import net.javaguides.springboot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentClient studentClient;

    @Autowired
    private StudentService studentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Response<Student> createStudent(@RequestBody Student student){
        return Response.ok(studentService.save(student));
    }

    @GetMapping
    public Response<List<Student>> getAllStudents(){
        return Response.ok(studentService.findAll());
    }

    @GetMapping("{studentId}")
    public Response<Student> getStudents(@PathVariable("studentId") Long studentId){
        return studentClient.getValues(studentId);
    }
}
