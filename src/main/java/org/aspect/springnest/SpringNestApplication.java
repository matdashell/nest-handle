package org.aspect.springnest;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
@RequiredArgsConstructor
public class SpringNestApplication {

	private final FakeService fakeService;

	//use breakpoints here
	@PostConstruct
	public void init() {
		fakeService.test();
		String test2 = fakeService.test2();
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringNestApplication.class, args);
	}

}
