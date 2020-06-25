package ru.job4j.collection;

import java.util.HashMap;
import java.util.Map;

public class PassportOffice {
    private final Map<String, Citizen> citizens = new HashMap<>();

    public boolean add(Citizen citizen) {
        if (!citizens.containsKey(citizen.getPassport())) {
            citizens.put(citizen.getPassport(), citizen);
            return true;
        }
        return false;
    }

    public Citizen get(String passport) {
        return citizens.get(passport);
    }
}
