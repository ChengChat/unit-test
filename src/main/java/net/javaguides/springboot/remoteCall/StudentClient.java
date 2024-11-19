package net.javaguides.springboot.remoteCall;

import net.javaguides.springboot.common.Response;
import net.javaguides.springboot.entity.Student;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "${student.service.name}", path = "/remote", contextId = "student-client")
public interface StudentClient {


  @GetMapping("/students/{studentId}")
  Response<Student> getValues(@PathVariable("studentId") Long studentId);
}
