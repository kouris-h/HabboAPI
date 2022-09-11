package habbo.api.util.deserialize;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.awt.Color;
import java.io.IOException;
import java.util.HexFormat;

public class ColorDeserializer extends StdDeserializer<Color> {
    public static final ColorDeserializer INSTANCE = new ColorDeserializer();

    protected ColorDeserializer() {
        super(Color.class);
    }

    @Override
    public Color deserialize(JsonParser parser, DeserializationContext context) throws IOException, JacksonException {
        return new Color(HexFormat.fromHexDigits(parser.getText()));
    }
}
