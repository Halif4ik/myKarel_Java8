package com.shpp.p2p.cs.dgrymach.Assignment17.PriorityQueue;

import com.shpp.p2p.cs.dgrymach.Assignment16.ArrayList.MyArrayList;

import java.util.Iterator;

/**
 * PriorityQueue this a container class consisting of an ArrayList storing objects(type generic).
 * in First cell [0] we store smaller value object.
 */
public class MyPriorityQueue<V extends Comparable<V>> implements Iterable<V> {
    /*The buffer into which the elements of the Stack are stored.*/
    private MyArrayList<V> pQueue;

    /**
     * Creates an empty   Priority Queue {.
     */
    public MyPriorityQueue() {
        pQueue = new MyArrayList<>();
    }

    /**
     * counter number objects in  pQueue.
     */
    private int size;
    /*------scope-----*/

    /**
     * Pushes value in down of this Queue. This has exactly  the same effect as: add(element)
     *
     * @param newElement the newElement to be pushed onto this Queue.
     * @return - the true if element was add.
     */
    public boolean add(V newElement) {
        pQueue.add(newElement); // add in the and array and resize if need
        shiftUpSmaller(size, newElement);
        size++;
        return true;
    }

    /**
     * Retrieves, but does not remove, the head of this queue .
     *
     * @return the object at the top of this queue  (the smaller item  on this queue).
     */
    public V peek() {
        if (size == 0) return null;
        return pQueue.get(0);
    }
    /**
     * Retrieves, but does not remove, the head of this queue .
     *
     * @return the object at the top of this queue  (the smaller item  on this queue).
     */
    public V get(int i) {
        if (size == 0) return null;
        return pQueue.get(i);
    }

    /**
     * Until we move the minimum value to the beginning of the array [0] or no more than the parent .
     *
     * @param currentIndex- index where we put last element
     * @param newElement-   last added element.
     */
    private void shiftUpSmaller(int currentIndex, V newElement) {
        int parent = (currentIndex - 1) / 2;

        while (currentIndex > 0 && pQueue.get(parent).compareTo(newElement) > 0) {
            /*move down parent */
            pQueue.set(currentIndex, pQueue.get(parent));
            currentIndex = parent;
            parent = (parent - 1) / 2;
        }
        pQueue.set(currentIndex, newElement);
    }

    /**
     * @param currentIndex- index last element in array it is one from larger elements.
     */
    private void shiftDownMax(int currentIndex) {
        int smallerChild;
        V tempTop = pQueue.get(0);

        while (currentIndex < size / 2) {
            int leftChild = 2 * currentIndex + 1;
            int rightChild = leftChild + 1;

            /*if right Child are present ||  "C".compare"B" =1   "B".compare"C" =-1  */
            if (rightChild < size || pQueue.get(leftChild).compareTo(pQueue.get(rightChild)) > 0) {
                smallerChild = rightChild;
            } else smallerChild = leftChild;

            if (tempTop.compareTo(pQueue.get(smallerChild)) < 0) break;

            pQueue.set(currentIndex, pQueue.get(smallerChild));
            currentIndex = smallerChild;
        }/*after found necessary index put value what was in memory*/
        pQueue.set(currentIndex, tempTop);
    }

    /**
     * Return and removes the head of this queue, or returns null if this queue is empty.
     *
     * @return The object at the last of this Queue (the smaller item  on this queue).
     */
    public V poll() {
        if (size == 0) return null;
        V minimal = pQueue.get(0);

        /*replace max value element on top*/
        pQueue.set(0, pQueue.get(--size));
        shiftDownMax(0);
        return minimal;
    }

    /**
     * Returns the number of elements in this list.
     *
     * @return the number of elements in this list
     */
    public int size() {
        return size;
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










