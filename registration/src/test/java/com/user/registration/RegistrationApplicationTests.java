package com.user.registration;

import static org.mockito.Mockito.mockStatic;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RegistrationApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void testMain() {
		try (MockedStatic<SpringApplication> mockedSpringApplication = mockStatic(SpringApplication.class)) {
			RegistrationApplication.main(new String[]{});

			// Verify that SpringApplication.run was called with the correct arguments
			mockedSpringApplication.verify(() -> SpringApplication.run(RegistrationApplication.class, new String[]{}));
		}
	}

}
