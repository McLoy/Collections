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
}
