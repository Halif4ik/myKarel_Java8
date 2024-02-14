package com.shpp.p2p.cs.dgrymach.Assignment17.PriorityQueue;

import java.util.PriorityQueue;
import java.util.Random;

/**
 * This class consist some one methods for testing MyHashmap   class , shuch metod us:
 * addLast, set,get,addFirst, add (index), remove and main it is MyIterator.
 */
class TestPriorityQueue {
    public static void main(String[] args) {
        MyPriorityQueue<Integer> testPQueue = new MyPriorityQueue<>();
        PriorityQueue<Integer> orig =new PriorityQueue<>();

        Random ran=new Random();
        for (int i = 0; i <5 ; i++) {
            int ra =ran.nextInt(500);
            testPQueue.add(ra);//0
            orig.add(ra);//0
        }

       for (Integer curEl : orig) System.out.println(curEl);
        System.out.println("------------");
       for (Integer curEl : testPQueue) System.out.println(curEl);
    }
}



