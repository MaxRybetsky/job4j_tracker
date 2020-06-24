package ru.job4j.collection;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class JobTest {
    @Test
    public void whenAscByPriority(){
        List<Job> jobs = Arrays.asList(
                new Job("Text1", 3),
                new Job("Text1", 4),
                new Job("Abc", 1),
                new Job("XYZ", 5)
        );
        List<Job> expected = Arrays.asList(
                new Job("Abc", 1),
                new Job("Text1", 3),
                new Job("Text1", 4),
                new Job("XYZ", 5)
        );
        Collections.sort(jobs, new JobAscByPriority());
        assertThat(jobs, is(expected));
    }

    @Test
    public void whenAscByName(){
        List<Job> jobs = Arrays.asList(
                new Job("Text1", 3),
                new Job("Text1", 4),
                new Job("Abc", 1),
                new Job("BYZ", 5)
        );
        List<Job> expected = Arrays.asList(
                new Job("Abc", 1),
                new Job("BYZ", 5),
                new Job("Text1", 3),
                new Job("Text1", 4)
        );
        Collections.sort(jobs, new JobAscByName());
        assertThat(jobs, is(expected));
    }

    @Test
    public void whenDescByPriority(){
        List<Job> jobs = Arrays.asList(
                new Job("Text1", 3),
                new Job("Text1", 4),
                new Job("Abc", 1),
                new Job("XYZ", 5)
        );
        List<Job> expected = Arrays.asList(
                new Job("XYZ", 5),
                new Job("Text1", 4),
                new Job("Text1", 3),
                new Job("Abc", 1)

        );
        Collections.sort(jobs, new JobDescByPriority());
        assertThat(jobs, is(expected));
    }

    @Test
    public void whenDescByName(){
        List<Job> jobs = Arrays.asList(
                new Job("Text1", 3),
                new Job("Text1", 4),
                new Job("Abc", 1),
                new Job("BYZ", 5)
        );
        List<Job> expected = Arrays.asList(
                new Job("Text1", 3),
                new Job("Text1", 4),
                new Job("BYZ", 5),
                new Job("Abc", 1)
        );
        Collections.sort(jobs, new JobDescByName());
        assertThat(jobs, is(expected));
    }

    @Test
    public void whenDescByNameAndAscByPriority(){
        List<Job> jobs = Arrays.asList(
                new Job("Text1", 4),
                new Job("Text1", 3),
                new Job("Abc", 1),
                new Job("BYZ", 5)
        );
        List<Job> expected = Arrays.asList(
                new Job("Text1", 3),
                new Job("Text1", 4),
                new Job("BYZ", 5),
                new Job("Abc", 1)
        );
        Comparator<Job> cmp = new JobDescByName().thenComparing(new JobAscByPriority());
        Collections.sort(jobs, cmp);
        assertThat(jobs, is(expected));
    }

    @Test
    public void whenComparatorByNameAndPriority() {
        Comparator<Job> cmpNamePriority = new JobDescByName().thenComparing(new JobDescByPriority());
        int rsl = cmpNamePriority.compare(
                new Job("Impl task", 0),
                new Job("Fix bug", 1)
        );
        assertThat(rsl, lessThan(0));
    }
}