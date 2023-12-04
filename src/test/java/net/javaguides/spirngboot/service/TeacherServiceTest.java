package net.javaguides.spirngboot.service;

import net.javaguides.spirngboot.entity.Student;
import net.javaguides.spirngboot.repository.StudentRepository;
import net.javaguides.spirngboot.service.impl.TeacherServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * 利用构造函数
 */
public class TeacherServiceTest {

  StudentRepository studentRepository = Mockito.mock(StudentRepository.class);

  StudentService studentService = new TeacherServiceImpl(studentRepository);

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