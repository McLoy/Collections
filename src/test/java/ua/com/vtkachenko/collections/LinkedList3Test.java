package ua.com.vtkachenko.collections;

import org.fest.assertions.Assertions;
import org.junit.Before;
import org.junit.Test;

public class LinkedList3Test {

    private LinkedList3<String> sut;

    @Before
    public void setUp() throws Exception {
        sut = new LinkedList3<>();
    }

    @Test
    public void creating() throws Exception {
        Assertions.assertThat(sut.getClass()).isEqualTo(LinkedList3.class);
    }

    @Test
    public void addSize() throws Exception {
        sut.add("A");
        sut.add("B");
        sut.add("C");
        Assertions.assertThat(sut).isNotEmpty();
        Assertions.assertThat(sut.size()).isEqualTo(3);
        Assertions.assertThat(sut.isEmpty()).isFalse();
    }

    @Test
    public void contains() throws Exception {
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
        sut.add(1, "D");
        Assertions.assertThat(sut.size()).isEqualTo(4);
    }

    @Test
    public void removeByValue() throws Exception {
        sut.add("A");
        sut.add("B");
        sut.add("C");
        Assertions.assertThat(sut.remove("B")).isTrue();
        Assertions.assertThat(sut.size()).isEqualTo(2);
    }

    @Test
    public void removeByIndex() throws Exception {
        sut.add("A");
        sut.add("B");
        sut.add("C");
        Assertions.assertThat(sut.remove(1)).isEqualTo("B");
        Assertions.assertThat(sut.size()).isEqualTo(2);
    }

    @Test
    public void toArray() throws Exception {
        sut.add("A");
        sut.add("B");
        sut.add("C");
        Assertions.assertThat(sut.toArray()).isNotEmpty();
        String[] arr = new String[sut.size()];
        String[] arr2 = new String[sut.size()];
        arr2[0] = "A";
        arr2[1] = "B";
        arr2[2] = "C";
        Assertions.assertThat(sut.toArray(arr)).isEqualTo(arr2);
    }

    @Test
    public void clear() throws Exception {
        sut.add("A");
        sut.add("B");
        sut.clear();
        Assertions.assertThat(sut).isEmpty();
        Assertions.assertThat(sut.size()).isEqualTo(0);
    }

    @Test
    public void get() throws Exception {
        sut.add("A");
        sut.add("B");
        sut.add("C");
        Assertions.assertThat(sut.get(1)).isEqualTo("B");
    }

    @Test
    public void set() throws Exception {
        sut.add("A");
        sut.add("B");
        sut.add("C");
        Assertions.assertThat(sut.set(1, "D")).isEqualTo("D");
    }

    @Test
    public void indexOf() throws Exception {
        sut.add("A");
        sut.add("B");
        sut.add("C");
        Assertions.assertThat(sut.indexOf("A")).isEqualTo(0);
        Assertions.assertThat(sut.indexOf("F")).isEqualTo(-1);
    }

    @Test
    public void lastIndexOf() throws Exception {
        sut.add("K");
        sut.add("B");
        sut.add("A");
        sut.add("K");
        sut.add("B");
        sut.add("A");
        Assertions.assertThat(sut.lastIndexOf("A")).isEqualTo(5);
        Assertions.assertThat(sut.lastIndexOf("K")).isEqualTo(3);
    }

    @Test
    public void sublist() throws Exception {
        sut.add("A");
        sut.add("B");
        sut.add("C");
        sut.add("D");
        sut.add("E");
        sut.add("F");
        Assertions.assertThat(sut.subList(0, 4).size()).isEqualTo(4);
    }

    @Test
    public void removeAll() throws Exception {
        sut.add("A");
        sut.add("B");
        sut.add("C");
        ArrayList2 arr = new ArrayList2();
        arr.add("C");
        arr.add("A");
        Assertions.assertThat(sut.removeAll(arr)).isTrue();
    }

    @Test
    public void addAll() throws Exception {
        sut.add("A");
        sut.add("B");
        sut.add("C");
        ArrayList2 arr = new ArrayList2();
        arr.add("C");
        arr.add("A");
        Assertions.assertThat(sut.addAll(arr)).isTrue();
    }

    @Test
    public void addAllByIndex() throws Exception {
        sut.add("A");
        sut.add("B");
        sut.add("C");
        ArrayList2 arr = new ArrayList2();
        arr.add("C");
        arr.add("E");
        Assertions.assertThat(sut.addAll(1, arr)).isTrue();
    }

    @Test
    public void containsAll() throws Exception {
        sut.add("A");
        sut.add("B");
        sut.add("C");
        ArrayList2 arr = new ArrayList2();
        arr.add("C");
        arr.add("A");
        Assertions.assertThat(sut.containsAll(arr)).isTrue();
    }
}
