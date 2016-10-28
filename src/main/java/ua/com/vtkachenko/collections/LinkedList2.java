package ua.com.vtkachenko.collections;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class LinkedList2<T> implements List<T> {

    private int size;
    private Entry header;

    LinkedList2(){
        size = 0;
        header = new Entry(null, header, header);
        header.next = header.prev = header;
    }

    private static class Entry<T>
    {
        T element;
        Entry<T> next;
        Entry<T> prev;

        Entry(T element, Entry<T> next, Entry<T> prev)
        {
            this.element = element;
            this.next = next;
            this.prev = prev;
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
        Entry<T> elem = header.next;
        for (int i = 0; i < size; i++) {
            if (o == null){
                if (elem.element == null)
                    return true;
            } else {
                if (elem.element == o)
                    return true;
            }
            elem = elem.next;
        }
        return false;
    }

    private class LinkedList2Iterator<T> implements Iterator<T>{

        private Entry<T> cursor = header.next;
        @Override
        public boolean hasNext() {
            return cursor.next != cursor;//index < size;
        }

        @Override
        public T next() {
            if (cursor.next != cursor){
                cursor = cursor.next;
                return cursor.prev.element;
            }
            return null;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedList2Iterator<>();
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(T t) {
        Entry newEntry = new Entry(t, header, header.prev);
        newEntry.prev.next = newEntry;
        newEntry.next.prev = newEntry;
        size++;
        return size > 0;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public T get(int index) {
        return null;
    }

    @Override
    public T set(int index, T element) {
        return null;
    }

    @Override
    public void add(int index, T element) {

    }

    @Override
    public T remove(int index) {
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
    public ListIterator<T> listIterator() {
        return null;
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return null;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return null;
    }
}
