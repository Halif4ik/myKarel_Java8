package com.shpp.p2p.cs.dgrymach.Assignment16.ArrayList;

import java.util.Iterator;

/**
 * Resizable-array implementation comparable im have hope. It is array List based on arrays , but not always index array
 * is index  our ArrayLIst,if we remove lat or first element from arraylist we only shift indexZero and index put element.
 * implementation methods: set, add, add(index ,element),get, size,contains,remove.¡
 */
public class MyArrayList<E> implements Iterable<E> {

    /**
     * Default initial capacity.¡
     */
    private static final int DEFAULT_CAPACITY = 16;
    /**
     * The array buffer into which the elements of the ArrayList are stored.
     * The capacity of the ArrayList is the array size is the difference between an indexPutLast and an indexZeroElement.
     */
    private E[] elementData;

    /**
     * The index where in the ArrayList we must put next element.
     */
    private int indexPutLast;
    /**
     * The index where in our array begin the ArrayList.
     */
    private int indexZeroElement;
    /*------scope-----*/

    /**
     * Constructs an empty list with an initial capacity of 0001 000.
     */
    public MyArrayList() {
        elementData = (E[]) (new Object[DEFAULT_CAPACITY]);
        indexZeroElement = indexPutLast = 0;
    }

    /**
     * Replaces the element at the specified position in this list with
     * the specified element.
     *
     * @param index   index of the element to replace
     * @param element element to be stored at the specified position
     * @return the element previously at the specified position
     */
    public E set(int index, E element) {
        if (index < 0 || index >= size()) throw new IndexOutOfBoundsException();
        E OldElement = elementData[index + indexZeroElement];
        elementData[index + indexZeroElement] = element;
        return OldElement;
    }

    /**
     * Appends the specified element to the end of this list.
     *
     * @param newItem element to be appended to this list
     * @return {@code true}
     */
    public boolean add(E newItem) {
        if (indexPutLast == elementData.length) grow();
        elementData[indexPutLast++] = newItem;
        return true;
    }

    /**
     * check if array(not capacity ArrayList) is full we need grow
     */
    private void grow() {
        if (indexZeroElement != 0) {
            final int oldSize = size();

            System.arraycopy(elementData, indexZeroElement, elementData, 0, indexPutLast - indexZeroElement);
            indexPutLast = oldSize;
            indexZeroElement = 0;
        } else {
            E[] temp = (E[]) new Object[elementData.length << 1];
            System.arraycopy(elementData, 0, temp, 0, indexPutLast);
            elementData = temp;
        }
    }

    /**
     * Inserts the specified element at the specified position in this list.
     * and shift all current element to right.
     *
     * @param indexAppends index at which the specified element is to be inserted
     * @param element      element to be inserted
     * @throws IndexOutOfBoundsException
     */
    public void add(int indexAppends, E element) {
        if (indexAppends < 0 || indexAppends > size()) throw new IndexOutOfBoundsException();
        if (indexAppends == size()) add(element);
            /*grow array before appends if went to right edge array */
        else {
            if (indexPutLast == elementData.length) grow();
            //                             (8-2) =6  -  1  = 5
            int countsElementsToShiftsRight = size() - indexAppends;

            System.arraycopy(elementData, indexAppends + indexZeroElement, elementData,
                    indexAppends + indexZeroElement + 1, countsElementsToShiftsRight);

            //                    0          +  2 =    [index begin arrayList 2]
            elementData[indexAppends + indexZeroElement] = element;
            indexPutLast++;
        }
    }

    /**
     * Removes the element at the specified position in this list.
     * последующие
     * Shifts any subsequent elements to the left (subtracts one from their indices).
     *
     * @param indexDelete the index of the element to be removed
     * @return the element that was removed from the list
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    public E remove(int indexDelete) {
        /*in current index appends count delete element from left*/
        indexDelete = indexDelete + indexZeroElement;

        /*check index if take empty cell if is empty*/
        if (indexDelete < indexZeroElement || indexDelete > indexPutLast - 1 || size() == 0)
            throw new IndexOutOfBoundsException();
        E oldValue;

        /*if it is last or first element what we put in array*/
        if (indexDelete == indexPutLast - 1) {
            oldValue = elementData[indexDelete];
            indexPutLast--;
        } else if (indexDelete == indexZeroElement) {
            oldValue = elementData[indexDelete];
            indexZeroElement++;
            /*else shift all right element from the current, in left on one */
        } else {
            oldValue = elementData[indexDelete];
            System.arraycopy(elementData, indexDelete + 1, elementData, indexDelete,
                    indexPutLast - 1 - indexDelete);
            indexPutLast--;
        }

        return oldValue;
    }

    /**
     * Returns the element at the specified position in this list.
     *
     * @param index index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    public E get(int index) {
        if (index < 0 || index >= size()) throw new IndexOutOfBoundsException();
        return elementData[index + indexZeroElement];
    }

    /**
     * Returns the number of elements in this list.
     *
     * @return the number of elements in this list
     */
    public int size() {
        return indexPutLast - indexZeroElement;
    }
// todo fix work whith null
    /**
     * Returns  true if this arrayList contains the specified element.
     *
     * @param testElem element whose presence in this list is to be tested
     * @return true if this list contains the specified element
     */
    public boolean contains(E testElem) {
        for (int i = 0; i < size(); i++) {
            E currElement = get(i);
            if (currElement == testElem || (currElement != null && testElem.equals(currElement))) return true;
        }
        return false;
    }

    @Override
    public String toString() {
        String result = "[";
        for (int i = 0; i < size(); i++) result += get(i).toString() + ", ";

        result = result.substring(0, result.lastIndexOf(", "));
        return result + "]";

    }

    /**
     * Override interface Iterable , create main class whits one variable and increment it when take next element
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            int index = 0;

            @Override
            public boolean hasNext() {
                return index < size();
            }

            @Override
            public E next() {
                return get(index++);
            }
        };
    }
}





