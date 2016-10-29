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
    public void defineCapacity() throws Exception {
        HashMap2<String, String> hmWithCapacity = new HashMap2<>(1073741825);
    }
}
