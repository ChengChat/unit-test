package net.javaguides.springboot.service.impl;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.javaguides.springboot.entity.Student;
import net.javaguides.springboot.repository.StudentRepository;
import net.javaguides.springboot.service.StudentService;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class TeacherServiceImpl implements StudentService {

  private final StudentRepository studentRepository;

  @Override
  public Student save(Student student) {
    return studentRepository.save(student);
  }

  @Override
  public List<Student> findAll() {
    return studentRepository.findAll();
  }
}
