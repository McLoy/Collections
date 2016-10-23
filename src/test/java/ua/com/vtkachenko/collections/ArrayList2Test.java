package ua.com.vtkachenko.collections;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.fest.assertions.Assertions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.core.IsInstanceOf.instanceOf;

@RunWith(JUnitParamsRunner.class)
public class ArrayList2Test {

    private ArrayList2<Integer> sut;

    public static Object[][] params() {
        return new Object[][]{
                {8},
                {-1},
                {null},
                {0},
        };
    }

    @Before
    public void setUp() throws Exception {
        sut = new ArrayList2<>();
    }

    @Test
    public void Creating() throws Exception {
        Assert.assertThat(sut, instanceOf(ArrayList2.class));
    }

    @Test
    public void isItEmpty() throws Exception {
        Assertions.assertThat(sut).isEmpty();
    }

    @Test
    public void sizeZero() throws Exception {
        Assertions.assertThat(sut).hasSize(0);
    }

    @Test
    public void containsValues() throws Exception {
        sut.add(1);
        sut.add(2);
        Assertions.assertThat(sut).isNotEmpty();
        Assertions.assertThat(sut).hasSize(2);
        Assertions.assertThat(sut).contains(1, 2);
    }

    @Test
    public void addValueInIndex() throws Exception {
        sut.add(1);
        sut.add(2);
        sut.add(1, 5);
        Assertions.assertThat(sut.get(1)).isEqualTo(5);
        Assertions.assertThat(sut).hasSize(3);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void invalidIndexAdd() throws Exception {
        sut.add(2, 5);
    }

    @Test
    @Parameters(method = "params")
    public void setValue(Integer value) throws Exception {
        sut.add(value);
        Assertions.assertThat(sut.get(0)).isEqualTo(value);
    }

    @Test
    public void testSetValue2() throws Exception {

//
//        String word;
//        sut.add("Tom");
//        sut.add("Jerry");
//        sut.add("Bony");
//        word = sut.set(1, "Megan");
//        assertEquals("Megan", word);

    }

    @Test
    public void testRemoveElementByIndex() throws Exception {


//        sut.add("Tom");
//        sut.add("Jerry");
//        assertEquals("Jerry", sut.remove(1));

    }

    @Test
    public void testRemoveElementByValue() throws Exception {


//        sut.add("Tom");
//        sut.add("Jerry");
//        assertTrue(sut.remove("Tom"));

    }

    @Test
    public void testClear() throws Exception {


//        sut.add("Tom");
//        sut.add("Jerry");
//        sut.clear();
//        assertEquals(null, sut.get(1));

    }

//    @Test
//    public void testLastIndexOfValue() throws Exception {
//
//
//        sut.add("Tom");
//        sut.add("Jerry");
//        sut.add("Jerry");
//        sut.add("Tom");
//        sut.add("Jerry");
//        sut.add("Miranda");
//        assertEquals(-1, sut.lastIndexOf("Hose"));
//
//    }
//
//    @Test
//    public void testLastIndexOfValue2() throws Exception {
//
//
//        sut.add("Mark");
//        sut.add("Jerry");
//        sut.add("Jerry");
//        sut.add("Tom");
//        sut.add("Jerry");
//        sut.add("Miranda");
//        assertEquals(0, sut.lastIndexOf("Mark"));
//
//    }
//
//    @Test
//    public void testLastIndexOfValue3() throws Exception {
//
//
//        sut.add("Mark");
//        sut.add("Jerry");
//        sut.add("Jerry");
//        sut.add("Tom");
//        sut.add("Jerry");
//        sut.add("Miranda");
//        assertEquals(5, sut.lastIndexOf("Miranda"));
//
//    }

    @Test(expected = UnsupportedOperationException.class)
    public void testRetainAll() throws Exception {
        sut.retainAll(new ArrayList2<Object>());
    }

//    @Test
//    public void testIndexOf() throws Exception {
//
//
//        sut.add("Mark");
//        sut.add("Jerry");
//        sut.add("Jerry");
//        sut.add("Tom");
//        sut.add("Jerry");
//        sut.add("Miranda");
//        assertEquals(5, sut.indexOf("Miranda"));
//
//    }
//
//    @Test
//    public void testIndexOf2() throws Exception {
//
//
//        sut.add("Mark");
//        sut.add("Jerry");
//        sut.add("Jerry");
//        sut.add("Tom");
//        sut.add("Jerry");
//        sut.add("Miranda");
//        assertEquals(-1, sut.indexOf("Hose"));
//
//    }
//
//    @Test
//    public void testIndexOf3() throws Exception {
//
//
//        sut.add("Mark");
//        sut.add("Jerry");
//        sut.add("Jerry");
//        sut.add("Tom");
//        sut.add("Jerry");
//        sut.add("Miranda");
//        assertEquals(0, sut.indexOf("Mark"));
//
//    }

//    @Test
//    public void testGetIterator() throws Exception {
//
//        ArrayList2 sut = new ArrayList2();
//        Iterator i = sut.iterator();
//
//    }


//    @Test
//    public void testToArray() throws Exception {
//
//        sut.add("Mark");
//        sut.add("Jerry");
//        sut.add("Jerry");
//        sut.add("Tom");
//        sut.add("Jerry");
//        Object[] array = sut.toArray();
////        System.out.println(array.toString());
////        for (int i = 0; i < array.length; i++) {
////            System.out.print(array[i] + ", ");
////        }
//        assertEquals("Mark", array[0]);
//
//    }
}
