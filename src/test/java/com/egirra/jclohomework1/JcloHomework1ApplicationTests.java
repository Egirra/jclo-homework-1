package com.egirra.jclohomework1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class JcloHomework1ApplicationTests {
    @Autowired
    TestRestTemplate restTemplate;
    private static final GenericContainer<?> devApp = new GenericContainer<>("devApp").withExposedPorts(8080);
    private static final GenericContainer<?> prodApp = new GenericContainer<>("prodApp").withExposedPorts(8081);

    @BeforeAll
    public static void setUp() {
        devApp.start();
        prodApp.start();
    }

    @Test
    void contextLoads() {
        ResponseEntity<String> devEntity = restTemplate.
                getForEntity("http://localhost:" + devApp.getMappedPort(8080), String.class);
        System.out.println(devEntity.getBody());

        ResponseEntity<String> prodEntity = restTemplate.
                getForEntity("http://localhost:" + prodApp.getMappedPort(8081), String.class);
        System.out.println(prodEntity.getBody());

        Assertions.assertEquals("Current profile is dev", devEntity.getBody());
        Assertions.assertEquals("Current profile is production", prodEntity.getBody());
    }
}