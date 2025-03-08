package com.project.broccoli;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.smStart.mapper")  // 패키지 경로 확인!
public class BroccoliApplication {

	public static void main(String[] args) {
		SpringApplication.run(BroccoliApplication.class, args);
	}

}
