package net.javaguides.spirngboot.repository;

import java.util.List;
import net.javaguides.spirngboot.entity.Student;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@DataJpaTest
@ActiveProfiles({"h2"})
//@TestPropertySource(locations = {"classpath:application-h2.yml"})
public class StudentRepositoryTest {

  @BeforeClass
  public static void beforeClass() throws Exception {
    System.setProperty("spring.datasource.data", "classpath*:sql/data-student.sql");
  }

  @AfterClass
  public static void afterClass() throws Exception {
    System.clearProperty("spring.datasource.data");
  }

  @Autowired
  StudentRepository studentRepository;

  @Test
  public void findUserByUserName() {
    List<Student> all = studentRepository.findAll();

    assertThat(all).isNotNull();
    assertThat(all.get(0).getFirstName()).isEqualTo("张三");
  }
}