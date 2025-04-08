package net.javaguides.springboot.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.google.common.collect.Lists;
import java.math.BigDecimal;
import java.util.List;
import net.javaguides.springboot.entity.Student;
import net.javaguides.springboot.maker.StudentsMaker;
import net.javaguides.springboot.repository.StudentRepository;
import net.javaguides.springboot.service.impl.StudentServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

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
    when(studentRepository.save(argumentCaptor.capture())).thenReturn(
        StudentsMaker.buildStudentsMaker(1L));

    Student save = studentService.save(StudentsMaker.buildStudentsMaker(1L));

    assertThat(save).isNotNull();
    verify(studentRepository).save(any());
    Student value = argumentCaptor.getValue();
    assertThat(value).isNotNull();
    assertThat(value.getId()).isEqualTo(1);
  }

  @Test
  public void saveWhenArgThat() {
    when(studentRepository.save(assertStudent())).thenReturn(
        StudentsMaker.buildStudentsMaker(1L));

    Student save = studentService.save(StudentsMaker.buildStudentsMaker(1L));

    assertThat(save).isNotNull();
    verify(studentRepository).save(assertStudent());
  }

  private static Student assertStudent() {
    return argThat(student -> {
      assertThat(student.getId()).isEqualTo(1);
      return true;
    });
  }

  @Test
  public void findAll() {
    List<Student> students = Lists.newArrayList(StudentsMaker.buildStudentsMaker(1L),
        StudentsMaker.buildStudentsMaker(2L));
    when(studentRepository.findAll()).thenReturn(students);

    List<Student> studentList = studentService.findAll();

    assertThat(studentList).isNotNull();
    assertThat(studentList.size()).isEqualTo(2);
    assertThat(studentList)
        .usingComparatorForType(BigDecimal::compareTo, BigDecimal.class)
        .containsExactlyInAnyOrder(students.toArray(new Student[]{}));
  }
}