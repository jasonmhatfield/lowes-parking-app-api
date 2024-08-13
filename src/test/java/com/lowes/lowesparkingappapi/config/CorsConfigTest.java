package com.lowes.lowesparkingappapi.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@WebAppConfiguration
public class CorsConfigTest {

    @Autowired
    private CorsConfig corsConfig;

    @Test
    public void testCorsConfigurerBean() {
        WebMvcConfigurer configurer = corsConfig.corsConfigurer();
        assertNotNull(configurer);
    }
}
