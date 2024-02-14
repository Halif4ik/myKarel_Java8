package com.shpp.p2p.cs.dgrymach.Assignment16.LinkedList;

import javax.swing.text.html.HTMLDocument;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * This class consist some one methods for testing myLinked list class , shuch metod us:
 * addLast, set,get,addFirst, add (index), remove
 */
class TestList {
    public static void main(String[] args) {
        MyLinkedList<String> testLinkedL = new MyLinkedList<>();

        //0 1 2
       // for (int i = 0; i < 20; i++) testLinkedL.add(i, "" + i);
        //remove in iteration
        /* Iterator<String> it =testLinkedL.iterator();
         while (it.hasNext()){
                          String data =  it.next();
             System.out.println(data);
             if(data.equals("0"))it.remove();
         }*/


        testLinkedL.add("B");//1
        testLinkedL.addFirst("A");//0
        testLinkedL.addLast("C");//2
        testLinkedL.add(testLinkedL.size() - 1, "F");//3 debug

        printLn(testLinkedL);

        for (int j = 0; j < 4; j++) {
            System.out.println(testLinkedL.remove(testLinkedL.size()-1));
        }
   //            printLn(testLinkedL);

    }

    private static void printLn(MyLinkedList<String> temp) {
        for (int i = 0; i < temp.size(); i++) System.out.print(temp.get(i) + " ");
        System.out.println(temp.size() + "size ");
    }
}

