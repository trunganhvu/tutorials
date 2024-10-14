/**
 * Copyright 2024
 * Name: Entity
 */
package com.anhvt;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 10/14/2024
 */
public class Entity<K, V> {
    private K key;
    private V value;

    public Entity(K key, V value) {
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
