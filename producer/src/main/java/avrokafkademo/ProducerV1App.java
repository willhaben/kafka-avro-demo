package avrokafkademo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ProducerV1App {

	public static void main(String[] args) {
		SpringApplication.run(ProducerV1App.class, args);
	}

}
