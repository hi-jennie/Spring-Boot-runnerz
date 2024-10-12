package dev.danvega.runnerz.run;

class InMemoryRunRepositoryTest {

//    InMemoryRunRepository repository;
//
//    // 表示在每个测试方法执行之前都要执行标注了这个注解的方法
//    @BeforeEach
//    void setUp() {
//        repository = new InMemoryRunRepository();
//
//        repository.create(new Run(1,
//                "First Run",
//                LocalDateTime.now(),
//                LocalDateTime.now().plusMinutes(30),
//                5,
//                Location.INDOOR, null));
//
//        repository.create(new Run(2,
//                "Second Run",
//                LocalDateTime.now(),
//                LocalDateTime.now().plusMinutes(60),
//                10,
//                Location.INDOOR, null));
//
//    }
//
//    @Test
//    void shouldFindAllRuns() {
//        List<Run> runs = repository.findAll();
//        assertEquals(2, runs.size(), "should have returned 2 runs");
//    }
//
//    @Test
//    void shouldFindRunWithValidId() {
//        var run = repository.findById(1).get();
//        assertEquals("First Run", run.title());
//        assertEquals(5, run.miles());
//    }
//
//    @Test
//    void shouldNotFindRunWithInvalidId() {
//        // RunNotFoundException.class represents the exception that is expected to be thrown
//        // if the second argument does not throw the exception or throw other exception, the test will fail
//        RunNotFoundException notFoundException = assertThrows(
//                RunNotFoundException.class,
//                () -> repository.findById(3).get()
//        );
//        assertEquals("Run Not Found", notFoundException.getMessage());
//    }
//
//    @Test
//    void shouldCreateNewRun() {
//        repository.create(new Run(3,
//                "Third Run",
//                LocalDateTime.now(),
//                LocalDateTime.now().plusMinutes(30),
//                5,
//                Location.INDOOR, null));
//        List<Run> runs = repository.findAll();
//        assertEquals(3, runs.size());
//    }
//
//    @Test
//    void shouldUpdateRun() {
//        repository.update(new Run(1,
//                "Updated First Run",
//                LocalDateTime.now(),
//                LocalDateTime.now().plusMinutes(30),
//                15,
//                Location.INDOOR, null), 1);
//        Run run = repository.findById(1).get();
//        assertEquals(15, run.miles());
//    }
//
//    @Test
//    void shouldDeleteRun() {
//        List<Run> runs = repository.findAll();
//        assertEquals(2, runs.size());
//        repository.delete(2);
//        assertEquals(1, runs.size());
//    }
}