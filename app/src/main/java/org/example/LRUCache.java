package org.example;
import java.util.Map;
import java.util.HashMap;
import java.net.URL;

public class LRUCache<K, V>
{
    private final Node<K, V> head = new Node(null, null);
    private final Node<K, V> tail = new Node(null, null);
    private final Map<K, Node<K, V>> map = new HashMap<>();
    private final int capacity;

    public LRUCache(int capacity)
    {
        this.capacity = capacity;
        head.next = tail;
        tail.prev = head;
    }

    public V get(K key)
    {
        if(map.containsKey(key)){
            Node<K, V> node = map.get(key);
            remove(node);
            insert(node);
            return node.value;
        } else {
            return null;
        }
    }

    public void put(K key, V value)
    {
        if(map.containsKey(key)){
            remove(map.get(key));
        }
        if(map.size() == capacity){
            remove(tail.prev);
        }
        insert(new Node<>(key, value));
    }

    private void remove(Node<K, V> node)
    {
        map.remove(node.key);
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void insert(Node<K, V> node)
    {
        map.put(node.key, node);
        node.next = head.next;
        node.next.prev = node;
        head.next = node;
        node.prev = head;
    }

    @Override 
    public String toString() 
    {
        StringBuilder builder = new StringBuilder();
        Node<K, V> curr = head.next;
        while(curr != tail){
            builder.append(curr.key).append(" -> ");
            curr = curr.next;
        }
        return builder.length() > 0 ? builder.toString() : "No items in cache";
    }

    public String filteredURL(String url)
    {
        if(url.startsWith("http://")){
            url = url.substring(7);
        } else if (url.startsWith("https://")){
            url = url.substring(8);
        }

        if(url.startsWith("www.")){
            url = url.substring(4);
        }

        return url;
    }
     
}