package ua.com.vtkachenko.collections;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class LinkedList2<T> implements List<T> {

    private int size;
    private Entry header;

    public LinkedList2(){
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

        private Entry<T> cursor = header;
        @Override
        public boolean hasNext() {
            return cursor.next != header && cursor.next != cursor;
        }

        @Override
        public T next() {
            if (cursor.next != header && cursor.next != cursor){
                cursor = cursor.next;
                return cursor.element;
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
        Object[] arr = new Object[size];
        Iterator it = iterator();
        int count = 0;
//        for (T t :this) {
//            arr[count++] = t;
//        }

        while (it.hasNext()){
            arr[count++] = it.next();
        }
        return arr;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return (T[])toArray();
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
        Entry<T> elem = header.next;
        for (int i = 0; i < size; i++) {
            if (o == null){
                if (elem.element == null){
                    clearInnerLinks(elem);
                    return true;
                }
            } else {
                if (elem.element == o){
                    clearInnerLinks(elem);
                    return true;
                }
            }
            elem = elem.next;
        }
        return false;
    }

    private void clearInnerLinks(Entry<T> x){
        x.prev.next = x.next;
        x.next.prev = x.prev;
        x.next = null;
        x.prev = null;
        x.element = null;
        size--;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        T[] arr = (T[]) c.toArray();
        boolean flag = false;
            for (int j = 0; j < arr.length; j++) {
                if (contains(arr[j])) {
                    flag = true;
                } else
                    flag = false;
            }
        return flag;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        boolean flag = false;
        for (T el:c) {
            Entry newEntry = new Entry(el, header, header.prev);
            newEntry.prev.next = newEntry;
            newEntry.next.prev = newEntry;
            size++;
            flag = true;
        }
        return flag;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        boolean flag = false;
        Entry curr = entry(index);
        for (T el:c) {
            Entry newEntry = new Entry(el, curr, curr.prev);
            newEntry.prev.next = newEntry;
            newEntry.next.prev = newEntry;
            size++;
            flag = true;
        }
        return flag;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean flag = false;
        int length = size;
        Entry<T> elem = header.next;
        T[] arr = (T[]) c.toArray();
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < c.size(); j++) {
                if (arr[j] == null) {
                    if (elem.element == null) {
                        elem = elem.next;
                        clearInnerLinks(elem.prev);
                        flag = true;
                        continue;
                    }
                } else {
                    if (elem.element == arr[j]) {
                        elem = elem.next;
                        clearInnerLinks(elem.prev);
                        flag = true;
                        continue;
                    }
                }
            }
        }
        return flag;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        Iterator it = iterator();
        while (it.hasNext()){
            remove(0);
        }
    }

    @Override
    public T get(int index) {
        return entry(index).element;
    }

    @Override
    public T set(int index, T element) {
        Entry newEntry = entry(index);
        newEntry.element = element;
        return element;
    }

    @Override
    public void add(int index, T element) {
        Entry entry = (index == size ? header : entry(index));
        Entry newEntry = new Entry(element, entry, entry.prev);
        newEntry.prev.next = newEntry;
        newEntry.next.prev = newEntry;
        size++;
    }

    private Entry<T> entry(int index){
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index: "+index+", Size: "+size);
        Entry<T> e = header;
        if (index < (size >> 1)){
            for (int i = 0; i <= index; i++)
                e = e.next;
        }
        else {
            for (int i = size; i > index; i--)
                e = e.prev;
        }
        return e;
    }

    @Override
    public T remove(int index) {
        Entry entry = (index == size ? header : entry(index));
        T value = (T)entry.element;
        clearInnerLinks(entry);
        return value;
    }

    @Override
    public int indexOf(Object o) {
        Entry<T> elem = header.next;
        for (int i = 0; i < size; i++) {
            if (o == null){
                if (elem.element == null){
                    return i;
                }
            } else {
                if (elem.element == o){
                    return i;
                }
            }
            elem = elem.next;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        Entry<T> elem = header.prev;
        for (int i = size - 1; i > 0; i--) {
            if (o == null){
                if (elem.element == null){
                    return i;
                }
            } else {
                if (elem.element == o){
                    return i;
                }
            }
            elem = elem.prev;
        }
        return -1;
    }

    @Override
    public ListIterator<T> listIterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        Entry<T> elem = header.next;
        int length = size;
        for (int i = 0; i < length; i++) {
            elem = elem.next;
            if (i < fromIndex || i >= toIndex){
                    clearInnerLinks(elem.prev);
                }
        }
        return this;
    }
}
