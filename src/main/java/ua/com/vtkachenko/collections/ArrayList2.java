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
    }

    public Object[] toArray() {
        return new Object[0];
    }

    public <T> T[] toArray(T[] a) {
        return null;
    }

    public boolean add(V v) {

        //Достаточно ли места в массиве для вставки нового элемента
        if (ensureCapacity(size + 1))
        //Добавляется значение в конец согласно значению size
            elementData[size++] = v;
        else {
            capacity = (capacity*3)/2 + 1;
            V[] oldData = elementData.clone();
            elementData = (V[]) new Object[capacity];
            System.arraycopy(oldData, 0, elementData, 0, size);
            elementData[size++] = v;
        }
        return true;
    }

    boolean ensureCapacity(int s){

        return s <= capacity;

    }

    public boolean remove(Object o) {
        return false;
    }

    public boolean containsAll(Collection<?> c) {
        return false;
    }

    public boolean addAll(Collection<? extends V> c) {
        return false;
    }

    public boolean addAll(int index, Collection<? extends V> c) {
        return false;
    }

    public boolean removeAll(Collection<?> c) {
        return false;
    }

    public boolean retainAll(Collection<?> c) {
        return false;
    }

    public void clear() {

    }

    public V get(int index) {

        for (int i = 0; i < elementData.length; i++) {
            if (i == index) return elementData[i];
        }

        return null;
    }

    public V set(int index, V element) {
        return null;
    }

    public void add(int index, V element) {

        if (index + 1 > size) {
            throw new IndexOutOfBoundsException();
        } else {

            for (int i = 0; i < size; i++) {
                if (i == index) elementData[i] = element;
            }
        }

    }

    public V remove(int index) {
        return null;
    }

    public int indexOf(Object o) {
        return 0;
    }

    public int lastIndexOf(Object o) {
        return 0;
    }

    public ListIterator<V> listIterator() {
        return null;
    }

    public ListIterator<V> listIterator(int index) {
        return null;
    }

    public List<V> subList(int fromIndex, int toIndex) {
        return null;
    }
}
