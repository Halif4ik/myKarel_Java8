package com.shpp.p2p.cs.dgrymach.Assignment16.Queue;

import com.shpp.p2p.cs.dgrymach.Assignment16.LinkedList.MyLinkedList;

import java.util.EmptyStackException;
import java.util.Iterator;

/**
 * The  class represents a first-in-first-out
 * (FIFO) stack of objects. A wrapper class for storing data that is based on MyLinkedList
 */
public class MyQueue<E > implements Iterable<E>{
    /*The buffer into which the elements of the Stack are stored.*/
   private MyLinkedList  Queue;

    /**
     * Creates an empty Queue.
     */
    public MyQueue() {
        Queue = new MyLinkedList<>();
    }

    /**
     * Pushes an item onto the top of this Queue. This has exactly
     * the same effect as:
     * addLastElement(item)
     *
     * @param item the item to be pushed onto this stack.
     * @return the {@code true} as specified by .
     */
    public boolean add(E item) {
        Queue.addLast(item);
        return true;
    }

    /**
     *Retrieves and removes the head of this queue, or returns null if this queue is empty.
     *
     * @return The object at the last of this Queue (the first item
     * of the  object).

     */
    public E poll() {
        int len = size();
        if (len == 0)  throw new EmptyStackException();
        return (E)Queue.remove(0);
    }
    /**
     * Returns the number of components in this Queue.
     *
     * @return  the number of components in this Queue
     */
    public int size() {
        return Queue.size();
    }

    /**
     * Retrieves, but does not remove, the head of this queue.
     *
     * @return  the object at the top of this queue (the last item
     *          of the object).

     */
    public E element() {
        int len = size();
        if (len == 0)  throw new EmptyStackException();
        return (E)Queue.get(0);
    }

    /**
     * Tests if this stack is empty.
     *
     * @return  {@code true} if and only if this Queue contains
     *          no items; {@code false} otherwise.
     */
    public boolean empty() {
        return Queue.size() == 0;
    }

    /**
     * Take object iterator from super class
     * @return - iterator LinkedList
     */
    @Override
    public Iterator<E> iterator() {
        return Queue.iterator();
    }

    public boolean isEmpty() {
        return Queue.isEmpty();
    }
}
