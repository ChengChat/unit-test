//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package net.javaguides.springboot.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder(toBuilder = true)
@JsonInclude(Include.NON_NULL)
public class Response<T> {
  private Integer code;
  private String message;
  private T data;

  public static <T> Response<T> ok(T body) {
    return Response.<T>builder().code(HttpStatus.OK.value()).data(body).build();
  }

  public static <T> Response<T> failed(HttpStatus status, String message) {
    return Response.<T>builder().code(status.value()).message(message).build();
  }

}
