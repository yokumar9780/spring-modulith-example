package com.example.spring_modulith;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(CustomTestcontainersConfiguration.class)
@SpringBootTest
class SpringModulithExampleApplicationTests {

	@Test
	void contextLoads() {
	}

}
