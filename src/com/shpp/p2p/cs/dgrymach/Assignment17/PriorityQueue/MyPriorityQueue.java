package com.shpp.p2p.cs.dgrymach.Assignment17.PriorityQueue;

import java.util.Iterator;

/**
 * PriorityQueue this a container class consisting of an LinkedList storing objects(type generic).
 * in First cell [0] we store smaller value object. In moment when we put element we check index where we wont put.
 */
public class MyPriorityQueue<V extends Comparable<V>> implements Iterable<V> {
    /*The buffer into which the elements of the Stack are stored.*/
    private MyLinkedListExt<V> pQueue;

    /**
     * Creates an empty   Priority Queue.
     */
    public MyPriorityQueue() {
        pQueue = new MyLinkedListExt<>();
    }

    /*------scope-----*/

    /**
     * Pushes value in down of this Queue. This has exactly  the same effect as: add(element)
     *
     * @param newElement the newElement to be pushed onto this Queue.
     * @return - the true if element was add.
     */
    public boolean add(V newElement) {
        if (size() == 0) pQueue.addFirst(newElement);
        else pQueue.putInCurrentPlace(newElement);
        return true;
    }


    /**
     * Retrieves, but does not remove, the head of this queue .
     *
     * @return the object at the top of this queue  (the smaller item  on this queue).
     */
    public V peek() {
        if (size() == 0) return null;
        return (V) pQueue.get(0);
    }

    /**
     * Return and removes the head of this queue, or returns null if this queue is empty.
     *
     * @return The object at the last of this Queue (the smaller item  on this queue).
     */
    public V poll() {
        if (size() == 0) return null;
        return (V) pQueue.removeFirst();
    }

    /**
     * Returns the number of elements in this list.
     *
     * @return the number of elements in this list
     */
    public int size() {
        return pQueue.size();
    }


    /**
     * Take object iterator from super class
     *
     * @return - iterator ArrayList.
     */
    @Override
    public Iterator<V> iterator() {
        return pQueue.iterator();
    }
}










