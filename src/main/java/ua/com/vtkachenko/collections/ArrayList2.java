package ua.com.vtkachenko.collections;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ArrayList2<V> implements List<V> {

    private int size;
    private V[] elementData;
    private int capacity = 0;

    public ArrayList2(){

        size = 0;
        capacity = 10;
        elementData = (V[]) new Object[capacity];

    }

    public ArrayList2(int capacity){

        this.capacity = capacity;
        elementData = (V[]) new Object[capacity];

    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(Object o) {

        for (V el:elementData) {
            if (el == (V)o)
                return true;
        };

        return false;
    }

    public Iterator<V> iterator() {
        return null;
    } ////???????

    public Object[] toArray() {

        return (Object[]) elementData;

    }

    public <T> T[] toArray(T[] a) {
        return null;
    } ////???????

    public boolean add(V v) {

        //Достаточно ли места в массиве для вставки нового элемента
        ensureCapacity(size + 1);
        //Добавляется значение в конец согласно значению size
        elementData[size++] = v;
        return true;

    }

    private void ensureCapacity(int s){

        if (s > capacity){
            capacity = (capacity*3)/2 + 1;
            V[] oldData = elementData.clone();
            elementData = (V[]) new Object[capacity];
            System.arraycopy(oldData, 0, elementData, 0, size);
        }

    }

    public boolean remove(Object o) {

        for (int i = 0; i < size; i++) {
            if (elementData[i] == (V)o){
                remove(i);
                return true;
            }
        }

        return false;
    }

    public boolean containsAll(Collection<?> c) {
        return false;
    } ////???????

    public boolean addAll(Collection<? extends V> c) {
        return false;
    } ////???????

    public boolean addAll(int index, Collection<? extends V> c) {
        return false;
    } ////???????

    public boolean removeAll(Collection<?> c) {
        return false;
    } ////???????

    public boolean retainAll(Collection<?> c) {

        throw new UnsupportedOperationException();

    }

    public void clear() {

        for (int i = 0; i < size; i++) {
                elementData[i] = null;
            }

    }

    public V get(int index) {

        return elementData[index];

    }

    public V set(int index, V element) {

        elementData[index] = element;
        return element;

    }

    public void add(int index, V element) {

        ensureCapacity(size + 1);
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = element;
        size++;

    }

    public V remove(int index) {

        V deletedElement = elementData[index];
        int numMoved = size - index - 1;
        System.arraycopy(elementData, index + 1, elementData, index, numMoved);
        elementData[--size] = null;
        return deletedElement;
    }

    public int indexOf(Object o) {

        for (int i = 0; i < size ; i++) {
            if (elementData[i] == (V)o) return i;
        }
        return -1;
    }

    public int lastIndexOf(Object o) {

        for (int i = size - 1; i >= 0 ; i--) {
            if (elementData[i] == (V)o) return i;
        }
        return -1;
    }

    public ListIterator<V> listIterator() {
        throw new UnsupportedOperationException();
    }

    public ListIterator<V> listIterator(int index) {
        throw new UnsupportedOperationException();
    }

    public List<V> subList(int fromIndex, int toIndex) {

        throw new UnsupportedOperationException();

    }

}
