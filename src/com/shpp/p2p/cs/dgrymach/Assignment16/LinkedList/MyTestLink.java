package com.shpp.p2p.cs.dgrymach.Assignment16.LinkedList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MyTestLink {

    private static final int DEFAULT_CIKLE = 10_000;
    LinkedList<Integer> original;
    MyLinkedList<Integer> test;
    Random ran;

    @BeforeEach
    void setUp() {
        ran = new Random();
        original = new LinkedList<>();
        test = new MyLinkedList<>();
        /*contain half linked list */
        for (int i = 0; i < DEFAULT_CIKLE / 2; i++) {
            original.add(0, i);
            test.add(0, i);
        }
    }

    @Test
    void add() { //add in random index
        int rIndex = ran.nextInt(DEFAULT_CIKLE / 2);

        for (int i = 0; i < DEFAULT_CIKLE / 2; i++) {
            original.add(rIndex, i);
            test.add(rIndex, i);
        }
        assertEquals(original.size(), test.size());

        System.out.println("originalSize " + original.size() + " mySize " + test.size());
    }

    @Test
    void addIndexAndGet() {
        for (int i = 0; i < DEFAULT_CIKLE; i++) {
            original.add(0, i);
            test.add(0, i);
        }
        for (int j = 0; j < DEFAULT_CIKLE; j++) assertEquals(original.get(j), test.get(j));
    }


    @Test
    void remove() { //random delete
        add();
        for (int i = 0; i < DEFAULT_CIKLE - 1; i++) {
            int rIndex = ran.nextInt(original.size() - 1);
            assertEquals(original.remove(rIndex), test.remove(rIndex));
        }
        assertEquals(original.remove(original.size() - 1), test.remove(test.size() - 1));
    }


    @Test
    void size() {
        assertEquals(original.size(), test.size());
    }
}