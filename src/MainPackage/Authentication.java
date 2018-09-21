package MainPackage;

import java.io.Serializable;

public class Authentication implements Transaction, Serializable
{
    boolean auth;
    String Error;

    public Authentication(boolean auth, String error) {
        this.auth = auth;
        Error = error;
    }
}
