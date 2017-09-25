package com.fileexample.excel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class ExcelApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExcelApplication.class, args);
	}
}
