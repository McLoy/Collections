package ua.com.vtkachenko.collections;

import org.junit.Test;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.*;

public class ArrayList2Test {

    @Test
    public void testCreatingArrayList2() throws Exception {

        ArrayList2 arr = new ArrayList2();
        assertThat(arr, instanceOf(ArrayList2.class));

    }

    @Test
    public void testCreatingArrayDefaultSize() throws Exception {

        ArrayList2 arr = new ArrayList2();
        assertEquals(0, arr.size());

    }

    @Test
    public void testCreatingArrayList2WithSomeCapacity() throws Exception {

        ArrayList2 arr = new ArrayList2(2);
        assertEquals(0, arr.size());

    }

    @Test
    public void testWhrenArrayList2IsEmpty() throws Exception {

        ArrayList2<Integer> arr = new ArrayList2<Integer>(2);
        assertEquals(true, arr.isEmpty());

    }

    @Test
    public void testAddToArrayList2() throws Exception {

        ArrayList2<Integer> arr = new ArrayList2<Integer>(2);
        assertTrue(arr.add(5));

    }

    @Test
    public void testArrayList2ContainsValue() throws Exception {

        ArrayList2<Integer> arr = new ArrayList2<Integer>(2);
        arr.add(5);
        assertEquals(true, arr.contains(5));

    }

    @Test
    public void testArrayList2ContainsValueString() throws Exception {

        ArrayList2<String> arr = new ArrayList2<String>(2);
        arr.add("Test");
        assertEquals(true, arr.contains("Test"));

    }

    @Test
    public void testArrayList2AddFewValues() throws Exception {

        ArrayList2<Integer> arr = new ArrayList2<Integer>();
        for (int i = 0; i < 20; i++) {
            arr.add(i+1);
        }
        assertEquals(20, arr.size());

    }

    @Test
    public void testGetElementByIndex() throws Exception {

        ArrayList2<Integer> arr = new ArrayList2<Integer>();
        for (int i = 0; i < 20; i++) {
            arr.add(i);
        }
        assertEquals(5, arr.get(5).intValue());

    }

    @Test
    public void testArrayList2AddValueFromSomePosition() throws Exception {

        ArrayList2<Integer> arr = new ArrayList2<Integer>();
        for (int i = 0; i < 20; i++) {
            arr.add(i);
        }
        arr.add(19, 5);
        assertEquals(5, arr.get(19).intValue());

    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void testArrayList2AddValueFromOutOfBoundsPosition() throws Exception {

        ArrayList2<Integer> arr = new ArrayList2<Integer>();
        for (int i = 0; i < 20; i++) {
            arr.add(i);
        }
        arr.add(30, 5);

    }

    @Test
    public void testAddToArrayList2FewValues() throws Exception {

        ArrayList2<Integer> arr = new ArrayList2<Integer>(2);
        int ind = 1;
        arr.add(1);
        arr.add(2);
        for (int i = 0; i < 3; i++) {
            arr.add(ind, ind + 8);
            ind++;
        }
        assertEquals(11, arr.get(3).intValue());
    }

    @Test
    public void testSetValue() throws Exception {

        ArrayList2<String> arr = new ArrayList2<String>(3);
        arr.add("Tom");
        arr.add("Jerry");
        arr.add("Bony");
        arr.set(1,"Megan");
        assertEquals("Megan", arr.get(1).toString());

    }

    @Test
    public void testSetValue2() throws Exception {

        ArrayList2<String> arr = new ArrayList2<String>(3);
        String word;
        arr.add("Tom");
        arr.add("Jerry");
        arr.add("Bony");
        word = arr.set(1,"Megan");
        assertEquals("Megan", word);

    }

    @Test
    public void testRemoveElementByIndex() throws Exception {

        ArrayList2<String> arr = new ArrayList2<String>(2);
        arr.add("Tom");
        arr.add("Jerry");
        assertEquals("Jerry", arr.remove(1));

    }

    @Test
    public void testRemoveElementByValue() throws Exception {

        ArrayList2<String> arr = new ArrayList2<String>(2);
        arr.add("Tom");
        arr.add("Jerry");
        assertTrue(arr.remove("Tom"));

    }

    @Test
    public void testClear() throws Exception {

        ArrayList2<String> arr = new ArrayList2<String>(2);
        arr.add("Tom");
        arr.add("Jerry");
        arr.clear();
        assertEquals(null, arr.get(1));

    }

    @Test
    public void testLastIndexOfValue() throws Exception {

        ArrayList2<String> arr = new ArrayList2<String>(2);
        arr.add("Tom");
        arr.add("Jerry");
        arr.add("Jerry");
        arr.add("Tom");
        arr.add("Jerry");
        arr.add("Miranda");
        assertEquals(-1, arr.lastIndexOf("Hose"));

    }

    @Test
    public void testLastIndexOfValue2() throws Exception {

        ArrayList2<String> arr = new ArrayList2<String>(2);
        arr.add("Mark");
        arr.add("Jerry");
        arr.add("Jerry");
        arr.add("Tom");
        arr.add("Jerry");
        arr.add("Miranda");
        assertEquals(0, arr.lastIndexOf("Mark"));

    }

    @Test
    public void testLastIndexOfValue3() throws Exception {

        ArrayList2<String> arr = new ArrayList2<String>(2);
        arr.add("Mark");
        arr.add("Jerry");
        arr.add("Jerry");
        arr.add("Tom");
        arr.add("Jerry");
        arr.add("Miranda");
        assertEquals(5, arr.lastIndexOf("Miranda"));

    }
}
