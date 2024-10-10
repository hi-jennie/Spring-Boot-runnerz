package dev.danvega.runnerz.run;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/runs")
public class RunController {
    // @Autowired here is also not suggested
    private final RunRepository runRepository;

    // think about why we don't use new RunRepository() here to initialize runRepository
    // actually, here we are using dependency injection
    // @Autowired is unnecessary here
    // 从 Spring 4.3 版本开始，@Autowired 在构造函数上已经是可选的。
    // 如果一个类只有一个构造函数，Spring 会默认将其作为注入点。这样你不必每次都手动加上 @Autowired，框架会自动帮你处理。
    public RunController(RunRepository runRepository) {
        this.runRepository = runRepository;
    }

    @GetMapping("")
    List<Run> findAll() {
        return runRepository.findAll();
    }

    @GetMapping("/{id}")
    Run FindRunById(@PathVariable Integer id) {
        // Optional is more like a container for an object which may or may not be null.
        Optional<Run> run =  runRepository.findById(id);
        if(run.isEmpty()){
            throw new RunNotFoundException();
        }

        return run.get();
    }

    // 当客户端通过 POST 请求调用 create 方法时，假如操作成功，这个注解告诉服务器返回 HTTP 201 状态码。
    // 这是标准的“创建成功”响应，意味着服务器已经成功创建了一个新的资源（比如一个数据库中的记录）
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    void create(@Valid @RequestBody Run run) {
        runRepository.save(run);
    }
    // @Valid 注解告诉 Spring Boot 在调用 create 方法之前验证 run 对象。
    // 根据 Run 类的定义，这意味着 Spring Boot 会检查 title 和 miles 字段是否符合定义的约束。

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    void update(@Valid @RequestBody Run run, @PathVariable Integer id) {
        runRepository.save(run);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void delete(@PathVariable Integer id) {
        runRepository.delete(runRepository.findById(id).get());
    }

    @GetMapping("/location/{location}")
    List<Run> findAllByLocation(@PathVariable String location) {
        return runRepository.findAllByLocation(location);
    }
}
