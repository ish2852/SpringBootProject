package com.sns.prj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class CodePressoSbApplication {

	public static void main(String[] args) {
		SpringApplication.run(CodePressoSbApplication.class, args);
	}

}
