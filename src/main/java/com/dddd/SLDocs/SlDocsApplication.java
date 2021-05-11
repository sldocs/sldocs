package com.dddd.SLDocs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileInputStream;
import java.io.IOException;

@SpringBootApplication
public class SlDocsApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(SlDocsApplication.class, args);
		final String excelPath = "C:\\Software\\Trash\\IntelliJ IDEA 2020.3.2\\workspace\\POI_test2\\src\\main\\resources\\SL.xlsx";
	}

}
