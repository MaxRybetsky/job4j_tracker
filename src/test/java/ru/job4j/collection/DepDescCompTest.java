package ru.job4j.collection;

import org.junit.Test;

import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.greaterThan;

public class DepDescCompTest {

    @Test
    public void whenDownDepartmentGoBefore() {
        int rsl = new DepDescComp().compare(
                "K2/SK1/SSK2",
                "K2/SK1/SSK1"
        );
        assertThat(rsl, greaterThan(0));
    }

    @Test
    public void whenUpDepartmentGoBefore() {
        int rsl = new DepDescComp().compare(
                "K2",
                "K2/SK1"
        );
        assertThat(rsl, lessThan(0));
    }

    @Test
    public void whenDownMainDepartmentGoBefore() {
        int rsl = new DepDescComp().compare(
                "K1",
                "K2"
        );
        assertThat(rsl, greaterThan(0));
    }

    @Test
    public void whenDepartmentsAreEqual1() {
        int rsl = new DepDescComp().compare(
                "K1",
                "K1"
        );
        assertThat(rsl, is(0));
    }

    @Test
    public void whenDepartmentsAreEqual2() {
        int rsl = new DepDescComp().compare(
                "K2/SK1/SSK2",
                "K2/SK1/SSK2"
        );
        assertThat(rsl, is(0));
    }
}