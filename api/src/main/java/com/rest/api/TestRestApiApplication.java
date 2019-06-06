package com.rest.api;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.rest.api.service.StorageService;

@SpringBootApplication
public class TestRestApiApplication implements CommandLineRunner {

	@Resource
	StorageService storageService;

	public static void main(String[] args) {
		SpringApplication.run(TestRestApiApplication.class, args);
	}

	@Override
	public void run(String... arg) throws Exception {
//		storageService.deleteAll();
//		storageService.init();
	}
}
