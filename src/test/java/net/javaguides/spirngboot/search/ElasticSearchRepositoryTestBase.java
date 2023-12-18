package net.javaguides.spirngboot.search;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.File;
import java.io.IOException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.testcontainers.elasticsearch.ElasticsearchContainer;
import org.testcontainers.shaded.org.apache.commons.io.FileUtils;


public class ElasticSearchRepositoryTestBase {

  @ClassRule
  public static final ElasticsearchContainer ES = new ElasticsearchContainer(
      "docker.elastic.co/elasticsearch/elasticsearch:7.10.0");
  @Autowired
  protected ElasticsearchRestTemplate operations;

  @BeforeClass
  public static void beforeClass() {
    System.setProperty("spring.elasticsearch.rest.uris", "http://" + ES.getHttpHostAddress());
  }

  @AfterClass
  public static void afterClass() {
    System.clearProperty("spring.elasticsearch.rest.uris");
  }

  protected String json(String name) throws IOException {
    String file = this.getClass().getClassLoader().getResource(name).getPath();
    return FileUtils.readFileToString(new File(file), UTF_8);
  }

}
