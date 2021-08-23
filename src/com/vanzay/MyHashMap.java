package com.vanzay;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class MyHashMap<K, V> {
    private int size = 0;
    private Node<K, V>[] buckets = new Node[16];

    static class Node<K, V> {
        final K key;
        V value;
        Node<K, V> next;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node<?, ?> node = (Node<?, ?>) o;
            return Objects.equals(key, node.key);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key);
        }
    }

    public int size() {
        return size;
    }

    public V get(K key) {
        int index = getIndex(key);
        Node<K, V> element = buckets[index];
        while (element != null) {
            if (element.key.equals(key)) {
                return element.value;
            }
            element = element.next;
        }
        return null;
    }

    public Set<K> keySet() {
        Set<K> keys = new HashSet<>();
        for (Node<K, V> bucket : buckets) {
            while (bucket != null) {
                keys.add(bucket.getKey());
                bucket = bucket.next;
            }
        }
        return keys;
    }

    public void put(K key, V value) {
        int index = getIndex(key);
        Node<K, V> element = buckets[index];

        if (element != null) {
            if (element.key.equals(key)) {
                element.value = value;
            } else {
                while (element.next != null) {
                    element = element.next;
                }
                element.next = new Node<>(key, value);
                size++;
            }
        } else {
            Node<K, V> entryInNewBucket = new Node<>(key, value);
            buckets[index] = entryInNewBucket;
            size++;
        }
    }

    public boolean contains(K key) {
        int index = getIndex(key);
        while (buckets[index] != null) {
            if (buckets[index].key.equals(key)) {
                return true;
            }
            buckets[index] = buckets[index].next;
        }
        return false;
    }

    private int getIndex(K key) {
        return Math.abs(key.hashCode() % buckets.length);
    }
}
