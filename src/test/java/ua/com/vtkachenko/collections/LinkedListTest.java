package ua.com.vtkachenko.collections;

import org.fest.assertions.Assertions;
import org.junit.Before;
import org.junit.Test;

public class LinkedListTest {

    private LinkedList2<String> sut;

    @Before
    public void setUp() throws Exception {
        sut = new LinkedList2<>();
    }

    @Test
    public void creating() throws Exception {
        Assertions.assertThat(sut.getClass()).isEqualTo(LinkedList2.class);
    }

    @Test
    public void add() throws Exception {
        sut.add("A");
        sut.add("B");
        Assertions.assertThat(sut).isNotEmpty();

    }
}
