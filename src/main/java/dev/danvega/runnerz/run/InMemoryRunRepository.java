package dev.danvega.runnerz.run;


import org.springframework.stereotype.Repository;

@Repository
public class InMemoryRunRepository {

//    private static final Logger log = LoggerFactory.getLogger(InMemoryRunRepository.class);
//    private final List<Run> runs = new ArrayList<>();
//
//    public List<Run> findAll() {
//        return runs;
//    }
//
//    public Optional<Run> findById(Integer id) {
//        return Optional.ofNullable(runs.stream()
//                .filter(run -> run.id() == id)
//                .findFirst()
//                .orElseThrow(RunNotFoundException::new));
//    }
//
//    public void create(Run run) {
//        Run newRun = new Run(run.id(),
//                run.title(),
//                run.startedOn(),
//                run.completedOn(),
//                run.miles(),
//                run.location(), null);
//
//        runs.add(newRun);
//    }
//
//    public void update(Run newRun, Integer id) {
//        Optional<Run> existingRun = findById(id);
//        if (existingRun.isPresent()) {
//            var r = existingRun.get();
//            log.info("Updating Existing Run: " + existingRun.get());
//            runs.set(runs.indexOf(r), newRun);
//        }
//    }
//
//    public void delete(Integer id) {
//        log.info("Deleting Run: " + id);
//        runs.removeIf(run -> run.id().equals(id));
//    }
//
//    public long count() {
//        return runs.size();
//    }
//
//    public void saveAll(List<Run> runs) {
//        runs.stream().forEach(run -> create(run));
//    }
//
//    public List<Run> findByLocation(String location) {
//        return runs.stream()
//                .filter(run -> Objects.equals(run.location(), location))
//                .toList();
//    }
//
//
//    @PostConstruct
//    private void init() {
//        runs.add(new Run(1,
//                "Monday Morning Run",
//                LocalDateTime.now(),
//                LocalDateTime.now().plusMinutes(30),
//                3,
//                Location.INDOOR, null));
//
//        runs.add(new Run(2,
//                "Wednesday Evening Run",
//                LocalDateTime.now(),
//                LocalDateTime.now().plusMinutes(60),
//                6,
//                Location.INDOOR, null));
//    }
//

}


//    runs.removeIf(new Predicate<Run>() {
//    @Override
//    public boolean test(Run run) {
//        return run.id().equals(id);
//    }
//    });



