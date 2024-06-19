package net.javaguides.spirngboot.controller;

import static net.javaguides.spirngboot.maker.StudentsMaker.buildStudentsMaker;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.google.common.collect.Lists;
import net.javaguides.spirngboot.entity.Student;
import net.javaguides.spirngboot.service.StudentService;
import net.javaguides.spirngboot.util.JsonUtil;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(StudentController.class)
//@ImportAutoConfiguration({WingWebAutoConfiguration.class, WebEnumConfig.class})
class StudentControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private StudentService studentService;


  @Test
  void createStudent() throws Exception {
    Student student = buildStudentsMaker(1L);
    when(studentService.save(any())).thenReturn(student);

    mockMvc.perform(post("/api/students")
            .content(JsonUtil.toJson(student))
            .contentType(APPLICATION_JSON))
//        .andDo(print())
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.code", is(200)))
        .andExpect(jsonPath("$.data.id", is(1)));
  }



  @Test
  void getAllStudents() throws Exception {
    Student student = buildStudentsMaker(1L);
    when(studentService.findAll()).thenReturn(Lists.newArrayList(student));

    mockMvc.perform(get("/api/students")
            .contentType(APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.code", is(200)))
        .andExpect(jsonPath("$.data[0].id", is(1)));
  }
}