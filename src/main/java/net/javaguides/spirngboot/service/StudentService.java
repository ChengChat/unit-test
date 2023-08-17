package net.javaguides.spirngboot.service;

import java.util.List;
import net.javaguides.spirngboot.entity.Student;

public interface StudentService {

  Student save(Student student);

  List<Student> findAll();
}
