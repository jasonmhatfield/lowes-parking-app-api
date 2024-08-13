package com.lowes.lowesparkingappapi;

import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;

@SpringBootTest
class ApplicationTest {

    @Test
    void testMain() {
        try (var mockedSpringApplication = mockStatic(SpringApplication.class)) {
            mockedSpringApplication.when(() -> SpringApplication.run(Application.class, new String[]{}))
                    .thenReturn(mock(ConfigurableApplicationContext.class));

            Application.main(new String[]{});

            mockedSpringApplication.verify(() -> SpringApplication.run(Application.class, new String[]{}));
        }
    }
}
