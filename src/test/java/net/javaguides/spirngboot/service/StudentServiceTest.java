package net.javaguides.spirngboot.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import net.javaguides.spirngboot.entity.Student;
import net.javaguides.spirngboot.repository.StudentRepository;
import net.javaguides.spirngboot.service.impl.StudentServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@Import({StudentServiceImpl.class})
//@ImportAutoConfiguration({StudentServiceImpl.class})
public class StudentServiceTest {

  @MockBean
  StudentRepository studentRepository;
  @Autowired
  StudentService studentService;

  @Test
  public void save() {
    when(studentRepository.save(any())).thenReturn(Student.builder().build());

    Student save = studentService.save(Student.builder().build());

    Assertions.assertThat(save).isNotNull();
    verify(studentRepository).save(any());
  }

  @Test
  public void findAll() {
  }
}