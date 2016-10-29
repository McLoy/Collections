package ua.com.vtkachenko.collections;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class HashMap2<K,V> implements Map<K,V>{

    public static final int DEFAULT_CAPACITY = 16;
    public static final double DEFAULT_LOADFACTOR = 0.75;
    private Entry[] table;
    private double loadFactor = 0.75, threshold;
    private int size, capacity = DEFAULT_CAPACITY;

    public HashMap2(){
        this(DEFAULT_CAPACITY);
    }
    public HashMap2(int capacity){
        this(capacity, DEFAULT_LOADFACTOR);
    }
    public HashMap2(int capacity, double loadFactor){
        if (capacity > Integer.MAX_VALUE/2 + 1) throw new IllegalArgumentException();
        this.loadFactor = loadFactor;
        this.table = new Entry[capacity];
        this.size = 0;
        this.threshold = thresholdCalc();
    }

    private double thresholdCalc(){
        return capacity*loadFactor;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
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
        return null;
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
