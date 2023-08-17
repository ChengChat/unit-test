package net.javaguides.spirngboot.repository;

import java.util.List;
import net.javaguides.spirngboot.entity.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@DataJpaTest
@TestPropertySource(locations = {"classpath:application-h2.yml"})
public class StudentRepositoryTest {

  @Autowired
  StudentRepository studentRepository;

  @Test
  public void findUserByUserName() {
    List<Student> all = studentRepository.findAll();
  }
}