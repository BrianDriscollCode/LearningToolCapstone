package com.example.LifeLongLearningTool.Utils;

//https://stackoverflow.com/questions/2973041/a-keyvaluepair-in-java

// - used this data structure to help solve a problem dealing with transferring hashmaps to the frontend

public class KeyValuePair<K, V> {
    private K key;
    private V value;

    public KeyValuePair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }
}
