package com.shpp.p2p.cs.dgrymach.Assignment17.PriorityQueue;

/**
 * This class consist some one methods for testing MyHashmap   class , shuch metod us:
 * addLast, set,get,addFirst, add (index), remove and main it is MyIterator.
 */
class TestPriorityQueue {
    public static void main(String[] args) {

        MyPriorityQueue<Integer> testPQueue = new MyPriorityQueue<>();
        testPQueue.add(3);//0
        testPQueue.add(2);//1
       testPQueue.add(1);//2
  //      testPQueue.add(4);//3

        for (int i = 0; i < testPQueue.size(); i++)    System.out.println(testPQueue.get(i));


    }
}



