package dev.danvega.runnerz.run;

import org.springframework.context.annotation.Bean;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RunRepository extends ListCrudRepository<Run,Integer> {
    List<Run> findAllByLocation(String location);
}
