package net.javaguides.spirngboot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class UnitTestApplication {

	public static void main(String[] args) {
		log.warn("我也跟着启动了");
		SpringApplication.run(UnitTestApplication.class, args);
	}

}
