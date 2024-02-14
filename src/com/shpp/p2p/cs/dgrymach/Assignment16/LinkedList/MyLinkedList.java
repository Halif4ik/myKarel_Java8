package com.shpp.p2p.cs.dgrymach.Assignment16.LinkedList;

import java.util.Iterator;

/**
 * Doubly-linked list . implementation metods addLast, addFirst,add(index ,element),add,get, size,remove,.
 */
public class MyLinkedList<E> implements Iterable<E> {
    /*scope where contains link to first item in Linked list  */
    MyLink firstInList;
    /* [0]..  1..2  scope where contains link to last item in Linked list  */
    MyLink lastInList;
    /*counter number Links in list*/
    private int size;
    /*------scope-----*/

    /**
     * create empty LinkedList.
     */
    public MyLinkedList() {
        size = 0;
    }

    /**
     * Inserts the specified element at the last position in Linkedlist.
     * add items in right-to-left order. firstInList - it is last added element.
     * and so it turns out we add a new element to the scope class - first in list.
     * <p>
     * * @param element element to be inserted
     */
    public void addLast(E element) {
        MyLink<E> newLink = new MyLink<>(element);
        if (size == 0) lastInList = newLink;
        else {
            firstInList.previous = newLink;
            newLink.next = firstInList;
        }
        firstInList = newLink;
        size++;
    }

    protected void incrementSize() {
        size++;
    }

    protected void decrementSize() {
        size--;
    }

    /**
     * Inserts the specified element at the beginning of this list..
     * add items in right-to-left order lastInList - it is first added element.
     * <p>
     * * @param element element to be inserted
     */
    public void addFirst(E element) {
        MyLink<E> newLink = new MyLink<>(element);

        if (size == 0) firstInList = newLink;
        else {
            lastInList.next = newLink;
            newLink.previous = lastInList;
        }
        lastInList = newLink;
        size++;
    }

    /**
     * Inserts the specified element at the specified position in this list.
     * and shift all current element to right.
     */
    public void add(int index, E element) {
        if (index == 0) {
            addFirst(element);
            return;
        }
        if (index == size) {
            addLast(element);
            return;
        }
        MyLink<E> newLink = new MyLink<>(element);
        MyLink currentLinkAtSpecifiedIndex = findIndexLink(index);
        /*create links to next and previous Object links*/
        newLink.next = currentLinkAtSpecifiedIndex.next;
        currentLinkAtSpecifiedIndex.next.previous = newLink;
        newLink.previous = currentLinkAtSpecifiedIndex;
        currentLinkAtSpecifiedIndex.next = newLink;
        /*after added link */
        size++;
    }

    /**
     * Until we move the minimum value to the beginning of the array [0] or no more than the parent .
     *
     * @param newElement- last added element.
     * @return - index where we put newElement
     */

    public boolean putInCurrentPlace(E newElement) {
        MyLink<E> newLink = new MyLink<>(newElement);

        return true;
    }

    /**
     * Check input index, and while not fuond it create get prevision object, we begin  from start [0]
     * it is lats element what added inLinked list
     *
     * @param index - counter iteration.
     */
    private MyLink findIndexLink(int index) {//if index==0
        if (index < 0 || index > size) throw new IndexOutOfBoundsException();
        MyLink currentLink;

        if (index == 0) return lastInList;
        if (index == size - 1) return firstInList;

        if (size / 2 > index) currentLink = findFromZero(index);
        else currentLink = findFromTheAnd(index);

        return currentLink;
    }

    /**
     * *if find from begin List [0] element first
     *
     * @param index
     */
    private MyLink findFromTheAnd(int index) {
        MyLink currentLink = firstInList;
        /*UNTIL WE GET TO THE SPECIFIED INDEX */
        for (int i = size - 1; i > index; i--) currentLink = currentLink.next;
        return currentLink;
    }

    /**
     * if find from begin List [0] element first in our search
     *
     * @param index- what we need return element
     */
    private MyLink<E> findFromZero(int index) {
        MyLink currentLink = lastInList;
        /*UNTIL WE GET TO THE SPECIFIED INDEX */
        for (int i = 0; i < index; i++) currentLink = currentLink.previous;
        return currentLink;
    }

    /**
     * appends element in end of list
     */
    public void add(E element) {
        addLast(element);
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
     * Returns the element at the specified position in this Linkedlist.
     *
     * @param index index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException
     */
    public E get(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        if (index == 0) return (E) lastInList.data;
        if (index == size - 1) return (E) firstInList.data;

        MyLink currentLinkAtSpecifiedIndex = findIndexLink(index);
        return (E) currentLinkAtSpecifiedIndex.data;

    }

    /**
     * Removes the element at the specified position in this list.
     * binds the left and right neighbors of the current link
     *
     * @param indexDelete the index of the element to be removed
     * @return the element that was removed from the list
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    public E remove(int indexDelete) {
        E oldData;
        /*check index if take empty cell*/
        if (indexDelete < 0 || indexDelete >= size) throw new IndexOutOfBoundsException();

        if (indexDelete == 0) oldData = removeFirst();
        else if (indexDelete == size - 1) oldData = removeLast();
        else {
            MyLink currentLinkAtSpecifiedIndex = findIndexLink(indexDelete);
            oldData = removeLink(currentLinkAtSpecifiedIndex);
        }
        return oldData;
    }

    private E removeLink(MyLink currentLinkAtSpecifiedIndex) {
        E oldData = (E) currentLinkAtSpecifiedIndex.data;

        //replace link on current object MyLink =  with the reference to the NEXT MyLink,skipping current/
        currentLinkAtSpecifiedIndex.previous.next = currentLinkAtSpecifiedIndex.next;
        //replace link on current object MyLink =  with the reference to the PREvious MyLink, skipping current/
        currentLinkAtSpecifiedIndex.next.previous = currentLinkAtSpecifiedIndex.previous;
        size--;
        return oldData;
    }

    /*if delete [2]== last element*/
    public E removeLast() {
        MyLink currentLink = firstInList;

        /*if once element */
        if (firstInList.next == null) firstInList = null;
        else firstInList.next.previous = null;

        firstInList = currentLink.next;
        size--;
        return (E) currentLink.data;
    }

    /*if delete [0] ==first element*/
    public E removeFirst() {
        MyLink currentLink = lastInList;
        /*if once element */
        if (lastInList.previous == null) lastInList = null;
        else
            lastInList.previous.next = null;

        lastInList = currentLink.previous;
        size--;
        return (E) currentLink.data;
    }


    /**
     * Returns  true if this LinkedList contains the specified element.
     *
     * @param testObjec element whose presence in this list is to be tested
     * @return true if this list contains the specified element
     */
    public boolean contains(E testObjec) {
        for (E currentObj : this) if (currentObj.equals(testObjec)) return true;
        return false;

    }

    /**
     * Override interface Iterable , create main(anonymous) instance whits one variable and increment it when
     * take next element
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            private MyLink currentLink = lastInList;
            int index = 0;

            @Override
            public boolean hasNext() {
                return currentLink != null;
            }

            @Override
            public E next() {
                E data = (E) currentLink.data;
                currentLink = currentLink.previous;
                index++;
                return data;
            }

            @Override
            public void remove() {
                MyLinkedList.this.remove(index - 1);

                index = 0;
                currentLink = lastInList;
            }
        };
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        String result = "[";
        for (E currentObj : this) {
            result +=currentObj.toString()+", ";
        }
        result=result.substring(0,result.lastIndexOf(", "));
        return result+"]";
    }

    protected class MyLink<E> {
        /*data what current link contains */
        E data;
        /*scope where contains link to next link */
        MyLink<E> next;
        /*scope where contains link to previous link */
        MyLink<E> previous;

        /**
         * In constructor create new object type (generics).
         */
        protected MyLink(E data) {
            this.data = data;
        }

        /**
         * method for correct println in foreach
         */
        @Override
        public String toString() {
            return "["+data+"]";
        }
    }
}