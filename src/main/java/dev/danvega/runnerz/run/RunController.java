package dev.danvega.runnerz.run;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
    public Iterable<Run> getRuns() {
        return runRepository.findAll();
    }

    @GetMapping("/{id}")
    Run FindRunById(@PathVariable Integer id) {
        // Optional is more like a container for an object which may or may not be null.
        Optional<Run> run =  runRepository.findById(id);
        if(run.isEmpty()){
            throw new ResponseStatusException(HttpStatus.MULTI_STATUS, "Run not found");
        }

        return run.get();
    }

    // 当客户端通过 POST 请求调用 create 方法时，假如操作成功，这个注解告诉服务器返回 HTTP 201 状态码。
    // 这是标准的“创建成功”响应，意味着服务器已经成功创建了一个新的资源（比如一个数据库中的记录）
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    void create(@RequestBody Run run) {
        runRepository.create(run);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    void update(@RequestBody Run run, @PathVariable Integer id) {
        runRepository.update(run, id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void delete(@PathVariable Integer id) {
        runRepository.delete(id);
    }

}
