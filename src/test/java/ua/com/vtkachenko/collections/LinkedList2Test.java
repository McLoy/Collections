package ua.com.vtkachenko.collections;

import org.fest.assertions.Assertions;
import org.junit.Before;
import org.junit.Test;

public class LinkedList2Test {

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
    public void addSize() throws Exception {
        sut.add("A");
        sut.add("B");
        Assertions.assertThat(sut).isNotEmpty();
        Assertions.assertThat(sut.size()).isEqualTo(2);
        Assertions.assertThat(sut.isEmpty()).isFalse();
    }

    @Test
    public void contents() throws Exception {
        sut.add("E");
        sut.add("A");
        Assertions.assertThat(sut.contains("A")).isTrue();
        Assertions.assertThat(sut.contains("B")).isFalse();
    }

    @Test
    public void iterator() throws Exception {
        Assertions.assertThat(sut.iterator().hasNext()).isFalse();
        sut.add("A");
        sut.add("B");
        Assertions.assertThat(sut.iterator().hasNext()).isTrue();
        Assertions.assertThat(sut.iterator().next()).isEqualTo("A");
    }

    @Test
    public void addByIndex() throws Exception {
        sut.add("A");
        sut.add("B");
        sut.add("C");
        sut.add("D", 1);
        Assertions.assertThat(sut.size()).isEqualTo(sut.size()+1);


    }
}
