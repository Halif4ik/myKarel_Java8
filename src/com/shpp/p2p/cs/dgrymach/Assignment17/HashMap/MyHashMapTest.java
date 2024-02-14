package com.shpp.p2p.cs.dgrymach.Assignment17.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class MyHashMapTest {

    private static final int DEFAULT_CIKLE = 10_000;
    HashMap<Integer, Integer> original;
    MyHashMap<Integer, Integer> test;
    Random random;

    @BeforeEach
    void setUp() {
        original = new HashMap<>();
        test = new MyHashMap<>();

        random = new Random();
        int rIndex = original.size();

        /*containing half data Hashmap  in random index(key) */
        for (int i = 0; i < 100; i++) {
            original.put(rIndex , i);
            test.put(rIndex , i);

            original.put(null , i);
            test.put(null , i);

            original.put(null , null);
            test.put(null , null);

            original.put(rIndex , null);
            test.put(rIndex , null);
            rIndex = random.nextInt(original.size());
        }
    }

    @Test
    void put() {
        /*add in random index in center (from 0 to half)*/
        for (int i = 0; i < DEFAULT_CIKLE ; i++) {
            assertEquals(original.put(i, i), test.put(i, i));
        }

    }

    @Test
    void get() {
        put();
        for (int i = 0; i < DEFAULT_CIKLE / 2; i++) {
            if (!(original.get(i) == null)) assertEquals(original.get(i), test.get(i));
        }
    }

    @Test
    void containsKey() {
        put();/* now we dont put  in null key*/
        for (int i = 0; i < DEFAULT_CIKLE; i++) assertEquals(original.containsKey(i), test.containsKey(i));
    }

    @Test
    void remove() {
        put();
        for (int i = 0; i < DEFAULT_CIKLE; i++) {
            if (original.containsKey(i)) assertEquals(original.remove(i), test.remove(i));
        }
    }

    @Test
    void isEmpty() {
        assertEquals(original.isEmpty(), test.isEmpty());

        remove();
        assertEquals(original.isEmpty(), test.isEmpty());
    }

    @Test
    void containsValue() {
        put();/* now we dont put null value*/
        for (int i = 0; i < DEFAULT_CIKLE/2; i++) {
                      assertEquals(original.containsValue(i), test.containsValue(i));
        }
    }
}