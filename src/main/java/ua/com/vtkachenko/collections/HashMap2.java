package ua.com.vtkachenko.collections;

import java.util.*;

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

    private static class MyEntry<K,V> implements Map.Entry<K,V>{
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
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            V old = this.value;
            this.value = value;
            return old;
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
                if (curr == null) continue;
                a = curr;
                    do {
                        if (ind == index) {
                            index++;
                            return a;
                        }
                        ind++;
                        a = a.next;
                    } while (a != null);

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
        while (it.hasNext()) {
            a = it.next();
            K key = a.key;
            V value = a.value;
            if (key == null) {
                nTable[0] = new MyEntry<>(0, null, value, null);
            } else {
                int hash = hash(key.hashCode());
                int pos = hash % nTable.length;
                e = nTable[pos];
                if (e != null) {
                    p = new MyEntry<>(hash, key, value, null);
                    if (compare(e, hash, key)) {
                        nTable[pos] = p;
                    } else {
                        while (e.next != null) {
                            e = e.next;
                            if (compare(e, hash, key)) {
                                e = p;
                                break;
                            }
                        }
                        e.next = p;
                    }
                    continue;
                }
                nTable[pos] = new MyEntry<>(hash, key, value, e);
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
        Iterator it = iterator();
        MyEntry<K,V> a;
        while (it.hasNext()){
            a = (MyEntry<K,V>)it.next();
            if (a != null && (a.key == key || key.equals(a.key))){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        Iterator it = iterator();
        MyEntry<K,V> a;
        while (it.hasNext()){
            a = (MyEntry<K,V>)it.next();
            if (a != null && (a.value == value || value.equals(a.value))){
                return true;
            }
        }
        return false;
    }

    @Override
    public V get(Object key) {
        Iterator it = iterator();
        MyEntry<K,V> a;
        V ret = null;
        while (it.hasNext()){
            a = (MyEntry<K, V>) it.next();
            if (a != null && (a.key == key || key.equals(a.key))){
                ret = a.value;
            }
        }
        return ret;
    }

    @Override
    public V put(K key, V value) {
        if (size > 0 && size + 1 > threshold){
            resize(size * 2);
        }
        if (key == null){
            putForNullKey(value);
        } else {
            int hash = hash(key.hashCode());
            int pos = hash % table.length;
            MyEntry<K,V> e = table[pos];
            if (e != null) {
                MyEntry<K,V> p = new MyEntry<>(hash, key, value, null);
                if (compare(e, hash, key)) {
                    V oldValue = e.value;
                    e.setValue(p.value);
                    return oldValue;
                } else {
                    while (e.next != null) {
                        e = e.next;
                        if (compare(e, hash, key)) {
                            e = p;
                            break;
                        }
                    }
                    e.next = p;
                    size++;
                    return null;
                }
            }
            addEntry(hash, key, value, pos);
        }
        return null;
    }

    @Override
    public String toString(){
        Iterator<Entry<K,V>> i = entrySet().iterator();
        if (! i.hasNext())
            return "{}";

        StringBuilder sb = new StringBuilder();
        sb.append('{');
        for (;;) {
            Entry<K,V> e = i.next();
            K key = e.getKey();
            V value = e.getValue();
            sb.append(key   == this ? "(this Map)" : key);
            sb.append('=');
            sb.append(value == this ? "(this Map)" : value);
            if (! i.hasNext())
                return sb.append('}').toString();
            sb.append(',').append(' ');
        }
    }

    private int hash(int h) {
        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }

    private void putForNullKey(V value) {
        MyEntry<K,V> e = table[0];
        if (e != null) {
            e.value = value;
        } else {
            addEntry(0, null, value, 0);
        }
    }

    private void addEntry(int hash, K key, V value, int index) {
        MyEntry<K, V> e = table[index];
        table[index] = new MyEntry<>(hash, key, value, e);
        size++;
    }

    @Override
    public V remove(Object keyD) {
        V prev = null;
        V prevT = null;
        MyEntry<K,V>[] nTable = new MyEntry[capacity];
        Iterator<MyEntry<K,V>> it = new HashMap2Iterator();
        MyEntry<K,V> a, e, p;
        while (it.hasNext()) {
            a = it.next();
            K key = a.key;
            if (keyD != key || !key.equals(keyD)) {
                V value = a.value;
                prevT = value;
                if (key == null) {
                    nTable[0] = new MyEntry<>(0, null, value, null);
                } else {
                    int hash = hash(key.hashCode());
                    int pos = hash % nTable.length;
                    e = nTable[pos];
                    if (e != null) {
                        p = new MyEntry<>(hash, key, value, null);
                        if (compare(e, hash, key)) {
                            nTable[pos] = p;
                        } else {
                            while (e.next != null) {
                                e = e.next;
                                if (compare(e, hash, key)) {
                                    e = p;
                                    break;
                                }
                            }
                            e.next = p;
                        }
                        continue;
                    }
                    nTable[pos] = new MyEntry<>(hash, key, value, e);
                }
            } else {
                prev = prevT;
            }
        }
        table = nTable;
        size--;
        return prev;
    }

    private boolean compare(MyEntry<K,V> e, int hash, K key){
        return e.hash == hash && (e.key == key || key.equals(e.key));
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
        Set<K> set = new HashSet<>();
        Iterator it = new HashMap2Iterator();
        MyEntry<K,V> tmp;
        while (it.hasNext()){
            tmp = (MyEntry<K, V>) it.next();
            set.add(tmp.key);
        }
        return set;
    }

    @Override
    public Collection<V> values() {
        ArrayList<V> arr = new ArrayList<>();
        Iterator it = new HashMap2Iterator();
        MyEntry<K,V> tmp;
        while (it.hasNext()){
            tmp = (MyEntry<K, V>) it.next();
            arr.add(tmp.value);
        }
        return arr;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        Set<Map.Entry<K,V>> set = new HashSet<>();
        Iterator it = new HashMap2Iterator();
        MyEntry<K,V> tmp;
        while (it.hasNext()){
            tmp = (MyEntry<K, V>) it.next();
            set.add(tmp);
        }
        return set;
    }
}
