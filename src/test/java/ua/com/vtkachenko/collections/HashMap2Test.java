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

//    @Test(expected = IllegalArgumentException.class)
//    public void defineCapacityException() throws Exception {
//        HashMap2<String, String> hm1 = new HashMap2<>(1073741825);
//    }

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
        HashMap2<String, String> sut2 = new HashMap2<>(7);
        Assertions.assertThat(sut2).hasSize(0);
        sut2.put("0", "zero");
        sut2.put("key", "one");
        sut2.put(null, null);
        sut2.put("0", "2-zero");
        Assertions.assertThat(sut2.put("0", "3-zero")).isEqualTo("2-zero");
        sut2.put(null, "Surprise");
        sut2.put(null, "2-Surprise");
        Assertions.assertThat(sut2).hasSize(7);
    }

    @Test
    public void getKey() throws Exception {
        sut.put("0", "zero");
        sut.put("2", "zr");
        sut.put("8", "ro");
        Assertions.assertThat(sut.get("2")).isEqualTo("zr");
        Assertions.assertThat(sut.get("8")).isEqualTo("ro");
    }

    @Test
    public void containsKey() throws Exception {
        sut.put("aa", "AA");
        sut.put("bb", "BB");
        sut.put("cc", "CC");
        Assertions.assertThat(sut.containsKey("cc")).isTrue();
    }

    @Test
    public void containsValue() throws Exception {
        sut.put("aa", "AA");
        sut.put("bb", "BB");
        sut.put("cc", "CC");
        Assertions.assertThat(sut.containsValue("NN")).isFalse();
        Assertions.assertThat(sut.containsValue("BB")).isTrue();
    }

    @Test
    public void remove() throws Exception {
//        sut.put("0", "zero");
//        sut.put("key", "one");
//        sut.put(null, null);
//        sut.put("0", "2-zero");
//        sut.put("0", "3-zero");
//        sut.put(null, "Surprise");
//        sut.put(null, "2-Surprise");
//        Assertions.assertThat(sut.remove("key")).isEqualTo("zero");
    }

    @Test
    public void clear() throws Exception {
        sut.put("aa", "AA");
        sut.put("bb", "BB");
        sut.clear();
        Assertions.assertThat(sut).isEmpty();
    }
}
