package ru.job4j.stream;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ProfilesTest {
    @Test
    public void whenGetAddresses() {
        List<Address> adresses = Arrays.asList(
                new Address("Moscow", "Okhotny ryad", 1, 1),
                new Address("Sankt-Petersburg", "Nevsky prospect", 7, 5),
                new Address("Smolensk", "Oktyabrskaya", 12, 190),
                new Address("Vladivostok", "Lenina", 23, 98),
                new Address("Chabarovsk", "Pionerskaya", 34, 1)
        );
        List<Profile> list = Arrays.asList(
                new Profile(adresses.get(0)),
                new Profile(adresses.get(1)),
                new Profile(adresses.get(2)),
                new Profile(adresses.get(3)),
                new Profile(adresses.get(4))
        );
        assertThat(
                Profiles.collect(list),
                is(adresses)
        );
    }

    @Test
    public void whenGetAddressesWithoutDuplicates() {
        List<Address> adresses = Arrays.asList(
                new Address("Moscow", "Okhotny ryad", 1, 1),
                new Address("Moscow", "Okhotny ryad", 1, 1),
                new Address("Smolensk", "Oktyabrskaya", 12, 190),
                new Address("Vladivostok", "Lenina", 23, 98),
                new Address("Vladivostok", "Lenina", 23, 98)
        );
        List<Profile> list = Arrays.asList(
                new Profile(adresses.get(0)),
                new Profile(adresses.get(1)),
                new Profile(adresses.get(2)),
                new Profile(adresses.get(3)),
                new Profile(adresses.get(4))
        );
        assertThat(
                Profiles.collectUnique(list),
                is(
                        Arrays.asList(
                                adresses.get(0),
                                adresses.get(2),
                                adresses.get(3)
                        )
                )
        );
    }
}