package dev.danvega.runnerz.run;

import java.time.LocalDateTime;

public record Run(
        Integer id,
        String title,
        LocalDateTime startTime,
        LocalDateTime completeTime,
        Integer miles,
        Location location
) {}
