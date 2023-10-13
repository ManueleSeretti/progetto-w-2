package manueleSeretti.Libreria;

import java.util.Random;

public enum Periodicità {
    SETTIMANALE,
    MENSILE,
    SEMESTRALE;
    private static final Random rndm = new Random();

    public static Periodicità randomPeriodicita() {

        Periodicità[] periodicita = values();
        return periodicita[rndm.nextInt(periodicita.length)];

    }
}
