package avrokafkademo.services;

import at.willhaben.tech.avro.User;
import org.apache.avro.specific.SpecificRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class Producer {

    @Autowired
    private KafkaTemplate<String, SpecificRecord> kafkaTemplate;

    private Logger logger = Logger.getLogger("Producer");

    @Scheduled(fixedRate = 2000,initialDelay = 0)
    public void sendMessage() {
        final User user = new User("Matthias", "Kausl", "Happy " + System.currentTimeMillis(), 1);

        SpecificRecord data = user;
        logger.info(String.format("Producing -> %s", data));

        this.kafkaTemplate.send("someTopic", data);
    }


}
