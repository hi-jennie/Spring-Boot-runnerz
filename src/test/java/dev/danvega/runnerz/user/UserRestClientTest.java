package dev.danvega.runnerz.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RestClientTest(UserRestClient.class) // it will disable full autoconfiguration and instead apply only configuration relevant to REST clients.
class UserRestClientTest {
    @Autowired
    MockRestServiceServer server;
    @Autowired
    UserRestClient client;
    @Autowired
    ObjectMapper objectMapper;

    @Test
    void shouldFindAllUsers() throws JsonProcessingException {
        User user = new User(1,
                "Leanne Graham",
                "lgraham",
                "lgraham@gamil.com",
                new Address("Kulas Light", "Apt. 556", "Gwenborough", "92998-3874", new Geo("-37.3159", "81.1496")),
                "1-770-736-8031 x56442",
                "hildegard.org",
                new Company("Romaguera-Crona", "Multi-layered client-server neural-net", "harness real-time e-markets"));

        List<User> users = List.of(user);

        this.server.expect(requestTo("https://jsonplaceholder.typicode.com/users"))
                .andRespond(withSuccess(objectMapper.writeValueAsString(users), MediaType.APPLICATION_JSON));

        // in findAll() method, it will convert the JSON response to a List<User> object.
        List<User> result = client.findAll();
        assertEquals(users, result);
    }

    @Test
    void shouldFindUserById() throws JsonProcessingException {
        User user = new User(1,
                "Leanne Graham",
                "lgraham",
                "lgraham@gmail.com",
                new Address("Kulas Light", "Apt. 556", "Gwenborough", "92998-3874", new Geo("-37.3159", "81.1496")),
                "1-770-736-8031 x56442",
                "hildegard.org",
                new Company("Romaguera-Crona", "Multi-layered client-server neural-net", "harness real-time e-markets"));

        this.server.expect(requestTo("https://jsonplaceholder.typicode.com/users/1"))
                .andRespond(withSuccess(objectMapper.writeValueAsString(user), MediaType.APPLICATION_JSON));

        User result = client.findById(1);
        assertEquals(user, result);
        assertEquals(result.name(), "Leanne Graham");
        assertEquals(result.username(), "lgraham");
        assertEquals(result.email(), "lgraham@gmail.com");
        assertAll("address",
                () -> assertEquals(result.address().street(), "Kulas Light"),
                () -> assertEquals(result.address().suite(), "Apt. 556"),
                () -> assertEquals(result.address().city(), "Gwenborough"),
                () -> assertEquals(result.address().zipcode(), "92998-3874"),
                () -> assertAll("geo",
                        () -> assertEquals(result.address().geo().lat(), "-37.3159"),
                        () -> assertEquals(result.address().geo().lng(), "81.1496")
                )
        );
        assertEquals(result.phone(), "1-770-736-8031 x56442");
        assertEquals(result.website(), "hildegard.org");

    }

}