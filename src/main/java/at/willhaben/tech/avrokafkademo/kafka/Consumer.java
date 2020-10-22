package at.willhaben.tech.avrokafkademo.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.logging.Logger;

@Service
public class Consumer {

    private Logger logger = Logger.getLogger("Consumer");

    @KafkaListener(topics = "users")
    public void consume(String message) throws IOException {
        logger.info(String.format("#### -> Consumed message -> %s", message));
    }

}
