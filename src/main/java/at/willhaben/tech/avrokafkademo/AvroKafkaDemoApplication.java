package at.willhaben.tech.avrokafkademo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AvroKafkaDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AvroKafkaDemoApplication.class, args);
	}

}
