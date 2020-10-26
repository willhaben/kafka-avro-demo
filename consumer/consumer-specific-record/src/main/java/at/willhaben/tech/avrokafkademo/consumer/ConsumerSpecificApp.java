package at.willhaben.tech.avrokafkademo.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ConsumerSpecificApp {

	public static void main(String[] args) {
		SpringApplication.run(ConsumerSpecificApp.class, args);
	}

}
