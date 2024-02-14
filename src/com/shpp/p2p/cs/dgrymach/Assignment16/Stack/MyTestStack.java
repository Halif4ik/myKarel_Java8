package com.shpp.p2p.cs.dgrymach.Assignment16.Stack;

import com.shpp.p2p.cs.dgrymach.Assignment16.ArrayList.MyArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MyTestStack {

    private static final int DEFAULT_CIKLE = 20_000;
    Stack<Integer> original;
    MyStack<Integer> test;
    Random random;

    @BeforeEach
    void setUp() {
        random = new Random();
        original = new Stack<>();
        test = new MyStack<>();
        int rIndex = 0;
        /*contain half Array list from begin to half cointains in random index */
        for (int i = 0; i < DEFAULT_CIKLE / 2; i++) {
            original.push(rIndex);
            test.push(rIndex);
            rIndex = random.nextInt(original.size());

        }

    }

    @Test
    void add() {
        /*add in random index in center (from 0 to half)*/
        for (int i = 0; i < DEFAULT_CIKLE / 2; i++) {
            int rIndex = random.nextInt(DEFAULT_CIKLE / 2);
            original.push(rIndex);
            test.push(rIndex);
        }
        assertEquals(original.size(), test.size());
        System.out.println("originalSize " + original.size() + " mySize " + test.size());

    }

    @Test
    void addInBeginAndPop() {
        for (int i = 0; i < DEFAULT_CIKLE / 2; i++) {
            original.push(0);
            test.push(0);
        }
        for (int j = 0; j < DEFAULT_CIKLE; j++) assertEquals(original.pop(), test.pop());

    }

    @Test
    void remove() {

        add();/*add yet half elements*/
        /*and delete 99% elements but will leave one element*/
        for (int i = 0; i < DEFAULT_CIKLE - 1; i++) {
            int rIndex = random.nextInt(original.size() - 1);
            assertEquals(original.pop(), test.pop());
        }
        System.out.println("originalSize " + original.size() + " mySize " + test.size());

      //  assertEquals(original.remove(original.size() - 1), test.remove(test.size() - 1));
        System.out.println("originalSize " + original.size() + " mySize " + test.size());
    }


    @Test
    void size() {
        assertEquals(original.size(), test.size());

    }
}