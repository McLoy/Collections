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
        return false;
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
        return null;
    }

    public V set(int index, V element) {
        return null;
    }

    public void add(int index, V element) {

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
