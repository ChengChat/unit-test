package net.javaguides.springboot.repository;

import net.javaguides.springboot.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
  // 这里我们自定义查询语句，必须使用HQL，即(Hibernate Query Language)
//  @Query(value = "select new Student(u.id, u.fastName, u.lastName) from Student u where u.fastName=:fastName")
//  Student findUserByFastName(String fastName);

}
