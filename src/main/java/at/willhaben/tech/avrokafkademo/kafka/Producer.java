package at.willhaben.tech.avrokafkademo.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class Producer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private Logger logger = Logger.getLogger("Producer");

    @Scheduled(fixedRate = 1000 )
    public void sendMessage() {
        final String message = "xx"+System.currentTimeMillis();
        logger.info(String.format("#### -> Producing message -> %s", message));
        this.kafkaTemplate.send("users", message);
    }

}
