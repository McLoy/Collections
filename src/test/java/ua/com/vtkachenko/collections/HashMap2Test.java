package ua.com.vtkachenko.collections;

import org.fest.assertions.Assertions;
import org.junit.Before;
import org.junit.Test;

public class HashMap2Test {

    private HashMap2<String, String> sut;

    @Before
    public void setUp() throws Exception{
        sut = new HashMap2<>();
    }

    @Test
    public void isEmpty() throws Exception{
        Assertions.assertThat(sut).isEmpty();
    }

    @Test(expected = IllegalArgumentException.class)
    public void defineCapacityException() throws Exception {
        HashMap2<String, String> hm1 = new HashMap2<>(1073741825);
    }

    @Test
    public void put() throws Exception {
        sut.put("0", "zero");
        sut.put("key", "one");
        sut.put(null, null);
        sut.put("0", "2-zero");
        sut.put("0", "3-zero");
        sut.put(null, "Surprise");
        sut.put(null, "2-Surprise");
        Assertions.assertThat(sut).isNotEmpty();
        Assertions.assertThat(sut).hasSize(7);
        sut.put("idx", "two");
    }

    @Test
    public void incrementSize() throws Exception {
        HashMap2<String, String> sut2 = new HashMap2<>(5);
        Assertions.assertThat(sut2).hasSize(0);
        sut2.put("0", "zero");
        sut2.put("1", "one");
        sut2.put("2", "two");
        sut2.put("3", "three");
        sut2.put("4", "four");
        sut2.put("5", "five");
        sut2.put("6", "six");
        sut2.put("7", "seven");
        Assertions.assertThat(sut2).hasSize(8);
    }
}
