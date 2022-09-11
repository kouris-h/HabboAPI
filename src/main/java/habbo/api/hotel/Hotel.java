package habbo.api.hotel;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public enum Hotel {
    NL("https://www.habbo.nl", "hhnl"),
    ES("https://www.habbo.es", "hhes"),
    DE("https://www.habbo.de", "hhde"),
    FR("https://www.habbo.fr", "hhfr"),
    IT("https://www.habbo.it", "hhit"),
    FI("https://www.habbo.fi", "hhfi"),
    COM("https://www.habbo.com", "hhus"),
    COMTR("https://www.habbo.com.tr", "hhtr"),//FIXME ?
    COMBR("https://www.habbo.com.br", "hhbr"),//FIXME ?
    SANDBOX("https://sandbox.habbo.com", "hhs2");

    public final String idPrefix;
    public final String domain;

    Hotel(String domain, String idPrefix) {
        this.idPrefix = idPrefix;
        this.domain = domain;
    }

    @Override
    public String toString() {
        return this.domain;
    }

    public static Hotel fromId(String uniqueId) {
        Pattern pattern = Pattern.compile(String.format("%s", Arrays.stream(values()).map(h -> h.idPrefix).collect(Collectors.joining("|"))));
        Matcher matcher = pattern.matcher(uniqueId);
        if (matcher.find()) {
            return Arrays.stream(values()).filter(h -> h.idPrefix.equals(matcher.group(0))).findFirst().orElse(null);
        }
        return null;
    }
}
