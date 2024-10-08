package dev.danvega.runnerz.run;

// add spring-boot-starter-validation dependency in pom.xml
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

public record Run(
        Integer id,
        @NotEmpty // tell spring that this field should not be empty
        String title,
        LocalDateTime startedOn,
        LocalDateTime completedOn,
        @Positive // tell spring that this field should be positive
        Integer miles,
        Location location
) {
    // record 类会为你自动生成一个全参构造器，接受所有字段作为参数。
    // 紧凑型构造器允许你在这个自动生成的构造器中添加额外的逻辑（如验证、异常处理等），而不必手动编写所有字段的初始化。
    public Run{
        if(!completedOn.isAfter(startedOn)){
            throw new IllegalArgumentException("Complete time must be after start time");
        }
    }
}
