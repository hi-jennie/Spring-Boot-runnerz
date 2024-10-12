package dev.danvega.runnerz.run;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// 	它会加载与你指定的控制器相关的上下文，例如RunController。
//	只聚焦于HTTP请求和响应的逻辑测试，而不会加载整个应用程序的上下文（如数据库、服务层）
@WebMvcTest(RunController.class)
class RunControllerTest {
    private final List<Run> runs = new ArrayList<>();
    @Autowired
    MockMvc mvc;// 使用 MockMvc 模拟 HTTP 请求和响应
    @Autowired
    ObjectMapper objectMapper;// 使用 ObjectMapper 将对象转换为 JSON 字符串
    @MockBean
    RunRepository repository;// 使用 @MockBean 替换掉实际的 Repository，定义其返回行为

    @BeforeEach
    void setUp() {
        runs.add(new Run(1,
                "First Run",
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(30),
                5,
                Location.INDOOR));

        runs.add(new Run(2,
                "Second Run",
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(60),
                10,
                Location.INDOOR));
    }

    @Test
    void shouldFindAllRuns() throws Exception {
        // 当调用 findAll() 方法时，返回 runs 列表而不是实际的数据库查询结果
        when(repository.findAll()).thenReturn(runs);
        mvc.perform(MockMvcRequestBuilders.get("/api/runs"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(runs.size())));

    }

    @Test
    void shouldFindRunById() throws Exception {
        Run run = runs.get(0);
        when(repository.findById(ArgumentMatchers.anyInt())).thenReturn(java.util.Optional.of(run));
        mvc.perform(MockMvcRequestBuilders.get("/api/runs/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(run.id())))
                .andExpect(jsonPath("$.title", is(run.title())))
                .andExpect(jsonPath("$.miles", is(run.miles())));
    }

    @Test
    void shouldReturnNotFoundWithInvalidId() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/runs/100"))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldCreateNewRun() throws Exception {
        var run = new Run(3,
                "Third Run",
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(45),
                7,
                Location.OUTDOOR);
        mvc.perform(MockMvcRequestBuilders.post("/api/runs")
                        .contentType(MediaType.APPLICATION_JSON) // represents that what we are sending is JSON
                        .content(objectMapper.writeValueAsBytes(run))) // maps the run object to JSON as the request body
                .andExpect(status().isCreated());
    }

    @Test
    void shouldUpdateRun() throws Exception {
        var run = new Run(1,
                "Updated Run",
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(45),
                7,
                Location.OUTDOOR);
        mvc.perform(MockMvcRequestBuilders.put("/api/runs/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(run)))
                .andExpect(status().isNoContent());
    }

//    @Test
//    void shouldDeleteRun() throws Exception {
//        mvc.perform(MockMvcRequestBuilders.delete("/api/runs/1"))
//                .andExpect(status().isNoContent());
//    }
    //  只能检验
    // 1.DELETE 请求是否能够成功发送。
    //  2.响应状态是否为 204 No Content。
}