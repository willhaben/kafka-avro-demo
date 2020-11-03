import at.willhaben.tech.avro.User;
import at.willhaben.tech.avro.UserBackwardCompatible;
import at.willhaben.tech.avro.UserForwardCompatible;
import org.apache.avro.io.BinaryDecoder;
import org.apache.avro.io.BinaryEncoder;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class TestCompatiblity {

    @Test
    public void testFailWithouthWriterSchema() throws IOException {
        final User build = User.newBuilder().setName("Matthias").setSurname("Kausl").setState("Happy").setVersion(1).build();
        final UserBackwardCompatible compatiblityBackward = UserBackwardCompatible.fromByteBuffer(build.toByteBuffer());
        assertThat(compatiblityBackward.getName()).isEqualTo(build.getName());
    }

    @Test
    public void testWithouthWriterSchema() throws IOException {
        final User base = User.newBuilder().setName("Matthias").setSurname("Kausl").setState("Happy").setVersion(1).build();

        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final BinaryEncoder binaryEncoder = EncoderFactory.get().binaryEncoder(out, null);
        final SpecificDatumWriter<User> userSpecificDatumWriter = new SpecificDatumWriter<>(User.SCHEMA$);
        userSpecificDatumWriter.write(base, binaryEncoder);
        binaryEncoder.flush();

        final byte[] bytes = out.toByteArray();
        final BinaryDecoder binaryDecoder = DecoderFactory.get().binaryDecoder(bytes, null);

        final SpecificDatumReader<UserBackwardCompatible> specificDatumReader = new SpecificDatumReader<>(UserBackwardCompatible.SCHEMA$);
        final UserBackwardCompatible userBackwardCompatible = specificDatumReader.read(null, binaryDecoder);
        assertThat(userBackwardCompatible.getName()).isEqualTo(base.getName());
        assertThat(userBackwardCompatible.getState()).isEqualTo(base.getState());
        assertThat(userBackwardCompatible.getVersion()).isEqualTo(base.getVersion());
        assertThat(userBackwardCompatible.getSkills()).isNull();
    }

    @Test()
    public void testBackwardFail() throws IOException {

        final UserBackwardCompatible userForwardCompatible = UserBackwardCompatible.newBuilder()
                .setName("Matthias").setSkills("English").setVersion(2).build();

        final BinaryMessageDecoder<User> decoder = User.getDecoder();
        decoder.addSchema(userForwardCompatible.getSchema());

        final User base = decoder.decode(userForwardCompatible.toByteBuffer());
        assertThat(base.getName()).isEqualTo(userForwardCompatible.getName());
        assertThat(base.getSurname()).isNull();
        assertThat(base.getState()).isNull();
        assertThat(base.getVersion()).isEqualTo(userForwardCompatible.getVersion());

    }

    @Test
    public void testBackward() throws IOException {
        final User base = User.newBuilder().setName("Matthias").setSurname("Kausl").setState("Happy").setVersion(1).build();
        final BinaryMessageDecoder<UserBackwardCompatible> decoder = UserBackwardCompatible.getDecoder();
        decoder.addSchema(base.getSchema());
        final UserBackwardCompatible userBackwardCompatible = decoder.decode(base.toByteBuffer());
        assertThat(userBackwardCompatible.getName()).isEqualTo(base.getName());
        assertThat(userBackwardCompatible.getState()).isEqualTo(base.getState());
        assertThat(userBackwardCompatible.getVersion()).isEqualTo(base.getVersion());
        assertThat(userBackwardCompatible.getSkills()).isNull();
    }

    @Test
    public void testForwardFail() throws IOException {
        final User base = User.newBuilder().setName("Matthias").setSurname("Kausl").setState("Happy").setVersion(1).build();

        final BinaryMessageDecoder<UserForwardCompatible> decoder = UserForwardCompatible.getDecoder();
        decoder.addSchema(base.getSchema());

        final UserForwardCompatible decuserForwardCompatiblede = decoder.decode(base.toByteBuffer());
        assertThat(decuserForwardCompatiblede.getName()).isEqualTo(base.getName());
        assertThat(decuserForwardCompatiblede.getSurname()).isEqualTo(base.getSurname());
        assertThat(decuserForwardCompatiblede.getVersion()).isEqualTo(base.getVersion());
        assertThat(decuserForwardCompatiblede.getLanguage()).isNull();

    }

    @Test
    public void testForward() throws IOException {
        final UserForwardCompatible userForwardCompatible = UserForwardCompatible.newBuilder()
                .setName("Matthias").setSurname("Kausl").setLanguage("English").setVersion(2).build();

        final BinaryMessageDecoder<User> decoder = User.getDecoder();
        decoder.addSchema(userForwardCompatible.getSchema());

        final User base = decoder.decode(userForwardCompatible.toByteBuffer());
        assertThat(base.getName()).isEqualTo(userForwardCompatible.getName());
        assertThat(base.getSurname()).isEqualTo(userForwardCompatible.getSurname());
        assertThat(base.getState()).isNull();
        assertThat(base.getVersion()).isEqualTo(userForwardCompatible.getVersion());

    }

}
