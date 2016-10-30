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

//    @Test
//    public void defineCapacity() throws Exception {
//        HashMap2<String, String> hm2 = new HashMap2<>(1073741824);
//    }


    @Test
    public void put() throws Exception {
        sut.put("0", "zero");
        sut.put("key", "one");
        sut.put(null, null);
        sut.put("0", "2-zero");
        sut.put("0", "3-zero");
        Assertions.assertThat(sut).isNotEmpty();
    }
}
