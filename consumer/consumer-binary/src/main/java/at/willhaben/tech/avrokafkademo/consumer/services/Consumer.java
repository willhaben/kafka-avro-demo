package at.willhaben.tech.avrokafkademo.consumer.services;


import at.willhaben.tech.avro.User;
import org.apache.avro.Schema;
import org.apache.avro.io.BinaryDecoder;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.io.ValidatingDecoder;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificRecordBase;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.logging.Logger;

@Service
public class Consumer<T> {

    private Logger logger = Logger.getLogger("Consumer");


    @KafkaListener(topics = "someTopic")
    public void consumeRaw(byte[] message) throws IOException {

        final User read = decode(message, User.SCHEMA$, User.class);

        logger.info(String.format("Consumed someTopic SomeRecord -> %s", read));
    }

    private <T extends SpecificRecordBase> T decode(byte[] message, Schema schema, Class<T> c) throws IOException {
        final BinaryDecoder binaryDecoder = DecoderFactory.get().binaryDecoder(message, 5, message.length - 5, null);
        final ValidatingDecoder validatingDecoder = DecoderFactory.get().validatingDecoder(schema, binaryDecoder);
        final SpecificDatumReader<T> specificDatumReader = new SpecificDatumReader<>(schema);
        final T read = specificDatumReader.read(null, validatingDecoder);
        return read;
    }


}
