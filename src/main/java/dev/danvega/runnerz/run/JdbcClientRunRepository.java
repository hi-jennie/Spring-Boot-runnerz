package dev.danvega.runnerz.run;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Repository
public class JdbcClientRunRepository {
    private static final Logger log = LoggerFactory.getLogger(JdbcClientRunRepository.class);
    // jdbc is more like a bridge between java and database
    private final JdbcClient jdbcClient;
    // dependency injection
    public JdbcClientRunRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public List<Run> findAll(){
        return jdbcClient.sql("select * from run")
                .query(Run.class)// map to Run class
                .list();
    }

    public Optional<Run> findById(Integer id){
        return jdbcClient.sql("select id, title, started_on, completed_on, miles, location FROM run where id = ?")
                .param(id) // parameter Binding
                .query(Run.class)
                .optional();

    }

    public void create(Run run){
        var updated = jdbcClient.sql("INSERT INTO run (id, title, started_on, completed_on, miles, location) values (?, ?, ?, ?, ?, ?)")
                .params(List.of(run.id(), run.title(), run.startedOn(), run.completedOn(), run.miles(), run.location().toString()))
                .update();

        Assert.state(updated ==1, "Failed to create run"+run.title());
        // 	•	updated == 1 作为条件：如果这个条件为真，程序就会继续执行。
        //	•	如果条件为假：程序会抛出异常并显示错误信息，相当于执行了一个“否则”的操作。
    }

    public void update(Run run, Integer id){
        var updated = jdbcClient.sql("UPDATE run SET title = ?, started_on = ?, completed_on = ?, miles = ?, location = ? WHERE id = ?")
                .params(List.of(run.title(), run.startedOn(), run.completedOn(), run.miles(), run.location().toString(), id))
                .update();

        Assert.state(updated == 1, "Failed to update run"+run.title());
    }

    public void delete(Integer id){
        var updated = jdbcClient.sql("DELETE FROM run WHERE id = ?")
                .param(id)
                .update();

        Assert.state(updated == 1, "Failed to delete run"+id);
    }

    public int count(){
        return jdbcClient.sql("select * from run").query().listOfRows().size();
    }

    public void saveAll(List<Run> runs){
        runs.stream().forEach(this::create);
    }

    public List<Run> findByLocation(String location){
        return jdbcClient.sql("select * from run where location = ?")
                .param(location )
                .query(Run.class)
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