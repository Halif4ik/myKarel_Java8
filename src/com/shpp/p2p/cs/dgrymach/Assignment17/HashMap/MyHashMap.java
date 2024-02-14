package com.shpp.p2p.cs.dgrymach.Assignment17.HashMap;

import com.shpp.p2p.cs.dgrymach.Assignment16.LinkedList.MyLinkedList;

import java.util.Iterator;

/**
 * MyHashMap this class a container class consisting of an array of LinkedLists storing Entry.
 * Entry - an object that stores associated information (key+ value). In an Array of initial size 16,
 * which initially store an empty linked list.
 */
public class MyHashMap<K, V> implements Iterable<MyHashMap.Entry> {
    /**
     * Default initial capacity.
     */
    private static final int DEFAULT_CAPACITY = 16;
    /**
     * The array buffer into which the elements of the ArrayList are stored.
     */
    private MyLinkedList<Entry>[] hashTableArray;

    /**
     * counter number entry(instance key+value) in hashTableArray
     */
    private int size;
    /*------scope-----*/

    /**
     * Constructs an empty table(array)  an initial capacity of 0001 000 it is counts  MyLinkedLists in new hashtable.
     * And contain it empty sets.
     */
    public MyHashMap() {
        hashTableArray = new MyLinkedList[DEFAULT_CAPACITY];
        for (int i = 0; i < hashTableArray.length; i++) {
            hashTableArray[i] = new MyLinkedList<>();
        }
    }

    /**
     * Constructs an empty table(array) whith current capacity of 0001 000 it is counts MyLinkedLists in new hashtable.
     * And contain it empty sets.
     */
    public MyHashMap(int capacity) {
        hashTableArray = new MyLinkedList[capacity];
        for (int i = 0; i < hashTableArray.length; i++) {
            hashTableArray[i] = new MyLinkedList<>();
        }
    }

    /**
     * Calculate index for hashTableArray.
     *
     * @param key -key in which index in hashTableArray are stored entry set.
     * @return int- value index hash table from 0 -to  hashTableArray.length.
     */
    private int hashIndex(K key) {
        int hash=0;
        if(key == null)return hash;
        else {
             hash = key.hashCode();
            if(hash<0) hash^=hash;
        }
        return  hash % hashTableArray.length;
    }

    /**
     * Create Entry with specified value + key , added in LinkedList and after add in HashMap, when current
     * cell empty in HashMap or we have collision.
     * If the HashMap previously contained a entry for the key, the old  value is rewriting.
     *
     * @param key   key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     * @return the old value associated with key, or null if not found pair.
     */
    public V put(K key, V value) {
        if (size > hashTableArray.length * 0.85) grow();

        /*take EntryList what store in currant index hashmap and add in this pair key value*/
        MyLinkedList<Entry> entrySet = hashTableArray[hashIndex(key)];

        /*if cell is empty we simply put*/
        if (entrySet.size() == 0) {
            entrySet.addLast(new Entry<>(key, value));
            size++;
            return null;
        }
        /*went around and if we already have a pair with such key Replace */
        K curentKey;
        for (Entry<K, V> currentEntry : entrySet) {
            if ((curentKey = currentEntry.key) == key || (curentKey != null && curentKey.equals(key))) {
                V oldValue = currentEntry.value;
                currentEntry.value = value;
                return oldValue;
            }
        }
        /*it is Collision we  have a pair , but not equals key simply aad new Entry in the and list */
        entrySet.addLast(new Entry<>(key, value));
        size++;
        return null;

    }

    /**
     * Returns the value to which the specified key is mapped,or null if this map no contains current entry.
     *
     * @param key- key current Entry in map
     * @return - value field old Entry with current key
     */
    public V get(K key) {
        K ck;
        int index = hashIndex(key);
        /*take EntryList what store in currant index hashmap*/
        if (index < 0) return null;
        MyLinkedList<Entry> entrySet = hashTableArray[index];

        if (entrySet.size() == 0) return null;
        else for (Entry<K, V> currentEntry : entrySet) {
            if ((ck = currentEntry.key) == key || (ck != null && ck.equals(key))) return currentEntry.value;
        }
        return null;
    }

    /**
     * check if array(not capacity ArrayList) is full we need grow
     */
    private void grow() {
        MyLinkedList[] oldHashTable = hashTableArray;
        hashTableArray = new MyLinkedList[hashTableArray.length << 1];
        size = 0;
        /*contain new hashmap empty EntrySet*/
        for (int i = 0; i < hashTableArray.length; i++) {
            hashTableArray[i] = new MyLinkedList<>();
        }
        /*contain new hashmap*/
        for (MyLinkedList<Entry> EntrySet : oldHashTable)
            for (Entry<K, V> temp : EntrySet) put(temp.key, temp.value);
    }

    /**
     * Returns true if this map contains  specified key.
     *
     * @param key -The key what need tested
     * @return - if this map contains specified key.
     */

    public boolean containsKey(K key) {
        K cK;
        int index = hashIndex(key);
        if (index < 0) return false;

        MyLinkedList<Entry> EntrySet = hashTableArray[index];
        if (EntrySet.size() == 0) return false;

        for (Entry<K, V> currentEntry : EntrySet)
            if ((cK = currentEntry.key) == key || (cK != null && cK.equals(key))) return true;
        return false;
    }

    /**
     * First we check input index and get EntrySet from hashtable. Depending on the size of the LinkedList,
     * we either delete the first element immediately or create an iterator and when found right pair
     * remember its field(value) and remove and return.
     *
     * @param key - key current ENTRY, what we need delete.
     * @return - deleted entry field(value) or null if current entry not found
     */
    public V remove(K key) {
        K ck;
        V oldValue;
        int index = hashIndex(key);
        /*take EntryList what store in currant index hashMap*/
        if (index < 0) return null;

        MyLinkedList<Entry> EntrySet = hashTableArray[index];

        if (EntrySet.size() == 0) return null;
            /*  went around, and if we already have a pair with such key Replace */
        else {
            Iterator<Entry> it = EntrySet.iterator();
            while (it.hasNext()) {
                Entry<K, V> currentEntry = it.next();
                if ((ck = currentEntry.key) == key || (ck != null && ck.equals(key))) {
                    oldValue = currentEntry.value;
                    it.remove();
                    size--;
                    return oldValue;
                }
            }
        }
        /*not found*/
        return null;
    }

    /**
     * Returns true if this map no  contains key-value pairs.
     *
     * @return - true if size ==0;
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the number of Entries (key+value )in this map.
     *
     * @return - count Entries
     */
    public int size() {
        return size;
    }

    /**
     * Returns true if this map contains specified value.
     *
     * @param value- what found value in map.
     * @return - first found value in map.
     */
    public boolean containsValue(V value) {
        V v;
        if (size == 0) return false;

        for (MyLinkedList<Entry> EntrySet : hashTableArray) {
            if (EntrySet.size() == 0) return false;
            for (Entry<K, V> currentEntry : EntrySet) {
                v = currentEntry.value;
                if (v == value || (value != null && v != null && v.equals(value))) return true;
            }
        }
        return false;
    }

    /**
     * Override interface Iterable , create main(anonymous) instance whits indexes and increment its when
     * take next element, also we store iterator our LinkedList what is current in current cel hashMap
     */
    @Override
    public Iterator<Entry> iterator() {
        return new Iterator<Entry>() {
            private Iterator<Entry> IteratorEntrySet = null;
            int indexEntry;
            int indexHashtable = -1;

            @Override
            public boolean hasNext() {
                /*  if we first one  ||  yet hashMap store elements  but items ended in current EntrySet    */
                while (IteratorEntrySet == null || (indexEntry != size && !IteratorEntrySet.hasNext())) {
                    MyLinkedList<Entry> EntrySet = hashTableArray[++indexHashtable];
                    if (EntrySet.size() == 0) continue;
                    IteratorEntrySet = EntrySet.iterator();
                    break;
                }
                return indexEntry != size;
            }

            @Override
            public Entry next() {
                indexEntry++;
                return IteratorEntrySet.next();
            }

        }
                ;
    }


    /**
     * instants this class store two filed  key mapping value
     */
    public class Entry<K, V> {
        /*key with which the specified value is to be associated*/
        public K key;
        /*data what current link contains */
        public V value;

        /**
         * In constructor create new object with two filed Key and value type (generics).
         */
        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        /**
         * method for correct println in foreach
         */
        @Override
        public String toString() {
            return key + "=" + value;
        }
    }
}








