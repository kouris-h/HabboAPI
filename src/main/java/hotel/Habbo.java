package hotel;

public enum Habbo {
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

    Habbo(String domain, String idPrefix) {
        this.idPrefix = idPrefix;
        this.domain = domain;
    }
}
