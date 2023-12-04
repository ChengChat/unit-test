package net.javaguides.spirngboot.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.google.common.collect.Lists;
import net.javaguides.spirngboot.entity.Student;
import net.javaguides.spirngboot.maker.StudentsMaker;
import net.javaguides.spirngboot.repository.StudentRepository;
import net.javaguides.spirngboot.service.impl.StudentServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * 利用spring bean
 */
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
    ArgumentCaptor<Student> argumentCaptor = ArgumentCaptor.forClass(Student.class);
    when(studentRepository.save(argumentCaptor.capture())).thenReturn(StudentsMaker.buildStudentsMaker());

    Student save = studentService.save(StudentsMaker.buildStudentsMaker());

    assertThat(save).isNotNull();
    verify(studentRepository).save(any());
    Student value = argumentCaptor.getValue();
    assertThat(value).isNotNull();
    assertThat(value.getId()).isEqualTo(1);
  }

  @Test
  public void findAll() {
    when(studentRepository.findAll()).thenReturn(Lists.newArrayList(StudentsMaker.buildStudentsMaker()));

    List<Student> studentList = studentService.findAll();

    assertThat(studentList).isNotNull();
    assertThat(studentList.size()).isEqualTo(1);
  }
}