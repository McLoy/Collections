package ua.com.vtkachenko.collections;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class HashMap2<K,V> implements Map<K,V> {

    public static final int DEFAULT_CAPACITY = 16;
    public static final int MAXIMUM_CAPACITY = Integer.MAX_VALUE/2;
    public static final double DEFAULT_LOADFACTOR = 0.75;
    private MyEntry<K,V>[] table;
    private double loadFactor;
    private int capacity = DEFAULT_CAPACITY;
    private int size, threshold;

    public HashMap2(){
        this(DEFAULT_CAPACITY);
    }
    public HashMap2(int capacity){
        this(capacity, DEFAULT_LOADFACTOR);
    }
    public HashMap2(int capacity, double loadFactor){
        this.capacity = capacity;
        this.loadFactor = loadFactor;
        this.table = new MyEntry[capacity];
        this.size = 0;
        this.threshold = thresholdCalc();
    }

    private static class MyEntry<K,V>{
        V value;
        K key;
        int hash;
        MyEntry<K,V> next;

        MyEntry(int hash, K key, V value, MyEntry<K,V> next){
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            MyEntry<K,V> elem = (MyEntry<K,V>)o;
            if (key == null && elem.key == null) {
                return value == elem.value;
            } else {
                return key == elem.key & value == elem.value;
            }
        }

        @Override
        public int hashCode() {
            int rezult = 17;
            int hcValue = value != null ? value.hashCode() : 0;
            int hcKey = key != null ? key.hashCode() : 0;
            rezult = 37 * rezult + hcValue;
            rezult = 37 * rezult + hcKey;
            return rezult;
        }
    }

    private class HashMap2Iterator implements Iterator<MyEntry<K,V>> {

        private int index = 0;

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public MyEntry<K,V> next() {
            int ind = 0;
            MyEntry<K,V> a;
            for (MyEntry<K,V> curr: table) {
                a = curr;
                if (a != null){
                    do {
                        if (ind == index) {
                            index++;
                            return a;
                        }
                        ind++;
                        a = a.next;
                    } while (a != null);
                }
            }
            return null;
        }
    }

    private Iterator<MyEntry<K,V>> iterator(){
        return new HashMap2Iterator();
    }

    private int thresholdCalc(){
        return (int)(capacity*loadFactor);
    }

    private void resize(int newCapacity){
        if (table.length == MAXIMUM_CAPACITY){
            threshold = Integer.MAX_VALUE;
            return;
        }
        MyEntry<K,V>[] newTable = new MyEntry[newCapacity];
        transfer(newTable);
        table = newTable;
        threshold = (int)(newCapacity * loadFactor);
    }

    private void transfer(MyEntry<K,V>[] nTable){
        Iterator<MyEntry<K,V>> it = new HashMap2Iterator();
        MyEntry<K,V> a, e, p;
        while (it.hasNext()){
            a = it.next();
            if (a != null) {
                K key = a.key;
                V value = a.value;
                if (key == null) {
                    putForNullKey(value, nTable);
                } else {
                    int hash = hash(key.hashCode());
                    int pos = indexFor(hash, nTable.length);
                    e = nTable[pos];
                    if (e != null) {
                        while (e.next != null) e = e.next;
                        p = new MyEntry<>(hash, key, value, null);
                        if (e.hash == hash && (e.key == key || key.equals(e.key))) {
                            e.next = p;
                        } else {
                            nTable[pos] = new MyEntry<>(hash, key, value, nTable[pos]);
                        }
                        continue;
                    }
                    addEntry(hash, key, value, pos, nTable);
                }
            }
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
    public boolean containsKey(Object key) {
        int hash = hash(key.hashCode());
        int pos = indexFor(hash, table.length);
        MyEntry<K,V> a = table[pos];
        return a != null && a.key != null;
    }

    @Override
    public boolean containsValue(Object value) {
        Iterator it = iterator();
        MyEntry<K,V> a;
        while (it.hasNext()){
            a = (MyEntry<K,V>)it.next();
            if (a != null && a.value == value){
                return true;
            }
        }
        return false;
    }

    @Override
    public V get(Object key) {
        int hash = hash(key.hashCode());
        int pos = indexFor(hash, table.length);
        MyEntry<K,V> a = table[pos];
        if (a != null){
            return a.value;
        }
        return null;
    }

    @Override
    public V put(K key, V value) {
        if (size > 0 && size + 1 > threshold){
            resize(size * 2);
        }
        if (key == null){
            putForNullKey(value, null);
        } else {
            int hash = hash(key.hashCode());
            int pos = indexFor(hash, table.length);
            MyEntry<K,V> e = table[pos];
            if (e != null) {
                while (e.next != null) e = e.next;
                MyEntry<K,V> p;
                p = new MyEntry<>(hash, key, value, null);
                if (e.hash == hash && (e.key == key || key.equals(e.key))) {
                    V oldValue = e.value;
                    e.next = p;
                    size++;
                    return oldValue;
                } else {
                    table[pos] = new MyEntry<>(hash, key, value, table[pos]);
                    size++;
                    return null;
                }
            }
            addEntry(hash, key, value, pos, null);
        }
        return null;
    }

    private static int indexFor(int h, int length){
        return h & (length - 1);
    }

    private static int hash(int h){
        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }

    private void putForNullKey(V value, MyEntry<K,V>[] tab) {
        if (tab == null) {
            MyEntry<K,V> e = table[0];
            if (e != null){
                MyEntry<K,V> p = new MyEntry<>(0, null, value, null);
                while (e.next != null) e = e.next;
                e.next = p;
                size++;
            } else {
                addEntry(0, null, value, 0, null);
            }
        } else {
            MyEntry<K, V> e = tab[0];
            if (e != null) {
                MyEntry<K, V> p = new MyEntry<>(0, null, value, null);
                while (e.next != null) e = e.next;
                e.next = p;
            } else {
                addEntry(0, null, value, 0, tab);
            }
        }
    }

    private void addEntry(int hash, K key, V value, int index, MyEntry<K,V>[] tab){
        if (tab == null) {
            MyEntry<K, V> e = table[index];
            table[index] = new MyEntry<>(hash, key, value, e);
            size++;
        } else {
            MyEntry<K, V> e = tab[index];
            tab[index] = new MyEntry<>(hash, key, value, e);
        }
    }

    @Override
    public V remove(Object key) {
//        if (containsKey(key)){
//            MyEntry<K,V> a;
//            V prev;
//            for (MyEntry<K,V> curr: table) {
//                a = curr;
//                if (a != null){
//                    do {
//                        prev = a.value;
//                        if (a.key == key){
//                            return prev;
//                        }
//                        a = a.next;
//                    } while (a != null);
//                }
//            }
//        }
        return null;

    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
    }

    @Override
    public void clear() {
        table = new MyEntry[capacity];
        size = 0;
        threshold = thresholdCalc();
    }

    @Override
    public Set<K> keySet() {
        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return null;
    }
}
