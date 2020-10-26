package at.willhaben.tech.avrokafkademo.consumer.services;


import org.apache.avro.generic.GenericRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.logging.Logger;

@Service
public class Consumer {

    private Logger logger = Logger.getLogger("Consumer");
    @KafkaListener(topics = "someTopic")
    public void consumeRaw(GenericRecord record) throws IOException {
        logger.info(String.format("Consumed someTopic -> %s", record));
    }



}
