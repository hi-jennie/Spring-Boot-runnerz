package dev.danvega.runnerz.run;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RunRepository{
    private static final Logger log = LoggerFactory.getLogger(RunRepository.class);
    // jdbc is more like a bridge between java and database
    private final JdbcClient jdbcClient;
    // dependency injection
    public RunRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public List<Run> findAll(){
        return jdbcClient.sql("select * from run")
                .query(Run.class)// map to Run class
                .list();
    }
}

/* in memory database
@Repository
public class RunRepository {
    private List<Run> runs = new ArrayList<>();

    List<Run> findAll() {
        return runs;
    }

    @PostConstruct
    private void init(){
        runs.add(new Run(1,
                "First Run",
                LocalDateTime.now(),
                LocalDateTime.now().plus(30, ChronoUnit.MINUTES),
                5,
                Location.INDOOR));
        runs.add(new Run(2,
                "Second Run",
                LocalDateTime.now(),
                LocalDateTime.now().plus(60, ChronoUnit.MINUTES),
                10,
                Location.INDOOR));
    }

    Optional<Run> findById(Integer id) {
        return runs.stream()
                .filter(run -> run.id().equals(id))
                .findFirst();
    }

    void create(Run run) {
        runs.add(run);
    }

    void update(Run run, Integer id) {
        Optional<Run> existingRun = findById(run.id());
        if(existingRun.isPresent()){
           runs.set(runs.indexOf(existingRun.get()), run);
        }
    }

    void delete(Integer id){
        runs.removeIf(run -> run.id().equals(id));
    }


//    runs.removeIf(new Predicate<Run>() {
//    @Override
//    public boolean test(Run run) {
//        return run.id().equals(id);
//    }
//    });

}
*/