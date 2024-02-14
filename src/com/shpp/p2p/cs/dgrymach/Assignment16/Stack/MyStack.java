package com.shpp.p2p.cs.dgrymach.Assignment16.Stack;

import com.shpp.p2p.cs.dgrymach.Assignment16.ArrayList.MyArrayList;

import java.util.EmptyStackException;
import java.util.Iterator;

/**
 * The class represents a last-in-first-out
 * (LIFO) stack of objects. A wrapper class for storing data that is based on MyArrayList
 */
public class MyStack<E> implements Iterable<E> {
    /*The buffer into which the elements of the Stack are stored.*/
    private MyArrayList<E> array;

    /**
     * Creates an empty Stack.
     */
    public MyStack() {
        array = new MyArrayList<>();
    }

    /**
     * Pushes an item onto the top of this stack. This has exactly
     * the same effect as:
     * addElement(item)
     *
     * @param item the item to be pushed onto this stack.
     * @return the {@code item} argument.
     */
    public boolean push(E item) {
        array.add(item);
        return true;
    }

    /**
     * Removes the object at the top of this stack and returns that
     * object as the value of this function.
     *
     * @return The object at the top of this stack (the last item
     * of the  object).
     */
    public E pop() {
        int len = size();
        if (len == 0) throw new EmptyStackException();
        return array.remove(len - 1);
    }

    /**
     * Returns the number of components in this stack.
     *
     * @return the number of components in this vector
     */
    public int size() {
        return array.size();
    }

    /**
     * Looks at the object at the top of this stack without removing it
     * from the stack.
     *
     * @return the object at the top of this stack (the last item
     * of the object).
     */
    public E peek() {
        int len = size();
        if (len == 0) throw new EmptyStackException();
        return array.get(len - 1);
    }

    /**
     * Tests if this stack is empty.
     *
     * @return {@code true} if and only if this stack contains
     * no items; {@code false} otherwise.
     */
    public boolean empty() {
        return array.size() == 0;
    }

    @Override
    public Iterator<E> iterator() {
        return array.iterator();
    }


}
