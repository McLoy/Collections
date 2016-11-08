package ua.com.vtkachenko.collections;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class LinkedList3<T> implements List {
    public static final int DEFAULT_SIZE = 0;
    private int size;
    private ListBox<T> first, last;

    public LinkedList3(){
        size = DEFAULT_SIZE;
    }

    private static class ListBox<T>{

        private ListBox<T> prev, next;
        private T element;

        private ListBox(T element, ListBox<T> prev, ListBox<T> next){
            this.element = element;
            this.prev = prev;
            this.next = next;
        }
    }

    private class LinkedList3Iterator<T> implements Iterator<T>{

        private ListBox<T> cursor = (ListBox<T>) first;

        @Override
        public boolean hasNext() {
            return cursor != null;
        }

        @Override
        public T next() {
            T ret;
            if (cursor != null){
                ret = cursor.element;
                cursor = cursor.next;
                return ret;
            }
            return null;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        T curr;
        Iterator it = new LinkedList3Iterator();
        while (it.hasNext()){
            curr = (T) it.next();
            if (curr != null && (curr == (T)o || o.equals(curr))){
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public boolean add(Object o){
        makeLast((T)o);
        return true;
    }

    private void makeLast(T e){
        ListBox<T> l = last;
        ListBox newBox = new ListBox(e, l, null);
        last = newBox;
        if (l == null) {
            first = newBox;
        } else {
            l.next = newBox;
        }
        size++;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean addAll(Collection c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public Object get(int index) {
        return null;
    }

    @Override
    public Object set(int index, Object element) {
        return null;
    }

    @Override
    public void add(int index, Object element) {

    }

    @Override
    public Object remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator listIterator() {
        return null;
    }

    @Override
    public ListIterator listIterator(int index) {
        return null;
    }

    @Override
    public List subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public boolean retainAll(Collection c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection c) {
        return false;
    }

    @Override
    public boolean containsAll(Collection c) {
        return false;
    }

    @Override
    public Object[] toArray(Object[] a) {
        return new Object[0];
    }
}
