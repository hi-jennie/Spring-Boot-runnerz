package dev.danvega.runnerz.run;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class InMemoryRunRepositoryTest {

    InMemoryRunRepository repository;

    // 表示在每个测试方法执行之前都要执行标注了这个注解的方法
    @BeforeEach
    void setUp() {
        repository = new InMemoryRunRepository();

        repository.create(new Run(1,
                "First Run",
                LocalDateTime.now(),
                LocalDateTime.now().plus(30, ChronoUnit.MINUTES),
                5,
                Location.INDOOR,null));

        repository.create(new Run(2,
                "Second Run",
                LocalDateTime.now(),
                LocalDateTime.now().plus(60, ChronoUnit.MINUTES),
                10,
                Location.INDOOR,null));

    }

    @Test
    void shouldFindAllRuns() {
        List<Run> runs = repository.findAll();
        assertEquals(2, runs.size(),"should have returned 2 runs");
    }

    @Test
    void shouldFindRunWithValidId(){
        var run = repository.findById(1).get();
        assertEquals("First Run", run.title());
        assertEquals(5, run.miles());
    }

    @Test
    void shouldNotFindRunWithInvalidId(){
        // RunNotFoundException.class represents the exception that is expected to be thrown
        // if the second argument does not throw the exception or throw other exception, the test will fail
        RunNotFoundException notFoundException = assertThrows(
                RunNotFoundException.class,
                () -> repository.findById(3).get()
        );
        assertEquals("Run Not Found", notFoundException.getMessage());
    }


}