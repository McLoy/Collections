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
    public void creating() throws Exception {
        Assert.assertThat(sut, instanceOf(ArrayList2.class));
        ArrayList2 arr = new ArrayList2(4);
        Assert.assertThat(arr, instanceOf(ArrayList2.class));
    }

    @Test
    public void isItEmpty() throws Exception {
        Assertions.assertThat(sut).isEmpty();
        Assertions.assertThat(sut).hasSize(0);
    }

    @Test
    public void containsValues() throws Exception {
        sut.add(1);
        sut.add(null);
        Assertions.assertThat(sut).isNotEmpty();
        Assertions.assertThat(sut).hasSize(2);
        Assertions.assertThat(sut.contains(1)).isTrue();
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
    public void replaceElement() throws Exception {
        sut.add(1);
        sut.add(2);
        sut.set(1, 5);
        Assertions.assertThat(sut.get(1)).isEqualTo(5);
        Assertions.assertThat(sut).hasSize(2);
    }

    @Test
    public void removeElement() throws Exception {
        sut.add(1);
        sut.add(2);
        sut.add(3);
        sut.remove(1);
        Assertions.assertThat(sut).hasSize(2);
        Assertions.assertThat(sut.get(1)).isEqualTo(3);
        sut.remove((Integer)1);
        Assertions.assertThat(sut).hasSize(1);
        Assertions.assertThat(sut.get(0)).isEqualTo(3);
    }

    @Test
    public void clear() throws Exception {
        sut.add(5);
        sut.add(10);
        sut.clear();
        Assertions.assertThat(sut).isEmpty();
    }

    @Test
    public void lastIndexOfElement() throws Exception {
        sut.add(1);
        sut.add(3);
        sut.add(1);
        Assertions.assertThat(sut.lastIndexOf(4)).isEqualTo(-1);
        Assertions.assertThat(sut.lastIndexOf(1)).isEqualTo(2);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void retainAll() throws Exception {
        sut.retainAll(new ArrayList2<Object>());
    }

    @Test
    public void indexOf() throws Exception {
        sut.add(1);
        sut.add(2);
        sut.add(3);
        Assertions.assertThat(sut.indexOf(1)).isEqualTo(0);
        Assertions.assertThat(sut.indexOf(8)).isEqualTo(-1);
    }

    @Test
    public void iteratorHasNext() throws Exception {
        sut.add(1);
        sut.add(2);
        sut.add(3);
        Assertions.assertThat(sut.iterator().hasNext()).isTrue();
        Assertions.assertThat(sut.iterator().next()).isEqualTo(1);
    }

    @Test
    public void toArray() throws Exception {
        sut.add(1);
        sut.add(2);
        Assertions.assertThat(sut.toArray()[1]).isEqualTo(2);
    }

    @Test
    public void containsAll() throws Exception {
        sut.add(1);
        sut.add(2);
        sut.add(3);
        sut.add(4);

        ArrayList2<Integer> arr = new ArrayList2<Integer>();
        arr.add(3);
        arr.add(1);

        Assertions.assertThat(sut.containsAll(arr)).isTrue();
    }

    @Test
    public void addAll() throws Exception {
        sut.add(1);
        sut.add(2);
        sut.add(3);
        sut.add(4);
        ArrayList2<Integer> arr = new ArrayList2<Integer>();
        arr.add(5);
        arr.add(6);
        arr.add(7);
        arr.add(8);
        Assertions.assertThat(sut.addAll(arr)).isTrue();
        Assertions.assertThat(sut.addAll(1, arr)).isTrue();
    }
}
