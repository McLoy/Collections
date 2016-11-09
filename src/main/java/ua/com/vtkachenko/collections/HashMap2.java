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

        MyEntry(K key, V value){
            this(Objects.hashCode(key), key, value, null);
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
        MyEntry<K,V> currEntry;
        Iterator it = iterator();
        while (it.hasNext()){
            currEntry = (MyEntry<K, V>) it.next();
            K key = currEntry.key;
            V value = currEntry.value;
            int hash = getHash(key);
            int bucketIndex = getIndex(hash, nTable);
            MyEntry<K, V> bucket = nTable[bucketIndex];

            if (bucket == null) {
                setInBucket(bucketIndex, new MyEntry<>(key, value), nTable);
            } else {
                MyEntry<K, V> curr = bucket;
                do {
                    if (compareKeys(curr, key)) {
                        curr.setValue(value);
                    }
                    if (curr.next == null) {
                        curr.next = new MyEntry<>(key, value);
                        setInBucket(bucketIndex, bucket, nTable);
                        break;
                    }
                    curr = curr.next;
                } while (curr != null);
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
        MyEntry<K,V> curr;
        while (it.hasNext()){
            curr = (MyEntry<K,V>)it.next();
            if (compareKeys(curr, (K) key)){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        Iterator it = iterator();
        MyEntry<K,V> curr;
        while (it.hasNext()){
            curr = (MyEntry<K,V>)it.next();
            if (curr != null && (curr.value == value || value.equals(curr.value))){
                return true;
            }
        }
        return false;
    }

    @Override
    public V get(Object key) {
        Iterator it = iterator();
        MyEntry<K,V> curr;
        V ret = null;
        while (it.hasNext()){
            curr = (MyEntry<K, V>) it.next();
            if (compareKeys(curr, (K) key)){
                ret = curr.value;
                break;
            }
        }
        return ret;
    }

    @Override
    public V put(K key, V value) {
        if (size > 0 && size + 1 > threshold) resize(size * 2);
        int hash = getHash(key);
        int bucketIndex = getIndex(hash);
        MyEntry<K, V> bucket = table[bucketIndex];

        if (bucket == null) {
            setInBucket(bucketIndex, new MyEntry<>(key, value));
        } else {
            MyEntry<K, V> curr = bucket;
            do {
                if (compareKeys(curr, key)) {
                    V oldValue = curr.value;
                    curr.setValue(value);
                    return oldValue;
                }
                if (curr.next == null) {
                    curr.next = new MyEntry<>(key, value);
                    setInBucket(bucketIndex, bucket);
                    return null;
                }
                curr = curr.next;
            } while (curr != null);

        }
        return null;
    }

    private int getIndex(int hash, MyEntry<K,V>[] tab) {
        return hash % tab.length;
//        hash ^= (hash >>> 20) ^ (hash >>> 12); //hash by habrhabr
//        hash = hash ^ (hash >>> 7) ^ (hash >>> 4); //hash by habrhabr
//        return hash & (tab.length - 1); //hash by habrhabr
    }

    private int getIndex(int hash) {
        return getIndex(hash, table);
    }

    private int getHash(K key) {
        return Objects.hashCode(key);
        //return key == null ?  0 : key.hashCode(); //key by habrhabr
    }

    private void setInBucket(int bucketIndex, MyEntry<K, V> bucket, MyEntry<K,V>[] tab) {
        tab[bucketIndex] = bucket;
    }

    private void setInBucket(int bucketIndex, MyEntry<K, V> bucket) {
        setInBucket(bucketIndex, bucket, table);
        size++;
    }

    @Override
    public String toString(){
        Iterator<MyEntry<K,V>> i = iterator();
        if (! i.hasNext())
            return "{}";

        StringBuilder sb = new StringBuilder();
        sb.append('{');
        for (;;) {
            MyEntry<K,V> e = i.next();
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

    @Override
    public V remove(Object key) {
        V prev = null;
        int hash = getHash((K)key);
        int bucketIndex = getIndex(hash, table);
        boolean flag = false;
        MyEntry<K, V> bucket = table[bucketIndex];
        MyEntry<K, V> newBucket = null, newEntry = null;
        if (bucket != null) {
            MyEntry<K, V> curr = bucket;
            do {
                if (compareKeys(curr, (K)key)) {
                    curr = curr.next;
                    flag = true;
                    size--;
                    continue;
                } else {
                    if (!flag) prev = curr.value;
                    if (newEntry != null){
                        newEntry.next = new MyEntry<>(curr.key, curr.value);
                        newBucket = newEntry;
                        newEntry = newEntry.next;
                    } else {
                        newEntry = new MyEntry<>(curr.key, curr.value);
                    }
                }
                curr = curr.next;
            } while (curr != null);
            table[bucketIndex] = newBucket;
        }
        return prev;
    }

    private boolean compareKeys(MyEntry<K,V> e, K key){
        return e.hash == getHash(key) && (e.key == key || e.key.equals(key));
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
