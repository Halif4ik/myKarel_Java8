package com.shpp.p2p.cs.dgrymach.Assignment16.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class MyTest {

    private static final int DEFAULT_CIKLE = 5_000;
    ArrayList<Integer> original;
    MyArrayList<Integer> test;
    Random random;

    @BeforeEach
    void setUp() {
        random = new Random();
        original = new ArrayList<>();
        test = new MyArrayList<>();
        int rIndex = 0;
        /*contain half Array list from begin to half cointains in random index */
        for (int i = 0; i < DEFAULT_CIKLE / 4; i++) {
            original.add(rIndex, 1);
            test.add(rIndex, 1);

            original.add(null);
            test.add(null);
            rIndex = random.nextInt(original.size());
        }

    }

    @Test
    void add() {
        /*add in random index in center (from 0 to half)*/
        for (int i = 0; i < DEFAULT_CIKLE / 2; i++) {
            int rIndex = random.nextInt(DEFAULT_CIKLE / 2);
            original.add(rIndex, i);
            test.add(rIndex, i);
        }
        assertEquals(original.size(), test.size());
        System.out.println("originalSize " + original.size() + " mySize " + test.size());

    }

    @Test
    void addInBeginAndGet() {
        for (int i = 0; i < DEFAULT_CIKLE / 2; i++) {
            original.add(0, i);
            test.add(0, i);
        }
        for (int j = 0; j < DEFAULT_CIKLE; j++) assertEquals(original.get(j), test.get(j));

    }

    @Test
    void remove() {

        add();/*add yet half elements*/
        /*and delete 99% elements but will leave one element*/
        for (int i = 0; i < DEFAULT_CIKLE - 1; i++) {
            int rIndex = random.nextInt(original.size() - 1);
            assertEquals(original.remove(rIndex), test.remove(rIndex));
        }
        System.out.println("originalSize " + original.size() + " mySize " + test.size());

        assertEquals(original.remove(original.size() - 1), test.remove(test.size() - 1));
        System.out.println("originalSize " + original.size() + " mySize " + test.size());
    }

    @Test
    void containsAndGet() {
        add();
        for (int i = 0; i < DEFAULT_CIKLE; i++) {
            int ran = random.nextInt(DEFAULT_CIKLE);
            assertEquals(original.contains(ran), test.contains(ran));
            assertEquals(original.get(ran), test.get(ran));
        }
        assertEquals(original.get(0), test.get(0));

    }

    @Test
    void size() {
        assertEquals(original.size(), test.size());

    }
}