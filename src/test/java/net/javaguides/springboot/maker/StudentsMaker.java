package net.javaguides.springboot.maker;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import net.javaguides.springboot.entity.Student;

public class StudentsMaker {

  public static Student buildStudentsMaker(long id) {
    return Student.builder()
        .id(id)
        .createTime(OffsetDateTime.now())
        .email("aa@qq.com")
        .firstName("zhang")
        .lastName("san")
        .gender(1)
        .belongClass(1)
        .introduce("我叫张三，今年22岁，性别男")
        .amount(new BigDecimal("10000.123"))
        .build();
  }

  public static Student buildStudentsMaker2() {
    return Student.builder()
        .id(2L)
        .createTime(OffsetDateTime.now())
        .email("bb@qq.com")
        .firstName("li")
        .lastName("si")
        .gender(1)
        .belongClass(1)
        .introduce("我叫李四，今年12岁，性别男")
        .build();
  }

  public static Student buildStudentsMaker3() {
    return Student.builder()
        .id(3L)
        .createTime(OffsetDateTime.now())
        .email("cc@qq.com")
        .firstName("wang")
        .lastName("wu")
        .gender(0)
        .belongClass(1)
        .introduce("我叫王五，今年23岁，性别女")
        .build();
  }

  public static Student buildStudentsMaker4() {
    return Student.builder()
        .id(4L)
        .createTime(OffsetDateTime.now())
        .email("dd@qq.com")
        .firstName("zhao")
        .lastName("liu")
        .gender(1)
        .belongClass(2)
        .introduce("我叫赵六，今年28岁，性别男")
        .build();
  }
}
