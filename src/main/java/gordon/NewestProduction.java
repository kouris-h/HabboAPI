package gordon;

import gamedata.external_variables.ExternalVariables;
import hotel.Hotel;

public class NewestProduction {
    public static final String client;

    static {
        client = ExternalVariables.getProduction(Hotel.SANDBOX);
    }
}
