package com.shpp.p2p.cs.dgrymach.Assignment17.HashMap;


import java.util.HashMap;
import java.util.Iterator;

/**
 * This class consist some one methods for testing MyHashmap   class , shuch metod us:
 * addLast, set,get,addFirst, add (index), remove and main it is MyIterator.
 */
class TestHashMap {
    public static void main(String[] args) {
        String s="000000000000001";
        System.out.println(s.hashCode());

        MyHashMap<Integer, String> testHashMap = new MyHashMap<>();
        testHashMap.put(0, "Dima");//0
        testHashMap.put(1, "Kolya");//1
        testHashMap.put(2, null);//2
        testHashMap.put(0, "Simona");//0
        testHashMap.put(null, "SimonaNull");//null
        testHashMap.put(8, "Slava");//null
        testHashMap.put(11, "Danya");//null

        System.out.println(testHashMap.get(0));
        System.out.println(testHashMap.get(1));
        System.out.println(testHashMap.get(2));
        System.out.println(testHashMap.get(null));
        System.out.println(testHashMap.get(8));
        System.out.println(testHashMap.get(11));



        System.out.println(testHashMap.remove(8) + "Remove");

        Iterator<MyHashMap.Entry> it =testHashMap.iterator();
        while (it.hasNext()){
            System.out.println(it.next());
        }



         if (testHashMap.containsKey(9)) System.out.println("containsKey(0)");
        if (testHashMap.containsKey(null)) System.out.println("containsValue");

        System.out.println(testHashMap.size());


    }
}




