package ua.com.vtkachenko.collections;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class HashMap2<K,V> implements Map<K,V> {

    public static final int DEFAULT_CAPACITY = 16;
    public static final double DEFAULT_LOADFACTOR = 0.75;
    private MyEntry<K,V>[] table;
    private double loadFactor, threshold;
    private int capacity = DEFAULT_CAPACITY;
    private int size;

    public HashMap2(){
        this(DEFAULT_CAPACITY);
    }
    public HashMap2(int capacity){
        this(capacity, DEFAULT_LOADFACTOR);
    }
    public HashMap2(int capacity, double loadFactor){
        if (capacity > Integer.MAX_VALUE/2 + 1) throw new IllegalArgumentException();
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
    }

    private double thresholdCalc(){
        return capacity*loadFactor;
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
                    return oldValue;
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
        addEntry(0, null, value, 0);
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
