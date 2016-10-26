package ua.com.vtkachenko.collections;

import java.util.*;

public class ArrayList2<T> implements List<T> {

    public static final int DEFAULT_CAPACITY = 0;
    private int size;
    private T[] elementData;
    private int capacity = 0;

    public ArrayList2() {
        this(DEFAULT_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public ArrayList2(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        this.elementData = (T[]) new Object[capacity];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(Object o) {

        for (T el : elementData) {
            boolean contains;
            if (el != null) {
                contains = el.equals(o);
            } else {
                contains = el == o;
            }
            if (contains)
                return true;
        }

        return false;
    }

    private class ArrayList2Iterator<E> implements Iterator<E> {
        private int index = 0;

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        @SuppressWarnings("unchecked")
        public E next() {
            if (index < size && index < elementData.length){
                return (E)elementData[index++];
            }
            throw new NoSuchElementException();
        }
    }

    public Iterator<T> iterator() {
        return new ArrayList2Iterator<>();
    }

    public Object[] toArray() {
        return (Object[]) elementData;
    }

    public <Z> Z[] toArray(Z[] a) {
        return (Z[]) elementData;
    }

    public boolean add(T v) {
        ensureCapacity(size + 1);
        elementData[size++] = v;
        return true;
    }

    private void ensureCapacity(int s) {

        if (s > capacity) {
            while (s > capacity){
            capacity = (capacity * 3) / 2 + 1;}
            T[] oldData = elementData.clone();
            elementData = (T[]) new Object[capacity];
            System.arraycopy(oldData, 0, elementData, 0, size);
        }

    }

    public boolean remove(Object o) {
        for (int i = 0; i < size; i++) {
            if (elementData[i] == (T) o) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean containsAll(Collection<?> c) {
        if (!c.isEmpty()) {
            boolean contains = false;
            for (Object el2 : c) {
                for (T el1 : elementData) {
                    if (el1 != null) {
                        contains = el1.equals(el2);
                        if (contains) break;
                    } else {
                        contains = el1 == el2;
                        if (contains) break;
                    }
                }
                if (!contains) return false;
            }
            if (contains) return true;}
        return false;
    }

    public boolean addAll(Collection<? extends T> c) {
        addAll(0, c);
        return true;
    }

    public boolean addAll(int index, Collection<? extends T> c) {
        if (!c.isEmpty()){
            if (index >= 0) {
                if (index > 0) {
                    int countEl = size - index;
                    T[] partOfArray = (T[]) new Object[countEl];
                    System.arraycopy(elementData, index, partOfArray, 0, countEl);
                    ensureCapacity(size + c.size());
                    System.arraycopy(c.toArray(), 0, elementData, index, c.size());
                    size += c.size();
                    System.arraycopy(partOfArray, 0, elementData, index + c.size(), partOfArray.length);
                } else if (index == 0) {
                    int countVal = elementData.length;
                    ensureCapacity(size + c.size());
                    System.arraycopy(c.toArray(), 0, elementData, countVal, c.size());
                    size += c.size();
                }
                return true;
            }
        }
        return false;
    }

    public boolean removeAll(Collection<?> c) {
        if (!c.isEmpty()) {
            for (Object el: c) {
                remove(el);
            }
            return true;
        }
        return false;
    }

    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    public void clear() {
        size = DEFAULT_CAPACITY;
        capacity = DEFAULT_CAPACITY;
        elementData = (T[]) new Object[DEFAULT_CAPACITY];
    }

    public T get(int index) {
        return elementData[index];
    }

    public T set(int index, T element) {
        elementData[index] = element;
        return element;
    }

    public void add(int index, T element) {
        ensureCapacity(size + 1);
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = element;
        size++;
    }

    public T remove(int index) {
        T deletedElement = elementData[index];
        int numMoved = size - index - 1;
        System.arraycopy(elementData, index + 1, elementData, index, numMoved);
        elementData[--size] = null;
        return deletedElement;
    }

    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (elementData[i] == (T) o) return i;
        }
        return -1;
    }

    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; i--) {
            if (elementData[i] == (T) o) return i;
        }
        return -1;
    }

    public ListIterator<T> listIterator() {
        throw new UnsupportedOperationException();
    }

    public ListIterator<T> listIterator(int index) {
        throw new UnsupportedOperationException();
    }

    public List<T> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

}
