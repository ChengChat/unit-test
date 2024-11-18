package net.javaguides.springboot.listener;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.javaguides.springboot.entity.Student;
import net.javaguides.springboot.service.StudentService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@AllArgsConstructor
@Component
public class TradeMatchListener {

  public static final String MSG_TOPIC = "student-add-topic";
  private final StudentService studentService;


  @KafkaListener(topics = MSG_TOPIC)
  void onSaveStudent(Student student) {
    log.info("Received save student event : {}", student);
    try {
      studentService.save(student);
    } catch (Exception e) {
      log.error("Unexpected Exception caught when save student, exception:", e);
    }
    log.info("Handled save student event : {}", student);
  }
}
