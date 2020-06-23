package ru.job4j.collection;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class PassportOfficeTest {

    @Test
    public void add() {
        Citizen citizen = new Citizen("2fe45", "Max Rybetsky");
        PassportOffice passportOffice = new PassportOffice();
        passportOffice.add(citizen);
        assertThat(
                passportOffice.get(citizen.getPassport()),
                is(citizen)
        );
    }
}