package ru.job4j.stream;

import org.junit.Test;
import ru.job4j.tracker.StubOutput;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ListToMapTest {
    @Test
    public void whenConvertListToMap() {
        List<Student> students = Arrays.asList(
                new Student("Ivanov", 90),
                new Student("Ivanova", 95),
                new Student("Volkov", 60),
                new Student("Leonov", 80),
                new Student("Vasilyev", 85)
        );
        Map<String, Student> studentMap = new HashMap<>();
        for (Student student : students) {
            studentMap.put(student.getSurname(), student);
        }
        assertThat(
                ListToMap.listToMap(students),
                is(studentMap)
        );
    }
}