package ru.job4j.stream;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

import static org.hamcrest.core.Is.is;

public class SchoolTest {
    @Test
    public void whenGetAClass() {
        assertThat(
                School.collect(
                        Arrays.asList(
                                new Student("Ivanov", 100),
                                new Student("Marker", 40),
                                new Student("Sidorov", 60),
                                new Student("Voloshin", 80),
                                new Student("Volodiev", 70),
                                new Student("Kraker", 50),
                                new Student("Braev", 30)
                        ),
                        (x) -> x.getScore() >= 70
                ),
                is(
                        Arrays.asList(
                                new Student("Ivanov", 100),
                                new Student("Voloshin", 80),
                                new Student("Volodiev", 70)
                        )
                )
        );
    }

    @Test
    public void whenGetBClass() {
        assertThat(
                School.collect(
                        Arrays.asList(
                                new Student("Ivanov", 100),
                                new Student("Marker", 40),
                                new Student("Sidorov", 60),
                                new Student("Voloshin", 80),
                                new Student("Volodiev", 70),
                                new Student("Kraker", 50),
                                new Student("Braev", 30)
                        ),
                        (x) -> x.getScore() >= 50 && x.getScore() < 70
                ),
                is(
                        Arrays.asList(
                                new Student("Sidorov", 60),
                                new Student("Kraker", 50)
                        )
                )
        );
    }

    @Test
    public void whenGetVClass() {
        assertThat(
                School.collect(
                        Arrays.asList(
                                new Student("Ivanov", 100),
                                new Student("Marker", 40),
                                new Student("Sidorov", 60),
                                new Student("Voloshin", 80),
                                new Student("Volodiev", 70),
                                new Student("Kraker", 50),
                                new Student("Braev", 30)
                        ),
                        (x) -> x.getScore() < 50
                ),
                is(
                        Arrays.asList(
                                new Student("Marker", 40),
                                new Student("Braev", 30)
                        )
                )
        );
    }
}