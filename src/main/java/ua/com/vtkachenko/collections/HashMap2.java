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
        if (capacity != DEFAULT_CAPACITY){
            resize(capacity);
        }
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
            for (MyEntry<K,V> curr: table) {
                if (ind == index){
                    index++;
                    return curr;
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

    void resize(int newCapacity){
        if (table.length == MAXIMUM_CAPACITY){
            threshold = Integer.MAX_VALUE;
            return;
        }
        MyEntry[] newTable = new MyEntry[newCapacity];
        transfer(newTable);
        table = newTable;
        threshold = (int)(newCapacity * loadFactor);
    }

    void transfer(MyEntry<K,V>[] tabl){
//        Iterator<MyEntry<K,V>> it = iterator();
//        MyEntry<K,V>[] newTable = new MyEntry[capacity];
//        while (it.hasNext()){
//            MyEntry<K,V> elem = it.next();
//            K key = elem.key;
//            V value = elem.value;
//
//            if (key == null){
//                putForNullKey(value);
//            } else {
//                int kh = key.hashCode();
//                int hash = hash(kh);
//                int tableLength = table.length;
//                int pos = indexFor(hash, tableLength);
//                MyEntry<K,V> e = table[pos];
//                if (e != null) {
//                    while (e.next != null) e = e.next;
//                    MyEntry<K,V> p;
//                    p = new MyEntry<>(hash, key, value, null);
//                    if (e.hash == hash && (e.key == key || key.equals(e.key))) {
//                        V oldValue = e.value;
//                        e.next = p;
////                        size++;
////                        return oldValue;
//                    } else {
//                        table[pos] = new MyEntry<>(hash, key, value, table[pos]);
////                        size++;
////                        return null;
//                    }
//                }
//                //addEntry(hash, key, value, pos);
//            }
//        }

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
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public V get(Object key) {
        return null;
    }

    @Override
    public V put(K key, V value) {
        if (size + 1 > threshold){
            resize(size * 2);
            //transfer(table);
        }
        if (key == null){
            putForNullKey(value);
        } else {
            int kh = key.hashCode();
            int hash = hash(kh);
            int tableLength = table.length;
            int pos = indexFor(hash, tableLength);
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
            addEntry(hash, key, value, pos);
        }
        return null;
    }

    void addEntry(int hash, K key, V value, int index){
        MyEntry<K,V> e = table[index];
        table[index] = new MyEntry<>(hash, key, value, e);
        size++;
    }

    static int indexFor(int h, int length){
        return h & (length - 1);
    }

    static int hash(int h){
        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }

    private void putForNullKey(V value) {
        MyEntry<K,V> e = table[0];
        if (e != null){
            MyEntry<K,V> p = new MyEntry<>(0, null, value, null);
            while (e.next != null) e = e.next;
            e.next = p;
            size++;
        } else {
            addEntry(0, null, value, 0);
        }
    }

    @Override
    public V remove(Object key) {
        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {

    }

    @Override
    public void clear() {

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
