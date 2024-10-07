package dev.danvega.runnerz;

import dev.danvega.runnerz.run.Location;
import dev.danvega.runnerz.run.Run;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@SpringBootApplication
public class Application {
	private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		log.info("Application started successfully.");
	}

	@Bean
	CommandLineRunner runner(){
		return args -> {
			Run run = new Run(
					1, "First Run",
					LocalDateTime.now(),
					LocalDateTime.now().plus(1, ChronoUnit.HOURS), 5,
					Location.OUTDOOR);
			log.info("Run: "+run);
		};
	}

}

/*
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
		// 当你在 Spring 应用程序里需要某个对象时（比如你在类中需要某个服务或组件），
		// 你不需要自己去创建它，只需要通过 getBean() 从上下文中拿到已经由 Spring 管理的 Bean。
		WelcomeMessage welcomeMessage = context.getBean(WelcomeMessage.class);
		System.out.println(welcomeMessage.getWelcomeMessage());

		// SpringApplication.run(Application.class, args);
		// var welcomeMessage = new WelcomeMessage();
		// System.out.println(welcomeMessage.getWelcomeMessage());
	}


	当 SpringApplication.run(Application.class, args) 被调用时，以下几件事情发生：
	1.	创建上下文：Spring 创建了一个 ConfigurableApplicationContext 对象，准备好去管理所有的 Bean。
	2.	扫描组件：Spring 自动扫描你的项目代码，找出所有用 @Component、@Service、@Repository 等注解标注的类，并且根据配置创建对应的 Bean。
	3.	初始化对象：Spring 根据你定义的配置，创建并初始化所有的 Bean。比如你的 WelcomeMessage 类会在这个阶段被创建并加载到上下文中。
	4.	依赖注入：如果某个 Bean 需要其他 Bean（比如服务类需要数据库类），Spring 会自动注入所需的依赖。
	5.	运行应用：应用启动后，你可以通过上下文 getBean() 来使用已经创建好的 Bean。

}
*/


/*
		Logger (log): A tool for recording messages (log entries) during application execution.
	•	LoggerFactory.getLogger(Application.class): Creates a logger associated with the Application class, helping to track where log messages originate.
	•	Log Levels: Allow you to manage the severity of messages (INFO, DEBUG, ERROR, etc.).
	•	Logging Output: Messages are printed to the console or written to a file, showing key information like timestamps, severity, and the class where the log was created.
		 */