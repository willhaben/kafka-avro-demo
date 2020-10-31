package at.willhaben.tech.avrokafkademo.consumer.services;

import at.willhaben.tech.avro.User;
import at.willhaben.tech.avro.UserBackwardCompatible;
import at.willhaben.tech.avro.UserForwardCompatible;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.logging.Logger;

@Service
public class Consumer {

    private Logger logger = Logger.getLogger("Consumer");

    @KafkaListener(topics = "someTopic")
    public void consumeSomeRecord(User read) throws IOException {

        logger.info(String.format("Consumed someTopic -> %s", read));
    }

    //  @KafkaListener(topics = "someTopic")
    public void consumeSomeWrongRecord(UserBackwardCompatible read) throws IOException {
        logger.info(String.format("Consumed someTopic -> %s", read));
    }

    //@KafkaListener(topics = "someTopic")
    public void consumeSomeWrongRecord(UserForwardCompatible read) throws IOException {
        logger.info(String.format("Consumed someTopic -> %s", read));
    }


}
