package Shell_scripting.demo;

import Shell_scripting.demo.service.HealthCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	@Autowired
	private HealthCheckService healthCheckService;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) {

		boolean status = healthCheckService.checkHealth();

		if (status) {
			System.exit(0);
		} else {
			System.exit(1);
		}
	}
}