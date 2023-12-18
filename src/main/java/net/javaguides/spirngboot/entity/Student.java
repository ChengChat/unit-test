package net.javaguides.spirngboot.entity;

import static org.springframework.data.elasticsearch.annotations.FieldType.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.OffsetDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Field;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Jacksonized
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "students")
public class Student {

    @Id
    @org.springframework.data.annotation.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;
    private String introduce;
    private Integer gender;
    @Column(name = "belong_class")
    private Integer belongClass;
    private String email;
    @Field(type = Date, format = DateFormat.custom, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    private OffsetDateTime createTime;
}
