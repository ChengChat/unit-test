package net.javaguides.spirngboot.maker;

import net.javaguides.spirngboot.entity.Student;

public class StudentsMaker {

  public static Student buildStudentsMaker() {
    return Student.builder().id(1L).build();
  }
}
