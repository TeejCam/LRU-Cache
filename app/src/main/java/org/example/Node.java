package org.example;

public class Node<K, V>
{
    Node<K, V> prev, next;
    K key;
    V value;
    Node(K key, V value)
    {
        this.key = key;
        this.value = value;
    }
}