package com.shpp.p2p.cs.dgrymach.Assignment17.PriorityQueue;

import com.shpp.p2p.cs.dgrymach.Assignment16.LinkedList.MyLinkedList;

import java.util.Iterator;

public class MyLinkedListExt<E extends Comparable<E>> extends MyLinkedList {
    /*scope where contains link to first item in Linked list  */
    MyLinkExt firstInList;
    /* [0]..  1..2  scope where contains link to last item in Linked list  */
    MyLinkExt lastInList;

    @Override
    public void addFirst(Object element) {
        MyLinkExt<E> newLink = new MyLinkExt<>((E) element);
        if (size() == 0) firstInList = newLink;
        else {
            lastInList.next = newLink;
            newLink.previous = lastInList;
        }
        lastInList = newLink;
        incrementSize();
    }

    /*if delete [0] ==first element*/
    @Override
    public Object removeFirst() {
        MyLinkExt currentLink = lastInList;
        /*if once element */
        if (lastInList.previous == null) lastInList = null;
        else lastInList.previous.next = null;

        lastInList = currentLink.previous;
        decrementSize();
        return currentLink.data;
    }

    @Override
    public void addLast(Object element) {
        MyLinkExt<E> newLink = new MyLinkExt<>((E) element);
        if (size() == 0) lastInList = newLink;
        else {
            firstInList.previous = newLink;
            newLink.next = firstInList;
        }
        firstInList = newLink;
        incrementSize();
    }

    @Override
    public Object get(int index) {
        return (E) lastInList.data;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            private MyLinkExt currentLink = lastInList;
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
                MyLinkedListExt.this.remove(index - 1);
                index = 0;
                currentLink = lastInList;
            }
        };
    }
    //----------Override methods---------//

    /**
     * Until we move the minimum value to the beginning of the array [0] or no more than the parent .
     *
     * @param newElement- last added element.
     * @return - index where we put newElement
     */
    boolean putInCurrentPlace(E newElement) {
        MyLinkExt currentLink = lastInList;

        /* as long as the item under the current index is really smaller than the new item */
        while (currentLink != null && currentLink.data.compareTo(newElement) < 0) {
            currentLink = currentLink.previous;
        }
        if (currentLink == null) addLast(newElement);
        else if (currentLink == lastInList) addFirst(newElement);
        else {
            MyLinkExt<E> newLink = new MyLinkExt<>(newElement);
            newLink.next = currentLink.next;
            currentLink.next.previous = newLink;
            newLink.previous = currentLink;
            currentLink.next = newLink;
            incrementSize();
        }
        return true;
    }

    class MyLinkExt<E extends Comparable<E>> {

        /*data what current link contains */
        E data;
        /*scope where contains link to next link */
        MyLinkExt<E> next;
        /*scope where contains link to previous link */
        MyLinkExt<E> previous;

        /**
         * In constructor create new object type (generics).
         */
        MyLinkExt(E data) {
            this.data = data;
        }

    }

}


