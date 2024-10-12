package dev.danvega.runnerz.run;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
// 	@JdbcTest：专注于数据库层的测试，避免加载多余组件，轻量高效。
//	@SpringBootTest：用于集成测试，确保所有组件和层次能够正确协同工作。

@JdbcTest
@Import(JdbcRunRepository.class)
/*  加@Import的原因
	1.	包不在扫描范围内
	2.	JdbcRunRepository缺少必要的注解（如@Repository）
	3.	@JdbcTest的裁剪机制导致它没有加载到测试上下文中
 */
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class JdbcRunRepositoryTest {
    @Autowired
    JdbcRunRepository repository;

    @BeforeEach
    void setUp() {
        repository.create(new Run(1,
                "First Run",
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(30),
                5,
                Location.INDOOR));

        repository.create(new Run(2,
                "Second Run",
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(60),
                10,
                Location.INDOOR));

    }

    @Test
    void shouldFindAllRuns() {
        List<Run> runs = repository.findAll();
        assertEquals(2, runs.size(), "should have returned 2 runs");
    }

    @Test
    void shouldFindRunWithValidId() {
        var run = repository.findById(1).get();
        assertEquals("First Run", run.title());
        assertEquals(5, run.miles());
    }

    @Test
    void shouldNotFindRunWithInvalidId() {
        var run = repository.findById(3);
        assertTrue(run.isEmpty());
    }

    @Test
    void shouldCreateNewRun() {
        repository.create(new Run(3,
                "Third Run",
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(30),
                5,
                Location.INDOOR));
        List<Run> runs = repository.findAll();
        assertEquals(3, runs.size());
    }

    @Test
    void shouldUpdateRun() {
        repository.update(new Run(1,
                "Updated First Run",
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(30),
                15,
                Location.INDOOR), 1);
        Run run = repository.findById(1).get();
        assertEquals(15, run.miles());
    }

    @Test
    void shouldDeleteRun() {
        repository.delete(1);
        List<Run> runs = repository.findAll();
        assertEquals(1, runs.size());
    }

}