package ru.job4j.stream;

import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class MatrixToListTest {
    @Test
    public void whenMatrix3On3ToList() {
        Integer[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        assertThat(
                MatrixToList.matrixToList(matrix),
                is(
                        Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9)
                )
        );
    }
}