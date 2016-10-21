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
}
