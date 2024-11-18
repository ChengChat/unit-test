package net.javaguides.springboot.service.impl;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import net.javaguides.springboot.entity.Student;
import net.javaguides.springboot.repository.StudentRepository;
import net.javaguides.springboot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Primary
public class StudentServiceImpl implements StudentService {

  @Autowired
  private StudentRepository studentRepository;

  @Override
  public Student save(Student student) {
    return studentRepository.save(student);
  }

  @Override
  public List<Student> findAll() {
    return studentRepository.findAll();
  }
}
