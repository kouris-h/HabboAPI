package habbo.api.util.fetch;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.awt.Color;
import java.io.IOException;
import java.util.HexFormat;

final class ColorDeserializer extends StdDeserializer<Color> {
    static final ColorDeserializer INSTANCE = new ColorDeserializer();

    ColorDeserializer() {
        super(Color.class);
    }

    @Override
    public Color deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        return new Color(HexFormat.fromHexDigits(parser.getText()));
    }
}
