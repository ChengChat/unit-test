package net.javaguides.spirngboot.repository;

import net.javaguides.spirngboot.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StudentRepository extends JpaRepository<Student, Long> {
  // 这里我们自定义查询语句，必须使用HQL，即(Hibernate Query Language)
//  @Query(value = "select new Student(u.id, u.fastName, u.lastName) from Student u where u.fastName=:fastName")
//  Student findUserByFastName(String fastName);

}
