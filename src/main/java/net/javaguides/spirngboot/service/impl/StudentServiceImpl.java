package net.javaguides.spirngboot.service.impl;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import net.javaguides.spirngboot.entity.Student;
import net.javaguides.spirngboot.repository.StudentRepository;
import net.javaguides.spirngboot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Slf4j
@Component
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
