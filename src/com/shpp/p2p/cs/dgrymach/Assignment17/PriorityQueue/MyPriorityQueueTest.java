package com.shpp.p2p.cs.dgrymach.Assignment17.PriorityQueue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.PriorityQueue;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class MyPriorityQueueTest {
    private static final int DEFAULT_CIKLE = 2_000;
    PriorityQueue<Integer> original;
    MyPriorityQueue<Integer> test;
    Random random;

    @BeforeEach
    void setUp() {
        original = new PriorityQueue<>();
        test = new MyPriorityQueue<>();

        random = new Random();
        int rIndex = original.size();

        /*containing half data Hashmap  in random index(key) */
        for (int i = 0; i < DEFAULT_CIKLE / 2; i++) {
            original.add(rIndex + i);
            test.add(rIndex + i);
            rIndex = random.nextInt(DEFAULT_CIKLE);
        }
    }

    @Test
    void add() {
        /*add  orders value in que (from 0 to half)*/
        for (int i = 0; i < DEFAULT_CIKLE / 2; i++) {
            assertEquals(original.add(i), test.add(i));
    }}

    @Test
    void peek() {
         assertEquals(original.peek(), test.peek());
        }


    @Test
    void poll() {
        add();
        for (int i = 0; i < DEFAULT_CIKLE; i++) assertEquals(original.poll(), test.poll());
    }
}