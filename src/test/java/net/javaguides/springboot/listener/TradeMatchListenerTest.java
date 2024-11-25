package net.javaguides.springboot.listener;

import net.javaguides.springboot.maker.StudentsMaker;
import net.javaguides.springboot.service.StudentService;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.rule.EmbeddedKafkaRule;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static java.util.concurrent.TimeUnit.SECONDS;
import static net.javaguides.springboot.listener.TradeMatchListener.MSG_TOPIC;
import static net.javaguides.springboot.util.JsonUtil.toJson;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.testcontainers.shaded.org.awaitility.Awaitility.waitAtMost;

@RunWith(SpringRunner.class)
@ImportAutoConfiguration({
    KafkaAutoConfiguration.class,
    JacksonAutoConfiguration.class,
})
@Import({TradeMatchListener.class})
@TestPropertySource(properties = {
    "spring.kafka.consumer.group-id=unit-test"
})
public class TradeMatchListenerTest {


  @ClassRule
  public static final EmbeddedKafkaRule kafka = new EmbeddedKafkaRule(
      1,
      true,
      MSG_TOPIC);

  @Autowired
  private KafkaTemplate<String, Object> kafkaTemplate;

  @BeforeClass
  public static void beforeClass() {
    System.setProperty("spring.kafka.bootstrap-servers", kafka.getEmbeddedKafka().getBrokersAsString());
//    System.setProperty("spring.kafka.producer.value-serializer", "org.apache.kafka.common.serialization.StringSerializer");

  }

  @AfterClass
  public static void afterClass() {
    System.clearProperty("spring.kafka.bootstrap-servers");

  }


  @MockBean
  private StudentService studentService;

  @Test
  public void onSaveStudent() {
    kafkaTemplate.send(MSG_TOPIC, toJson(StudentsMaker.buildStudentsMaker(1L)));

    waitAtMost(5, SECONDS).untilAsserted(
        () -> verify(studentService).save(any()));
  }
}