package ua.com.vtkachenko.collections;

import java.util.*;

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
        Iterator it = iterator();
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
        return new LinkedList3Iterator<>();
    }

    @Override
    public Object[] toArray() {
        Object[] arr = new Object[size];
        Iterator it = iterator();
        int count = 0;
        while (it.hasNext()){
            arr[count++] = it.next();
        }
        return arr;
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
        ListBox<T> elem = first;
        while (elem.next != null){
            if (o != null && o.equals(elem.element)){
                clearInnerLinks(elem);
                return true;
            }
            elem = elem.next;
        }
        return false;
    }

    private void clearInnerLinks(ListBox<T> x){
        if (x != first){
            x.prev.next = x.next;
        } else {
            first = first.next;
        }
        if (x != last){
            x.next.prev = x.prev;
        } else {
            last = last.prev;
        }
        x.next = null;
        x.prev = null;
        x.element = null;
        size--;
    }

    @Override
    public boolean addAll(Collection c) {
        boolean flag = false;
        for (Object el:c) {
            ListBox newEntry = new ListBox(el, first, last);
            newEntry.prev.next = newEntry;
            newEntry.next.prev = newEntry;
            size++;
            flag = true;
        }
        return flag;
    }

    @Override
    public boolean addAll(int index, Collection c) {
        boolean flag = false;
        ListBox curr = entry(index);
        for (Object el:c) {
            ListBox newEntry = new ListBox(el, curr, curr.prev);
            newEntry.prev.next = newEntry;
            newEntry.next.prev = newEntry;
            size++;
            flag = true;
        }
        return flag;
    }

    @Override
    public void clear() {
        int count = size;
        for (int i = 0; i < count; i++) {
            remove(0);
        }
    }

    @Override
    public Object get(int index) {
        return entry(index).element;
    }

    @Override
    public Object set(int index, Object element) {
        ListBox newEntry = entry(index);
        newEntry.element = element;
        return element;
    }

    @Override
    public void add(int index, Object element) {
        if (index == 0){
            makeFirst((T)element);
        } else {
            int count = 0;
            ListBox<T> curr = first;
            while (curr != null || curr.next != null) {
                if (count == index - 1) {
                    ListBox<T> newBox = new ListBox<>((T) element, curr, curr.next);
                    curr.next.prev = newBox;
                    curr.next = newBox;
                    size++;
                    break;
                }
                count++;
                curr = curr.next;
            }
        }
    }

    private void makeFirst(T e){
        ListBox<T> f = first;
        ListBox newBox = new ListBox(e, null, f);
        first = newBox;
        if (f == null) {
            last = newBox;
        } else {
            f.prev = newBox;
        }
        size++;
    }

    @Override
    public Object remove(int index) {
        ListBox<T> entry = (index == size ? last : entry(index));
        T value = entry.element;
        clearInnerLinks(entry);
        return value;
    }

    private ListBox<T> entry(int index){
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index: "+index+", Size: "+size);
        ListBox<T> e;
        if (index <= (size >> 1)){
            e = first;
            for (int i = 0; i < index; i++)
                e = e.next;
        }
        else {
            e = last;
            for (int i = size - 1; i > index; i--)
                e = e.prev;
        }
        return e;
    }

    @Override
    public int indexOf(Object o) {
        ListBox<T> elem = first;
        for (int i = 0; i < size; i++) {
            if (o != null && o.equals(elem.element)){
                return i;
            }
            elem = elem.next;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        ListBox<T> elem = last;
        for (int i = size - 1; i > 0; i--) {
            if (o != null && o.equals(elem.element)){
                return i;
            }
            elem = elem.prev;
        }
        return -1;
    }

    @Override
    public ListIterator listIterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator listIterator(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List subList(int fromIndex, int toIndex) {
        ListBox<T> elem = first;
        int length = size;
        for (int i = 0; i < length; i++) {
            elem = elem.next;
            if (i < fromIndex || i >= toIndex){
                if (elem == null){
                    size--;
                } else {
                    clearInnerLinks(elem);
                }
            }
        }
        return this;
    }

    @Override
    public boolean retainAll(Collection c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection c) {
        boolean flag = false;
        int length = size;
        ListBox<T> elem = first;
        T[] arr = (T[]) c.toArray();
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < c.size(); j++) {
                if (arr[j] == null) {
                    if (elem.element == null) {
                        elem = elem.next;
                        clearInnerLinks(elem);
                        flag = true;
                        continue;
                    }
                } else {
                    if (elem.element == arr[j]) {
                        elem = elem.next;
                        clearInnerLinks(elem);
                        flag = true;
                        continue;
                    }
                }
            }
        }
        return flag;
    }

    @Override
    public boolean containsAll(Collection c) {
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
    public Object[] toArray(Object[] a) {
        return (T[])toArray();
    }
}
