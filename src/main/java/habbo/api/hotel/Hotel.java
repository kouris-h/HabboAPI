package habbo.api.hotel;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Habbo Hotels
 * <p>
 * Values:
 * <ul>
 *     <li>
 *         {@link #NL}: <a href="https://www.habbo.nl">habbo.nl</a>
 *     </li>
 *     <li>
 *         {@link #ES}: <a href="https://www.habbo.es">habbo.es</a>
 *     </li>
 *     <li>
 *         {@link #DE}: <a href="https://www.habbo.de">habbo.de</a>
 *     </li>
 *     <li>
 *         {@link #FR}: <a href="https://www.habbo.fr">habbo.fr</a>
 *     </li>
 *     <li>
 *         {@link #IT}: <a href="https://www.habbo.it">habbo.it</a>
 *     </li>
 *     <li>
 *         {@link #FI}: <a href="https://www.habbo.fi">habbo.fi</a>
 *     </li>
 *     <li>
 *         {@link #COM}: <a href="https://www.habbo.com">habbo.com</a>
 *     </li>
 *     <li>
 *         {@link #COMTR}: <a href="https://www.habbo.com.tr">habbo.com.tr</a>
 *     </li>
 *     <li>
 *         {@link #COMBR}: <a href="https://www.habbo.com.br">habbo.com.br</a>
 *     </li>
 *     <li>
 *         {@link #SANDBOX}: <a href="https://sandbox.habbo.com">sandbox.habbo.com</a>
 *     </li>
 * </ul>
 *
 * @author WiredSpast
 * @version 2.0
 * @since 1.0
 */
public enum Hotel {
    /**
     * habbo.nl - Dutch
     */
    NL("https://www.habbo.nl", "hhnl"),
    /**
     * habbo.es - Spanish
     */
    ES("https://www.habbo.es", "hhes"),
    /**
     * habbo.de - German
     */
    DE("https://www.habbo.de", "hhde"),
    /**
     * habbo.fr - French
     */
    FR("https://www.habbo.fr", "hhfr"),
    /**
     * habbo.it - Italian
     */
    IT("https://www.habbo.it", "hhit"),
    /**
     * habbo.fi - Finnish
     */
    FI("https://www.habbo.fi", "hhfi"),
    /**
     * habbo.com - English
     */
    COM("https://www.habbo.com", "hhus"),
    /**
     * habbo.com.tr - Turkish
     */
    COMTR("https://www.habbo.com.tr", "hhtr"),
    /**
     * habbo.com.br - Brazilian
     */
    COMBR("https://www.habbo.com.br", "hhbr"),
    /**
     * sandbox.habbo.com - Tester hotel
     */
    SANDBOX("https://sandbox.habbo.com", "hhs2");

    /**
     * Prefix of the unique id's linked to the hotel (hhus, hhnl, hhs2, ...)
     */
    public final String idPrefix;

    /**
     * URL domain of the hotel
     */
    public final String domain;

    /**
     *
     * @param domain URL domain of the hotel
     * @param idPrefix Prefix of the unique id's linked to the hotel
     */
    Hotel(String domain, String idPrefix) {
        this.idPrefix = idPrefix;
        this.domain = domain;
    }

    @Override
    public String toString() {
        return this.domain;
    }

    /**
     * Extracts the hotel form the prefix in the unique id
     *
     * @param uniqueId unique id to extract the hotel from
     * @return Hotel linked to the unique id (or {@code null} if the unique id is invalid)
     */
    public static Hotel fromId(String uniqueId) {
        Pattern pattern = Pattern.compile(String.format("%s", Arrays.stream(values()).map(h -> h.idPrefix).collect(Collectors.joining("|"))));
        Matcher matcher = pattern.matcher(uniqueId);
        if (matcher.find()) {
            return Arrays.stream(values()).filter(h -> h.idPrefix.equals(matcher.group(0))).findFirst().orElse(null);
        }
        return null;
    }
}
