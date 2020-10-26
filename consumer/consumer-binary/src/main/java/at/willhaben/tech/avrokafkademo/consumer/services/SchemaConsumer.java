package at.willhaben.tech.avrokafkademo.consumer.services;


import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class SchemaConsumer {

    private Logger logger = Logger.getLogger("SchemaConsumer");

    @KafkaListener(topics = "_schemas")
    public void consumeSchema(byte[] message) {
        if (message != null) {
            final String jsonSchema = new String(message);
            logger.info(String.format("received schema from registry: %s", jsonSchema));
        }
    }

}
