package com.shpp.p2p.cs.dgrymach.Assignment16.Stack;


import java.util.Iterator;
import java.util.Stack;

/**
 * This class consist some one methods for testing myLinked list class , shuch metod us:
 * addLast, set,get,addFirst, add (index), remove
 */
class TestStack {
    public static void main(String[] args) {
        MyStack<String> testStac = new MyStack<>();
        Iterator it =testStac.iterator();

        testStac.push("A");//2
        testStac.push("B");//1
        testStac.push("C");//0

       for (String stac : testStac)           System.out.println(stac);


        System.out.println(testStac.size() + " size");
        System.out.println(testStac.peek());
        while (!testStac.empty()) System.out.println(testStac.pop());

    }


}

