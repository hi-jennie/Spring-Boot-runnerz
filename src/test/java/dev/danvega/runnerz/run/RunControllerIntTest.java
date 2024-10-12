package dev.danvega.runnerz.run;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestClient;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

// this test will include the service layer——RestClient
// this is an integration test so we need to use @SpringBootTest
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RunControllerIntTest {
    RestClient restClient;
    @LocalServerPort
    private int randomServerPort;

    @BeforeEach
    void setUp() {
        restClient = RestClient.create("http://localhost:" + randomServerPort);
    }

    @Test
    void shouldFindAllRuns() {
        // here this isn't a mock,the restClient will talk to the real repository and retrieve the data
        List<Run> runs = restClient.get()
                .uri("/api/runs")
                .retrieve()
                .body(new ParameterizedTypeReference<List<Run>>() {
                });
        // my data.sql file has 4 runs
        assertEquals(4, runs.size());
    }
}