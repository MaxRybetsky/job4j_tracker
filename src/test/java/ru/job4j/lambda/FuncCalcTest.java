package ru.job4j.lambda;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class FuncCalcTest {
    @Test
    public void whenLinearFunctionThenLinearResults() {
        List<Double> result = FuncCalc.diapason(5, 8, x -> 2 * x + 1);
        List<Double> expected = Arrays.asList(11D, 13D, 15D, 17D);
        assertThat(result, is(expected));
    }

    @Test
    public void whenExponentialFunctionThenExponentialResults() {
        List<Double> result = FuncCalc.diapason(0, 10, x -> x * x);
        List<Double> expected = Arrays.asList(0D, 1D, 4D, 9D, 16D, 25D, 36D, 49D, 64D, 81D, 100D);
        assertThat(result, is(expected));
    }

    @Test
    public void whenQuadraticFunctionThenQuadraticResults() {
        List<Double> result = FuncCalc.diapason(0, 10, x -> Math.pow(2D, x));
        List<Double> expected = Arrays.asList(1D, 2D, 4D, 8D, 16D, 32D, 64D, 128D, 256D, 512D, 1024D);
        assertThat(result, is(expected));
    }
}