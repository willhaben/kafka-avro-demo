package at.willhaben.tech.avrokafkademo.consumer.services;


import at.willhaben.tech.avro.SomeRecord;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.BinaryDecoder;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.io.ValidatingDecoder;
import org.apache.avro.specific.SpecificDatumReader;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.logging.Logger;

@Service
public class Consumer {

    private Logger logger = Logger.getLogger("Consumer");

    @KafkaListener(topics = "someTopic")
    public void consumeSomeRecord(SomeRecord read) throws IOException {

        logger.info(String.format("Consumed someTopic -> %s", read));
    }



}
