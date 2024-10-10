package dev.danvega.runnerz.run;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.asm.TypeReference;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Component
public class RunJsonDataLoader implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(RunJsonDataLoader.class);
    private final JdbcClientRunRepository runRepository;
    private final ObjectMapper objectMapper;

    public RunJsonDataLoader(JdbcClientRunRepository runRepository, ObjectMapper objectMapper) {
        this.runRepository = runRepository;
        this.objectMapper = objectMapper;
    }

    // execute the code in run() method when the application starts
    @Override
    public void run(String... args) throws Exception {
        if(runRepository.count()==0){
            // read JSON data from .json file
            try(InputStream inputStream = TypeReference.class.getResourceAsStream("/data/runs.json")){
                // convert JSON data to Run object separately, then save to in_memory collection in List<Run>,and use Runs(List<Run run)to wrap it
                Runs allRuns = objectMapper.readValue(inputStream, Runs.class);
                log.info("Reading {} runs from JSON data and saving to a database.", allRuns.runs().size());
                runRepository.saveAll(allRuns.runs());
            } catch (IOException e){
                throw new RuntimeException("Failed to load JSON data", e);
            }
        }else{
            log.info("Not loading Runs from JSON data because the collection contains data");
        }
    }
}
