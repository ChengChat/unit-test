package net.javaguides.springboot.remoteCall;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static wiremock.com.google.common.net.HttpHeaders.CONTENT_TYPE;
import static wiremock.org.apache.http.HttpStatus.SC_OK;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import net.javaguides.springboot.UnitTestApplication;
import net.javaguides.springboot.common.Response;
import net.javaguides.springboot.entity.Student;
import net.javaguides.springboot.util.JsonUtil;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UnitTestApplication.class)
@TestPropertySource(
    properties = {
        "student.service.name=ping-service",
    }
)
@EnableFeignClients(clients = StudentClient.class)
@AutoConfigureMockMvc
@ActiveProfiles("h2")
public class StudentClientTest {

  @ClassRule
  public static final WireMockRule wireMockRule = new WireMockRule(wireMockConfig().dynamicPort());

  @Autowired
  private MockMvc mockMvc;

  @BeforeClass
  public static void setUp() {
    System.setProperty("ping-service.ribbon.listOfServers",
        "http://127.0.0.1:" + wireMockRule.port());

    stubFor(get(urlEqualTo("/remote/students/1"))
        .willReturn(aResponse()
            .withStatus(SC_OK)
            .withHeader(CONTENT_TYPE, APPLICATION_JSON_VALUE)
            .withBody(JsonUtil.toJson(
                Response.ok(Student.builder()
                        .email("aa@qq.com")
                        .gender(1)
                        .firstName("zhang")
                    .build())))));
  }

  @AfterClass
  public static void tearDown() {
    System.clearProperty("ping-service.ribbon.listOfServers");
  }

  @Test
  public void shouldGetMessageInChinese() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/api/students/1")
            .contentType(APPLICATION_JSON))
        .andDo(print())
        .andExpect(jsonPath("$.code", is(200)))
        .andExpect(jsonPath("$.data.email", is("aa@qq.com")))
        .andExpect(jsonPath("$.data.gender", is(1)))
        .andExpect(jsonPath("$.data.firstName", is("zhang")));
  }
}


