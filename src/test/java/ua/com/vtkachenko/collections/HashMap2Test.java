package ua.com.vtkachenko.collections;

import org.fest.assertions.Assertions;
import org.junit.Before;
import org.junit.Test;

public class HashMap2Test {

    private HashMap2<String, String> sut;
    private HashMap2<Integer, Integer> sutInt;

    @Before
    public void setUp() throws Exception{
        sut = new HashMap2<>();
        sutInt = new HashMap2<>();
    }

    @Test
    public void isEmpty() throws Exception{
        Assertions.assertThat(sut).isEmpty();
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
        Assertions.assertThat(sut).hasSize(3);
        sut.put("idx", "two");
    }

    @Test
    public void incrementSize() throws Exception {
        HashMap2<String, String> sut2 = new HashMap2<>(3);
        sut2.put("0", "zero");
        sut2.put("key", "one");
        sut2.put(null, null);
        sut2.put("0", "2-zero");
        Assertions.assertThat(sut2.put("0", "3-zero")).isEqualTo("2-zero");
        sut2.put(null, "Surprise");
        sut2.put(null, "2-Surprise");
        Assertions.assertThat(sut2).hasSize(3);
    }

    @Test
    public void getKey() throws Exception {
        sut.put("8", "rororo");
        sut.put("0", "zero");
        sut.put("0", "2-zero");
        sut.put("2", "zr");
        sut.put("2", "zopr");
        sut.put("8", "ro");
        Assertions.assertThat(sut.get("2")).isEqualTo("zopr");
        Assertions.assertThat(sut.get("8")).isEqualTo("ro");
    }

    @Test
    public void containsKey() throws Exception {
        sut.put(null, "AA");
        sut.put("aa", "AA");
        sut.put("bb", "BB");
        sut.put("cc", "CC");
        Assertions.assertThat(sut.containsKey("aa")).isTrue();
    }

    @Test
    public void containsValue() throws Exception {
        sut.put(null, null);
        sut.put("aa", "AA");
        sut.put("bb", "BB");
        sut.put("cc", "CC");
        Assertions.assertThat(sut.containsValue("NN")).isFalse();
        Assertions.assertThat(sut.containsValue("BB")).isTrue();
        Assertions.assertThat(sut.containsValue(5)).isFalse();
        Assertions.assertThat(sut.containsValue(null)).isTrue();
    }

    @Test
    public void remove() throws Exception {
        sut.put("0", "zero");
        sut.put("key", "one");
        sut.put(null, null);
        sut.put("0", "2-zero");
        sut.put("0", "3-zero");
        sut.put(null, "Surprise");
        sut.put(null, "2-Surprise");
        Assertions.assertThat(sut.remove("key")).isEqualTo("3-zero");
    }

    @Test
    public void clear() throws Exception {
        sut.put("aa", "AA");
        sut.put("bb", "BB");
        sut.clear();
        Assertions.assertThat(sut).isEmpty();
    }

    @Test
    public void putInt() throws Exception {
        sutInt.put(88,10);
        sutInt.put(99,20);
        sutInt.put(5,40);
        sutInt.put(39,50);
    }
}
