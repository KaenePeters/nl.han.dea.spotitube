package nl.han.dea.util;

import java.util.UUID;

public class TokenGenerator {

    public String generateUUID() {
        return UUID.randomUUID().toString();
    }

}
