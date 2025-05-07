package com.example.spring_modulith;

import com.example.SpringModulithExampleApplication;
import org.springframework.boot.SpringApplication;

public class TestSpringModulithExampleApplication {

	public static void main(String[] args) {
		SpringApplication.from(SpringModulithExampleApplication::main).with(CustomTestcontainersConfiguration.class).run(args);
	}

}
