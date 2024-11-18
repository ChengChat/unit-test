package net.javaguides.springboot.search;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.elasticsearch.index.query.QueryBuilders.boolQuery;
import static org.elasticsearch.index.query.QueryBuilders.matchQuery;
import static org.elasticsearch.index.query.QueryBuilders.termQuery;
import static org.testcontainers.shaded.org.awaitility.Awaitility.waitAtMost;

import com.google.common.collect.Lists;
import java.io.IOException;
import java.util.List;
import net.javaguides.springboot.entity.Student;
import net.javaguides.springboot.maker.StudentsMaker;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchDataAutoConfiguration;
import org.springframework.boot.autoconfigure.data.elasticsearch.ReactiveElasticsearchRestClientAutoConfiguration;
import org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchRestClientAutoConfiguration;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.IndexOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.document.Document;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ImportAutoConfiguration({
    ElasticsearchRestClientAutoConfiguration.class,
    ReactiveElasticsearchRestClientAutoConfiguration.class,
    ElasticsearchDataAutoConfiguration.class,
})
public class ElasticSearchNewsRepositoryTest extends ElasticSearchRepositoryTestBase {

  private static final String INDEX_NAME = "i_product";
  private final IndexCoordinates index = IndexCoordinates.of(INDEX_NAME);
  private final List<Student> students = Lists.newArrayList(StudentsMaker.buildStudentsMaker(1L),
      StudentsMaker.buildStudentsMaker2(),
      StudentsMaker.buildStudentsMaker3(),
      StudentsMaker.buildStudentsMaker4()
  );

  @Before
  public void setUp() throws IOException {
    indexOps(operations).delete();
    if (!indexOps(operations).exists()) {
      Document settings = Document.parse(json("schema/settings.json"));
      indexOps(operations).create(settings);

      Document mappings = Document.parse(json("schema/mappings_student.json"));
      indexOps(operations).putMapping(mappings);
    }

    operations.save(students, index);
  }

  @After
  public void tearDown() {
    operations.delete("1", index);
  }

  @Test
  public void shouldGetAllMatchingNews() {
    waitAtMost(5, SECONDS).untilAsserted(
        () -> assertThat(operations.search(Query.findAll(), Student.class, index)).isNotEmpty());
    operations.search(Query.findAll(), Student.class, index);

    // introduce like %男% && (lastName = liu || lastName = wu)
    BoolQueryBuilder queryBuilder = boolQuery()
        .must(matchQuery("introduce", "男"))
        .should(termQuery("lastName", "liu"))
        .should(termQuery("lastName", "wu"))
        .minimumShouldMatch(1)
        ;

    NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
        .withQuery(queryBuilder)
        .build();

    SearchHits<Student> search = operations.search(searchQuery, Student.class, index);
    assertThat(search).hasSize(1);
  }

  private IndexOperations indexOps(ElasticsearchRestTemplate operations) {
    return operations.indexOps(index);
  }
}
