package net.javaguides.springboot.service;

import java.util.List;
import net.javaguides.springboot.entity.Student;

public interface StudentService {

  Student save(Student student);

  List<Student> findAll();
}
