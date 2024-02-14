package com.shpp.p2p.cs.dgrymach.Assignment16.Queue;


/**
 * This class consist some one methods for testing myQueue list class , shuch metod us: add,size,poll
 */
class TestQueue {
    public static void main(String[] args) {
        MyQueue<String> testQueue = new MyQueue<>();

        testQueue.add("A");//0
        testQueue.add("B");//1
        testQueue.add("C");//2

        for (String it : testQueue) System.out.println(it);

        System.out.println(testQueue.size() + " size");
        System.out.println(testQueue.element() + " first in Queue");

        while (!testQueue.empty()) System.out.println(testQueue.poll());



    }
}

