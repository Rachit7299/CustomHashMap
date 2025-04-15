package io.rachit7299;

import java.util.LinkedList;

public class CustomHashMap<K,V> {

    private static class Entry<K,V> {
        K key;
        V value;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private static final int INITIAL_CAPACITY = 16;
    private static final float LOAD_FACTOR = 0.75f;

    private LinkedList<Entry<K,V>>[] buckets;
    private int size;

    public CustomHashMap(){
        buckets = new LinkedList[INITIAL_CAPACITY];
        size=0;
    }

    private int getIndex(K key) {
        return Math.abs(key.hashCode()) % buckets.length;
    }

    public V get(K key) {
        int index = getIndex(key);
        if(buckets[index] != null) {
            for(Entry<K,V> entry : buckets[index]) {
                if(entry.key.equals(key)) {
                    return entry.value;
                }
            }
        }
        return null;
    }

    public boolean containsKey(K key) {
        return get(key)!=null;
    }

    public void remove(K key) {
        int index = getIndex(key);
        if(buckets[index] != null) {
            for(Entry<K,V> entry : buckets[index]) {
                if(entry.key.equals(key)) {
                    buckets[index].remove(entry);
                    size--;
                    return;
                }
            }
        }
    }

    public int size() {
        return size;
    }

    public void put(K key,V value){
        int index = getIndex(key);
        if(buckets[index] == null) {
            buckets[index] = new LinkedList<>();
        }
        for(Entry<K,V> entry : buckets[index]) {
            if(entry.key.equals(key)) {
                entry.value = value;
                return;
            }
        }

        buckets[index].add(new Entry<>(key, value));

        size++;

        if((double) size/ buckets.length >= LOAD_FACTOR){
            resize();
        }
    }

    private void resize() {
        LinkedList<Entry<K,V>>[] oldBuckets = buckets;
        buckets = new LinkedList[oldBuckets.length * 2];
        size = 0;

        for (LinkedList<Entry<K,V>> bucket : oldBuckets) {
            if (bucket != null) {
                for (Entry<K,V> entry : bucket) {
                    put(entry.key, entry.value);
                }
            }
        }
    }
}
