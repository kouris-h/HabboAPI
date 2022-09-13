package habbo.api.util.fetch;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

final class LocalDateTimeDeserializer extends StdDeserializer<LocalDateTime> {
    static final DateTimeFormatter dtf_Z = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
    static final DateTimeFormatter dtf_z = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSz");

    static final LocalDateTimeDeserializer INSTANCE = new LocalDateTimeDeserializer();

    LocalDateTimeDeserializer() {
        super(LocalDateTime.class);
    }

    @Override
    public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        if(jsonParser.getText().matches(".*\\+.*:.*")) {
            return LocalDateTime.parse(jsonParser.getText(), dtf_z);
        } else {
            return LocalDateTime.parse(jsonParser.getText(), dtf_Z);
        }
    }
}
