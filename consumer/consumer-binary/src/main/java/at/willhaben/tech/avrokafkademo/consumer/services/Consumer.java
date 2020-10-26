package at.willhaben.tech.avrokafkademo.consumer.services;


import at.willhaben.tech.avro.SomeRecord;
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
    final SpecificDatumReader<SomeRecord> specificDatumReader = new SpecificDatumReader<>(SomeRecord.SCHEMA$);

    @KafkaListener(topics = "someTopic")
    public void consumeRaw(byte[] message) throws IOException {

        final BinaryDecoder binaryDecoder = DecoderFactory.get().binaryDecoder(message, 5, message.length - 5, null);
        final ValidatingDecoder validatingDecoder = DecoderFactory.get().validatingDecoder(SomeRecord.SCHEMA$, binaryDecoder);
        final SomeRecord read = specificDatumReader.read(null, validatingDecoder);
        logger.info(String.format("Consumed someTopic -> %s", read));
    }



}
